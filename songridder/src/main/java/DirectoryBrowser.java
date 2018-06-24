import beatmap.BeatMap;
import beatmap.BeatMapCriteria;
import beatmap.BeatMapParser;
import com.google.common.collect.ImmutableList;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DirectoryBrowser {

    private static final String OSU_EXTENSION = "osu";
    private static final String WAV_EXTENSION = "wav";
    private static final String AVI_EXTENSION = "avi";

    public static List<Path> readAllDirectories(final String rootFolder) throws IOException {
        return Files.walk(Paths.get(rootFolder)).filter(Files::isDirectory).skip(1).collect(Collectors.toList());
    }

    public static List<BeatMap> readAllBeatMaps(final Path songFolder, final BeatMapCriteria criteria) throws IOException {
        List<Path> beatMapPaths = Files.walk(songFolder).filter(isBeatMapFile()).collect(Collectors.toList());

        return createBeatMapList(beatMapPaths).stream().filter(criteria::verifyBeatMap).map(Optional::get).collect(Collectors.toList());
    }

    public static List<Path> readSoundsAndVideo(final Path songFolder, final BeatMapCriteria criteria) throws IOException {
        return criteria.isRemoveCustomSounds() && criteria.isRemoveVideo() ?
                Files.walk(songFolder).filter(isSongOrVideo()).collect(Collectors.toList()) : ImmutableList.of();
    }

    private static List<Optional<BeatMap>> createBeatMapList(final List<Path> beatMapPaths) {
        return beatMapPaths.stream().map(BeatMapParser::parseBeatMapInfo).collect(Collectors.toList());
    }

    private static Predicate<Path> isSongOrVideo() {
        return file -> Files.isRegularFile(file) &&
                (FilenameUtils.isExtension(file.getFileName().toString(), AVI_EXTENSION)
                        || FilenameUtils.isExtension(file.getFileName().toString(), WAV_EXTENSION));
    }

    private static Predicate<Path> isBeatMapFile() {
        return file -> Files.isRegularFile(file) && FilenameUtils.isExtension(file.getFileName().toString(), OSU_EXTENSION);
    }
}

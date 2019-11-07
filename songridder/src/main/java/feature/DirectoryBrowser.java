package feature;

import com.google.common.collect.ImmutableList;
import model.beatmap.BeatMap;
import model.beatmap.BeatMapCriteria;
import model.beatmap.BeatMapParser;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DirectoryBrowser {
    private static final String OSU_EXTENSION = "osu";
    private static final String WAV_EXTENSION = "wav";
    private static final String AVI_EXTENSION = "avi";

    public static List<Path> readAllDirectories(final String songsFolder) {
        List<Path> directories = new ArrayList<>();
        try {
            directories = Files.walk(Paths.get(songsFolder), 1).filter(Files::isDirectory).skip(1).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return directories;
    }

    public static List<BeatMap> readAllBeatMaps(final Path beatMapFolder, final BeatMapCriteria criteria) {
        List<Path> beatMapPaths = new ArrayList<>();
        try {
            beatMapPaths = Files.walk(beatMapFolder).filter(isBeatMapFile()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return createBeatMapList(beatMapPaths).stream().filter(criteria::verifyBeatMap).map(Optional::get).collect(Collectors.toList());
    }

    public static List<Path> readSoundsAndVideo(final Path beatMapFolder, final BeatMapCriteria criteria) {
        List<Path> paths = new ArrayList<>();
        try {
            paths = criteria.isRemoveCustomSounds() && criteria.isRemoveVideo()
                    ? Files.walk(beatMapFolder).filter(isSongOrVideo()).collect(Collectors.toList())
                    : ImmutableList.of();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return paths;
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

import beatmap.BeatMap;
import beatmap.BeatMapCriteria;
import beatmap.BeatMapParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DirectoryBrowser {

    private static final String OSU_EXTENSION = ".osu";

    public List<Path> readAllDirectories(final String rootFolder) throws IOException {
        return Files.walk(Paths.get(rootFolder)).filter(Files::isDirectory).collect(Collectors.toList());
    }

    public List<BeatMap> readAllBeatMaps(final String songFolder, final BeatMapCriteria criteria) throws IOException {
        List<Path> beatMapPaths = Files.walk(Paths.get(songFolder)).filter(isBeatMapFile()).collect(Collectors.toList());

        return createBeatMapList(beatMapPaths).stream().filter(criteria::verifyBeatMap).collect(Collectors.toList());
    }

    private List<BeatMap> createBeatMapList(final List<Path> beatMapPaths) {
        return beatMapPaths.stream().map(BeatMapParser::parseBeatMapInfo).collect(Collectors.toList());
    }

    private Predicate<Path> isBeatMapFile() {
        return file -> Files.isRegularFile(file) && file.endsWith(OSU_EXTENSION);
    }
}

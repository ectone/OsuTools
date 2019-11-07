package model.beatmap;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeatMapParser {
    private static final Pattern VERSION_PATTERN = Pattern.compile("osu file format v(?<version>\\d+)");
    private static final Pattern NAME_PATTERN = Pattern.compile("Title:(?<name>.*)[\r|\n]");
    private static final Pattern ARTIST_PATTERN = Pattern.compile("Artist:(?<artist>.*)[\r|\n]");
    private static final Pattern MODE_PATTERN = Pattern.compile("Mode:(?<mode>\\s\\d)");
    private static final Pattern DIFFICULTY_PATTERN = Pattern.compile("OverallDifficulty:(?<diff>\\d)");
    private static final Pattern ID_PATTERN = Pattern.compile("BeatmapID:(?<id>.*)[\r|\n]");
    private static final Pattern SET_ID_PATTERN = Pattern.compile("BeatmapSetID:(?<setId>.*)[\r|\n]");

    public static Optional<BeatMap> parseBeatMapInfo(final Path pathToBeatMap) {
        BeatMap beatMap = null;
        try {
            String content = readFile(pathToBeatMap, Charset.defaultCharset());
            if (Integer.valueOf(getMatch(content, VERSION_PATTERN, "version").trim()) >= 13) {
                beatMap = new BeatMap(
                        getMatch(content, NAME_PATTERN, "name"),
                        getMatch(content, ARTIST_PATTERN, "artist"),
                        BeatMapMode.fromString(getMatch(content, MODE_PATTERN, "mode").trim()),
                        Double.valueOf(getMatch(content, DIFFICULTY_PATTERN, "diff")),
                        getMatch(content, ID_PATTERN, "id"),
                        getMatch(content, SET_ID_PATTERN, "setId"),
                        pathToBeatMap
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(beatMap);
    }

    private static String getMatch(final String content, final Pattern namePattern, final String name) {
        String match = null;
        Matcher matcher = namePattern.matcher(content);
        if (matcher.find()) {
            match = matcher.group(name);
        }

        return match;
    }

    private static String readFile(Path path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, encoding);
    }
}

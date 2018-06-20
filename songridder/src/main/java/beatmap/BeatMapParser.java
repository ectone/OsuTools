package beatmap;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class BeatMapParser {

    private static final Pattern NAME_PATTERN = Pattern.compile("Title:(?<name>[\\w\\s]+)");
    private static final Pattern ARTIST_PATTERN = Pattern.compile("Artist:(?<artist>[\\w\\s]+)");
    private static final Pattern MODE_PATTERN = Pattern.compile("Mode:(?<mode>\\s\\d)");
    private static final Pattern DIFFICULTY_PATTERN = Pattern.compile("OverallDifficulty:(?<diff>\\d)");
    private static final Pattern ID_PATTERN = Pattern.compile("BeatmapID:(?<id>\\w+)");

    public static BeatMap parseBeatMapInfo(final Path pathToBeatMap) {
        BeatMap beatMap = null;
        try {
            String content = readFile(pathToBeatMap, Charset.defaultCharset());
            beatMap = new BeatMap(
                    NAME_PATTERN.matcher(content).group("name"),
                    ARTIST_PATTERN.matcher(content).group("artist"),
                    BeatMapMode.valueOf(MODE_PATTERN.matcher(content).group("mode")),
                    Double.valueOf(DIFFICULTY_PATTERN.matcher(content).group("diff")),
                    ID_PATTERN.matcher(content).group("id"),
                    pathToBeatMap
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return beatMap;
    }

    private static String readFile(Path path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, encoding);
    }
}

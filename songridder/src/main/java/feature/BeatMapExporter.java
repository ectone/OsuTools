package feature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

public class BeatMapExporter {

    public void exportBeatMaps(final String songsFolder, final String targetFilePath) {
        try {
            if(!Files.exists(Paths.get(targetFilePath)))
            Files.createFile(Paths.get(targetFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        createDownloadLinks(songsFolder).forEach(url -> appendLinkToFile(targetFilePath, url));
    }

    private Set<String> createDownloadLinks(final String songsFolder) {
        Set<String> downloadLinks = new HashSet<>();
        DirectoryBrowser.readAllDirectories(songsFolder).forEach(dir -> downloadLinks.add(buildLink(dir)));
        return downloadLinks;
    }

    private String buildLink(final Path directory) {
        String dirName = directory.getFileName().toString();
        return String.format("http://bloodcat.com/osu/d/%s\n", dirName.substring(0, dirName.indexOf(' ')));
    }

    private void appendLinkToFile(final String targetFile, final String url) {
        try {
            System.out.println("Adding download link: " + url);
            Files.write(Paths.get(targetFile), url.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

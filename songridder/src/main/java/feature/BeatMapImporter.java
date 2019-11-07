package feature;

import org.apache.http.client.fluent.Request;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BeatMapImporter {

    public void importBeatMaps(final String songsFolder, final String importFile) {
        getDownloadUrls(importFile).forEach(url -> download(url, songsFolder));
    }

    private List<String> getDownloadUrls(final String songsFolder) {
        List<String> links = new ArrayList<>();
        try {
            links = Files.readAllLines(Paths.get(songsFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return links;
    }

    private void download(final String url, final String songsFolder) {
        try {
            Request.Get(url).execute().saveContent(new File(songsFolder+url.substring(url.lastIndexOf("/")) + ".osz"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

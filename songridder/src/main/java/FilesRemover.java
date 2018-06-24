import beatmap.BeatMap;
import beatmap.BeatMapCriteria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilesRemover {

    public void removeFiles(final String root, final BeatMapCriteria criteria) throws IOException {
        List<Path> filesToRemove = listAllFilesToRemove(root, criteria);

        System.out.println("Removing " + filesToRemove.size() + " files.");
        filesToRemove.forEach(file -> {
            try {
                System.out.println("Removing file: " + file.getFileName());
                Files.delete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private List<Path> listAllFilesToRemove(final String root, final BeatMapCriteria criteria) throws IOException {
        List<Path> files = new ArrayList<>();
        DirectoryBrowser.readAllDirectories(root).forEach(
                folder -> {
                    try {
                        files.addAll(
                                DirectoryBrowser.readAllBeatMaps(folder, criteria).stream().map(BeatMap::getFolder).collect(Collectors.toList()));
                        files.addAll(
                                DirectoryBrowser.readSoundsAndVideo(folder, criteria));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        return files;
    }
}

package feature;

import model.beatmap.BeatMap;
import model.beatmap.BeatMapCriteria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilesRemover {

    public void removeBeatMaps(final String songsFolder, final BeatMapCriteria criteria) {
        List<Path> filesToRemove = listAllFilesToRemove(songsFolder, criteria);

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

    private List<Path> listAllFilesToRemove(final String songsFolder, final BeatMapCriteria criteria) {
        List<Path> files = new ArrayList<>();
        DirectoryBrowser.readAllDirectories(songsFolder).forEach(
                folder -> {
                    files.addAll(
                            DirectoryBrowser.readAllBeatMaps(folder, criteria).stream().map(BeatMap::getFolder).collect(Collectors.toList()));
                    files.addAll(
                            DirectoryBrowser.readSoundsAndVideo(folder, criteria));
                }
        );

        return files;
    }
}

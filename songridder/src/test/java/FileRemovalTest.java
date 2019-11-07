import feature.BeatMapExporter;
import feature.BeatMapImporter;
import feature.FilesRemover;
import model.beatmap.BeatMapCriteria;
import model.beatmap.BeatMapMode;
import org.junit.Test;

import java.io.IOException;

public class FileRemovalTest {

    @Test
    public void test() throws IOException {
        FilesRemover filesRemover = new FilesRemover();
        BeatMapCriteria criteria = new BeatMapCriteria(null, BeatMapMode.STANDARD, null, false, false, false);
        filesRemover.removeBeatMaps("", criteria);
    }

    @Test
    public void exportTest() {
        FeatureController featureController = new FeatureController(new FilesRemover(), new BeatMapExporter(), new BeatMapImporter());
        featureController.exportBeatMaps("c:\\Program Files\\osu\\Songs\\", "c:\\Users\\ect1\\Downloads\\export.txt");
    }

    @Test
    public void importTest() {
        FeatureController featureController = new FeatureController(new FilesRemover(), new BeatMapExporter(), new BeatMapImporter());
        featureController.importBeatMaps("c:\\Users\\ect1\\Downloads\\", "c:\\Users\\ect1\\Downloads\\export.txt");
    }
}

import feature.BeatMapExporter;
import feature.BeatMapImporter;
import feature.FilesRemover;
import model.beatmap.BeatMapCriteria;

public class FeatureController implements FeatureControllerInterface {

    private FilesRemover filesRemover;
    private BeatMapExporter beatMapExporter;
    private BeatMapImporter beatMapImporter;

    public FeatureController(final FilesRemover filesRemover, final BeatMapExporter beatMapExporter, final BeatMapImporter beatMapImporter) {
        this.filesRemover = filesRemover;
        this.beatMapExporter = beatMapExporter;
        this.beatMapImporter = beatMapImporter;
    }

    @Override
    public void removeBeatMaps(final String songsFolder, final BeatMapCriteria criteria) {
        filesRemover.removeBeatMaps(songsFolder, criteria);
    }

    @Override
    public void exportBeatMaps(final String songsFolder, final String targetFilePath) {
        beatMapExporter.exportBeatMaps(songsFolder, targetFilePath);
    }

    @Override
    public void importBeatMaps(final String songsFolder, final String importFile) {
        beatMapImporter.importBeatMaps(songsFolder, importFile);
    }
}

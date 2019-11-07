import model.beatmap.BeatMapCriteria;

import java.io.IOException;

public interface FeatureControllerInterface {

    void removeBeatMaps(String songsFolder, BeatMapCriteria criteria) ;

    void exportBeatMaps(String songsFolder, String targetFilePath);

    void importBeatMaps(String songsFolder, String importDir);
}

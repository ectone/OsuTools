import beatmap.BeatMapCriteria;
import beatmap.BeatMapMode;
import org.junit.Test;

import java.io.IOException;

public class FileRemovalTest {

    @Test
    public void test() throws IOException {
        FilesRemover filesRemover = new FilesRemover();
        BeatMapCriteria criteria = new BeatMapCriteria(null, BeatMapMode.STANDARD, null, true, true, true);
        filesRemover.removeFiles("", criteria);
    }
}

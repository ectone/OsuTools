package model.beatmap;

import java.nio.file.Path;

public class BeatMap {
    private String name;
    private String artist;
    private BeatMapMode mode;
    private Double odDifficulty;
    private String beatMapId;
    private String beatMapSetId;
    private Path folder;

    BeatMap(
            final String name,
            final String artist,
            final BeatMapMode mode,
            final Double difficulty,
            final String beatMapId,
            final String beatmapSetId,
            final Path folder) {
        this.name = name;
        this.artist = artist;
        this.mode = mode;
        this.odDifficulty = difficulty;
        this.beatMapId = beatMapId;
        this.beatMapSetId = beatmapSetId;
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public BeatMapMode getMode() {
        return mode;
    }

    public Double getOdDifficulty() {
        return odDifficulty;
    }

    public String getBeatMapId() {
        return beatMapId;
    }

    public String getBeatMapSetId() {
        return beatMapSetId;
    }

    public Path getFolder() {
        return folder;
    }
}

package beatmap;

import java.util.Optional;

/*
 * Criteria used to search for beat maps
 */
public class BeatMapCriteria {
    private String artist;
    private BeatMapMode mode;
    private Double minDifficulty;

    public BeatMapCriteria(
            final String artist,
            final BeatMapMode mode,
            final Double minDifficulty,
            final boolean removeCustomSounds,
            final boolean removeVideo) {
        this.artist = artist;
        this.mode = mode;
        this.minDifficulty = minDifficulty;
    }

    public Optional<String> getArtist() {
        return Optional.of(artist);
    }

    public Optional<BeatMapMode> getMode() {
        return Optional.ofNullable(mode);
    }

    public Optional<Double> getMinDifficulty() {
        return Optional.ofNullable(minDifficulty);
    }

    private boolean checkArtist(final String matchingArtist) {
        return artist.equals(matchingArtist);
    }

    private Boolean matchArtist(final String artist) {
        return getArtist().map(artist::equals).orElse(true);
    }

    private Boolean matchMode(final BeatMapMode beatMapMode) {
        return getMode().map(beatMapMode::equals).orElse(true);
    }

    private Boolean matchDifficulty(final double diff) {
        return getMinDifficulty().map(matching -> diff > matching).orElse(true);
    }

    public boolean verifyBeatMap(final BeatMap beatMap) {
        return matchArtist(beatMap.getArtist()) && matchDifficulty(beatMap.getOdDifficulty()) && matchMode(beatMap.getMode());
    }
}

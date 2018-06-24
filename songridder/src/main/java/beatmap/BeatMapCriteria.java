package beatmap;

import java.util.Optional;

/*
 * Criteria used to search for beat maps
 */
public class BeatMapCriteria {
    private String artist;
    private BeatMapMode mode;
    private Double minDifficulty;
    private boolean removeCustomSounds;
    private boolean removeVideo;
    private boolean invertModeSearch;

    public BeatMapCriteria(
            final String artist,
            final BeatMapMode mode,
            final Double minDifficulty,
            final boolean removeCustomSounds,
            final boolean removeVideo,
            final boolean invertModeSearch) {
        this.artist = artist;
        this.mode = mode;
        this.minDifficulty = minDifficulty;
        this.removeCustomSounds = removeCustomSounds;
        this.removeVideo = removeVideo;
        this.invertModeSearch = invertModeSearch;
    }

    public Optional<String> getArtist() {
        return Optional.ofNullable(artist);
    }

    public Optional<BeatMapMode> getMode() {
        return Optional.ofNullable(mode);
    }

    public Optional<Double> getMinDifficulty() {
        return Optional.ofNullable(minDifficulty);
    }

    public boolean isRemoveCustomSounds() {
        return removeCustomSounds;
    }

    public boolean isRemoveVideo() {
        return removeVideo;
    }

    public boolean isInvertModeSearch() {
        return invertModeSearch;
    }

    private Boolean matchArtist(final String artist) {
        return getArtist().map(artist::equals).orElse(true);
    }

    private Boolean matchMode(final BeatMapMode beatMapMode) {
        return isInvertModeSearch() ?
                getMode().map(mode -> !mode.equals(beatMapMode)).orElse(true)
                : getMode().map(beatMapMode::equals).orElse(true);
    }

    private Boolean matchDifficulty(final double diff) {
        return getMinDifficulty().map(matching -> diff < matching).orElse(true);
    }

    public boolean verifyBeatMap(final Optional<BeatMap> beatMapOptional) {
        boolean match = false;
        if (beatMapOptional.isPresent()) {
            BeatMap beatMap = beatMapOptional.get();
            match = matchArtist(beatMap.getArtist()) && matchDifficulty(beatMap.getOdDifficulty()) && matchMode(beatMap.getMode());
        }

        return match;
    }
}

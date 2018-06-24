package beatmap;

public enum BeatMapMode {
    STANDARD("0"),
    TAIKO("1"),
    BEAT("2"),
    MANIA("3");

    private String value;

    BeatMapMode(final String newValue) {
        value = newValue;
    }

    public static BeatMapMode fromString(final String value) {
        BeatMapMode beatMapMode = null;
        for (final BeatMapMode mode : BeatMapMode.values()) {
            if (mode.value.equals(value))
                beatMapMode = mode;
        }
        return beatMapMode;
    }
}

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

    public String getValue() {
        return value;
    }
}

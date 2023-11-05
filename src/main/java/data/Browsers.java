package data;

public enum Browsers {
    CHROME("chrome"),
    FIREFOX("firefox"),
    OPERA("opera");

    private final String name;
    Browsers(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

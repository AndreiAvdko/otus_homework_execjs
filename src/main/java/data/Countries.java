package data;

public enum Countries {
    RUSSIA("Россия");

    private final String name;
    Countries(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

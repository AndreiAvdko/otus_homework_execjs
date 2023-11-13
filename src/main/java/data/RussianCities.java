package data;

public enum RussianCities {
    MOSCOW("Москва"),
    ST_PETERSBUG("Санкт-Петербург");

    private final String name;
    RussianCities(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

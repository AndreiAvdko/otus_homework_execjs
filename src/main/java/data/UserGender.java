package data;

public enum UserGender {
    MALE("Мужской"),
    FEMALE("Женский"),
    NO_SELECT("Не указано");

    private final String name;
    UserGender(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

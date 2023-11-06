package data;

public enum LanguageLevel {
    NO_SELECTED("Не указано"),
    BEGINNER("Начальный уровень (Beginner)"),
    ELEMENTARY("Элементарный уровень (Elementary)"),
    PRE_INTERMEDIATE("Ниже среднего (Pre-Intermediate)"),
    INTERMEDIATE("Средний (Intermediate)"),
    UPPER_INTERMEDIATE("Выше среднего (Upper Intermediate)"),
    ADVANCED("Продвинутый (Advanced)"),
    MASTERY("Супер продвинутый (Mastery)");

    private final String name;
    LanguageLevel(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

package utils.fakeData;

import com.github.javafaker.Faker;

import com.ibm.icu.text.Transliterator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class DataFaker {
    private static Faker faker;

    public static String fakeRUFirstName() {
        faker = new Faker(new Locale("ru"));
        return faker.name().firstName();
    }

    public static String fakeRUSurnameName() {
        faker = new Faker(new Locale("ru"));
        return faker.name().lastName();
    }

    public static String RuIntoLatin(String ruString) {
        Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");
        return toLatinTrans.transliterate(ruString);
    }

    public static String generateBirthDate() {
        faker = new Faker();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(faker.date().birthday(18, 60));
    }

    public static int getRandomInDuration(int bound) {
        Random random = new Random();
        int result = random.nextInt(bound);
        if (result != 0) {
            return result;
        } else return result + 1;
    }

    public static String getRandomCompanyName() {
        return faker.company().name();
    }
}

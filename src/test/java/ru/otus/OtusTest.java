package ru.otus;

import data.*;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.otus.pages.MainPage;
import utils.fakeData.DataFaker;

import java.util.HashMap;
import java.util.Map;

import static data.WorkFormat.*;


public class OtusTest {
    private WebDriver driver;
    private final String FIRST_NAME = DataFaker.fakeRUFirstName();
    private final String LATIN_FNAME = DataFaker.RuIntoLatin(FIRST_NAME);
    private final String SURNAME = DataFaker.fakeRUSurnameName();
    private final String LATIN_SNAME = DataFaker.RuIntoLatin(SURNAME);
    private final String BLOG_NAME = DataFaker.RuIntoLatin(DataFaker.fakeRUFirstName());
    private final String BIRTHDATE = DataFaker.generateBirthDate();
    private final String COMPANY = DataFaker.getRandomCompanyName();
    private final String POSITION = DataFaker.getRandomCompanyName();

    private final Map<SocialNetworkType, String> userContacts = new HashMap() {
    {
            put(SocialNetworkType.OK, "contact1");
            put(SocialNetworkType.VK, "contact2");
    }
    };
    public OtusTest() {
    }

    @BeforeEach
    public void initDriver() {
        driver = new WebDriverFactory().newDriver();
    }
    @Test
    public void checkOwnDataInOtusAccount() {
        MainPage mainPage = new MainPage(driver);
        if (System.getProperty("authWithCockie", "y").equals("y")) {
            mainPage.openWithAuthorizedUserWithCoockies();
        } else {
            mainPage.open()
                    .logInToTheSite();
        }
        mainPage
                .goToPersonalAccount()
                .fillNameWithLatinName(FIRST_NAME, LATIN_FNAME)
                .fillSurnameWithLatinName(SURNAME, LATIN_SNAME)
                .fillBlogName(BLOG_NAME)
                .fillBirthDate(BIRTHDATE)
                .fillCountry(Countries.RUSSIA)
                .fillCity(RussianCities.MOSCOW)
                .fillEnglishLanguageLevel(LanguageLevel.ADVANCED)
                .fillReadyToRelocation(false)
                .fillWorkFormat(FLEXIBLE_WORK, FULL_TIME, REMOTE_WORK)
                .addCommunicationContact(userContacts)
                .fillGender(UserGender.MALE)
                .fillCompanyAndPosition(COMPANY, POSITION)
                .saveAndReturnAfterTime()
                .close();
        driver = new WebDriverFactory().newDriver();
        mainPage = new MainPage(driver);
        if (System.getProperty("authWithCockie", "y").equals("y")) {
            mainPage.openWithAuthorizedUserWithCoockies();
        } else {
            mainPage.open()
                    .logInToTheSite();
        }
        mainPage.openPersonalAccountWithAuthorizedUserWithCoockies()
                .open()
                .checkCorrectFillingNameAndLatinName(FIRST_NAME, LATIN_FNAME)
                .checkCorrectFillingSurnameAndLatinSurname(SURNAME, LATIN_SNAME)
                .checkCorrectFillingBlogName(BLOG_NAME)
                .checkCorrectFillingBirthDay(BIRTHDATE)
                .checkCorrectFillingCountryAndCity(Countries.RUSSIA.getName(), RussianCities.MOSCOW.getName())
                .checkCorrectFillingEnglishLevel(LanguageLevel.ADVANCED.getName())
                .checkCorrectFillingReadyToRelocate(false)
                .checkCorrectFillingWorkFormat(FLEXIBLE_WORK, FULL_TIME, REMOTE_WORK)
                .checkCorrectFillingCompanyAndPosition(COMPANY, POSITION);
    }
    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
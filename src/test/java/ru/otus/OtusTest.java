package ru.otus;

import data.LanguageLevel;
import data.SocialNetworkType;
import data.UserGender;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.otus.pages.MainPage;
import ru.otus.pages.PersonalAccountProfilePage;
import utils.fakeData.DataFaker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static data.WorkFormat.*;


public class OtusTest {
    private WebDriver driver;
    private final String FIRST_NAME = DataFaker.fakeRUFirstName();
    private final String LATIN_FNAME = DataFaker.RuIntoLatin(FIRST_NAME);
    private final String SURNAME = DataFaker.fakeRUSurnameName();
    private final String LATIN_SNAME = DataFaker.RuIntoLatin(SURNAME);
    private final String BLOG_NAME = DataFaker.RuIntoLatin(DataFaker.fakeRUFirstName());
    private final String BIRTHDATE = DataFaker.generateBirthDate();

    Map<SocialNetworkType, String> userContacts = new HashMap() {
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
        //driver = new ChromeDriver();
    }
    //TODO Добавить открытие браузера на весь экран
    // TODO Можно ли перезайти подменив куку?
    @Test
    public void checkOwnDataInOtusAccount() {
        new MainPage(driver).open()
                .logInToTheSite()
                .goToPersonalAccount()
                .fillNameWithLatinName(FIRST_NAME, LATIN_FNAME)
                .fillSurnameWithLatinName(SURNAME, LATIN_SNAME)
                .fillBlogName(BLOG_NAME)
                .fillBirthDate(BIRTHDATE)
                .fillCountry()
                .fillCity()
                .fillEnglishLanguageLevel(LanguageLevel.ADVANCED)
                .fillReadyToRelocation(false)
                .fillWorkFormat(FLEXIBLE_WORK, FULL_TIME, REMOTE_WORK)
                .addCommunicationContact(userContacts)
                .fillGender(UserGender.MALE)
                .fillCompanyAndPosition()
                .saveAndReturnAfterTime()
                .close();
        driver = new WebDriverFactory().newDriver();
        new MainPage(driver)
                .open()
                .logInToTheSite()
                .goToPersonalAccount();

    }
    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

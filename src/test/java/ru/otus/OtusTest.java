package ru.otus;

import data.UserGender;
import data.WorkFormat;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.otus.pages.MainPage;


public class OtusTest {
    private WebDriver driver;

    @BeforeEach
    public void initDriver() {
        driver = new WebDriverFactory().newDriver();
        //driver = new ChromeDriver();
    }

    @Test
    public void checkOwnDataInOtusAccount() throws InterruptedException {
        new MainPage(driver).open()
                .logInToTheSite()
                .goToPersonalAccount()
                .fillNameWithLatinName()
                .fillSurnameWithLatinName()
                .fillBlogName()
                .fillBirthDate()
                .fillCountry()
                .fillCity()
                .fillEnglishLanguageLevel()
                .fillReadyToRelocation(true)
                .fillWorkFormat(WorkFormat.FLEXIBLE_WORK, WorkFormat.FULL_TIME, WorkFormat.REMOTE_WORK)
                .addCommunicationContact()
                .fillGender(UserGender.MALE)
                .fillCompanyAndPosition()
                ;
    }
    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            //driver.quit();
        }
    }
}

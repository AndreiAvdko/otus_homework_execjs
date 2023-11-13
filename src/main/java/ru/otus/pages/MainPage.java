package ru.otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends AbsBasePage {
    String defaultLogin = "yesakas564@gronasu.com";
    String defaultPassword = "1.yjdsqgfhjkM";
    String inputButtonSelector = "button.sc-mrx253-0";
    String emailInputActivateLocator = "//div[./input[@name='email']]";
    String emailInputSelector = "input[name='email']";
    String passwordInputActivateLocator = "//div[./input[@type='password']]";
    String passwordInputSelector = "input[type='password']";
    String authorizeButtonLocator = "//button[@type='button']//div[text()='Войти']";
    String profileIconLocator = "//section//div[contains(@class, '1og4wiw')][2]";
    String personalAccButtonLocator = "//a[text()='Мой профиль']";

    @Override
    public MainPage open() {
        driver.manage().window().maximize();
        driver.get(BASE_URL + path);
        return this;
    }

    public MainPage(WebDriver driver) {
        super(driver, "/");
    }

    public MainPage logInToTheSite() {
        waiters.waitForCondition(ExpectedConditions.elementToBeClickable($(inputButtonSelector)));
        $(inputButtonSelector).click();
        $x(emailInputActivateLocator).click();
        $(emailInputSelector).sendKeys(System.getProperty("login", defaultLogin));
        $x(passwordInputActivateLocator).click();
        $(passwordInputSelector).sendKeys(System.getProperty("password", defaultPassword));
        $x(authorizeButtonLocator).click();
        if (waiters.waitElementVisible($x(authorizeButtonLocator))) {
            $x(authorizeButtonLocator).click();
        }
        return this;
    }

    public PersonalAccountProfilePage goToPersonalAccount() {
        waiters.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.xpath(profileIconLocator)));
        action.moveToElement($x(profileIconLocator)).perform();
        waiters.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath(personalAccButtonLocator)));
        $x(personalAccButtonLocator).click();
        return new PersonalAccountProfilePage(this.driver);
    }


}

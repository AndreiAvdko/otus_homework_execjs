package ru.otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends AbsBasePage {
    private String defaultLogin = "yesakas564@gronasu.com";
    private String defaultPassword = "1.yjdsqgfhjkM";
    private final String AUTH_TOKEN_COOCKIE = System.getProperty("auth_token", "ygdWNOyUXsmo_8v_v1j7aA");
    private final String AUTH_TOKEN_EXPIRES_COOCKIE = System.getProperty("auth_token_expires", "1794595060");
    private final String OID_COOCKIE = System.getProperty("oid", "873f9abe17eec6c877ccf38fa72141b0");
    private final String SESSIONID_COOCKIE = System.getProperty("sessionid", "tq4j5nj69umhmpjr505q9a1rxtiz542s");
    private final String UTM_COOCKIE = System.getProperty("utm", "utm_source%3Dtelegram%26utm_medium%3Dcpm%26utm_campaign%3Djqa%26utm_content%3Dlesson-01-11-2023%26utm_term%3Dengineers_qa");
    private String inputButtonLocator = "//button[text()='Войти']";
    private String emailInputActivateLocator = "//div[./input[@name='email']]";
    private String emailInputSelector = "input[name='email']";
    private String passwordInputActivateLocator = "//div[./input[@type='password']]";
    private String passwordInputSelector = "input[type='password']";
    private String authorizeButtonLocator = "//button[@type='button']//div[text()='Войти']";
    private String profileIconLocator = "//section//div[contains(@class, '1og4wiw')][2]";
    private String personalAccButtonLocator = "//a[text()='Мой профиль']";

    @Override
    public MainPage open() {
        driver.manage().window().maximize();
        driver.get(BASE_URL + path);
        return this;
    }
    public void setCoockieForAuthorize() {
        driver.manage().addCookie(new Cookie("auth_token", AUTH_TOKEN_COOCKIE));
        driver.manage().addCookie(new Cookie("auth_token_expires", AUTH_TOKEN_EXPIRES_COOCKIE));
        driver.manage().addCookie(new Cookie("oid", OID_COOCKIE));
        driver.manage().addCookie(new Cookie("sessionid", SESSIONID_COOCKIE));
        driver.manage().addCookie(new Cookie("utm", UTM_COOCKIE));
    }
    public MainPage openWithAuthorizedUserWithCoockies() {
        driver.manage().window().maximize();
        driver.get(BASE_URL + path);
        setCoockieForAuthorize();
        driver.navigate().refresh();
        return this;
    }
    public PersonalAccountProfilePage openPersonalAccountWithAuthorizedUserWithCoockies() {
        driver.manage().window().maximize();
        driver.get(BASE_URL + path);
        setCoockieForAuthorize();
        driver.navigate().refresh();
        return new PersonalAccountProfilePage(this.driver);
    }
    public MainPage(WebDriver driver) {
        super(driver, "/");
    }

    public MainPage logInToTheSite() {
        WebElement inputButton = $x(inputButtonLocator);
        waiters.waitForCondition(ExpectedConditions.elementToBeClickable(inputButton));
        inputButton.click();
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

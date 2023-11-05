package ru.otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends AbsBasePage {

    public MainPage(WebDriver driver) {
        super(driver, "/");
    }

    @Override
    public MainPage open() {
        driver.get(BASE_URL + path);
        return this;
    }

    public MainPage logInToTheSite() {
        WebElement inputButton = driver.findElement(By.cssSelector("button.sc-mrx253-0"));
        waiters.waitForCondition(ExpectedConditions.elementToBeClickable(inputButton));
        inputButton.click();

        WebElement emailInputActivate = driver.findElement(By.xpath("//div[./input[@name='email']]"));
        emailInputActivate.click();
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        emailInput.sendKeys("yesakas564@gronasu.com");

        WebElement passwordInputActivate = driver.findElement(By.xpath("//div[./input[@type='password']]"));
        passwordInputActivate.click();
        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.sendKeys("1.yjdsqgfhjkM");

        WebElement authorizeButton = driver.findElement(By.xpath("//button[@type='button']//div[text()='Войти']"));
        authorizeButton.click();
        return this;
    }

    public PersonalAccountProfilePage goToPersonalAccount() {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section//div[contains(@class, '1og4wiw')][2]")));
        WebElement profileIcon = driver.findElement(By.xpath("//section//div[contains(@class, '1og4wiw')][2]"));
        action.moveToElement(profileIcon).perform();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Мой профиль']")));
        WebElement personalAccButton = driver.findElement(By.xpath("//a[text()='Мой профиль']"));
        personalAccButton.click();
        return new PersonalAccountProfilePage(this.driver);
    }


}

package ru.otus;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.fakeData.DataFaker;

import java.time.Duration;
import java.util.List;


public class OtusTest {
    private WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
    }

    /*
    1.  Открыть https://otus.ru
    2.  Авторизоваться на сайте
    3.  Войти в личный кабинет
    4.  В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
    5.  Нажать сохранить
    6.  Открыть https://otus.ru в "чистом браузере"
    7.  Авторизоваться на сайте
    8.  Войти в личный кабинет
    9.  Проверить, что в разделе "О себе" отображаются указанные ранее данные
    */
    @Test
    public void checkOwnDataInOtusAccount() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://otus.ru/");
        WebElement inputButton = driver.findElement(By.cssSelector("button.sc-mrx253-0"));
        inputButton.click();
        WebElement emailInput = driver.findElement(By.cssSelector("input[name='email']"));
        emailInput.sendKeys("yesakas564@gronasu.com");
        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.sendKeys("1.yjdsqgfhjkM");
        WebElement authorizeButton = driver.findElement(By.xpath("//button[@type='button']//div[text()='Войти']"));
        authorizeButton.click();
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section//div[contains(@class, '1og4wiw')][2]")));
        WebElement profileIcon = driver.findElement(By.xpath("//section//div[contains(@class, '1og4wiw')][2]"));
        action.moveToElement(profileIcon).perform();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Мой профиль']")));
        WebElement personalAccButton = driver.findElement(By.xpath("//a[text()='Мой профиль']"));
        personalAccButton.click();

        // Заполняем персональные данные в личном кабинете

        String firstName = DataFaker.fakeRUFirstName();
        String surname = DataFaker.fakeRUSurnameName();
        // Имя
        WebElement nameInput = driver.findElement(By.cssSelector("input[name='fname']"));
        nameInput.clear();
        nameInput.sendKeys(firstName);
        // Имя на латинице
        WebElement nameLatinInput = driver.findElement(By.cssSelector("input[name='fname_latin']"));
        nameLatinInput.clear();
        nameLatinInput.sendKeys(DataFaker.RuIntoLatin(firstName));
        // Фамилия
        WebElement surnameInput = driver.findElement(By.cssSelector("input[name='lname']"));
        surnameInput.clear();
        surnameInput.sendKeys(surname);
        // Фамилия на латинице
        WebElement surnameLatinInput = driver.findElement(By.cssSelector("input[name='lname_latin']"));
        surnameLatinInput.clear();
        surnameLatinInput.sendKeys(DataFaker.RuIntoLatin(surname));
        // Имя в блоге
        WebElement nameInBlog = driver.findElement(By.cssSelector("input[id='id_blog_name']"));
        nameInBlog.clear();
        nameInBlog.sendKeys(DataFaker.RuIntoLatin(DataFaker.RuIntoLatin(firstName)));

        // Дата рождения
        WebElement birthDate = driver.findElement(By.cssSelector("input[name='date_of_birth']"));
        birthDate.sendKeys(DataFaker.generateBirthDate());
        birthDate.sendKeys(Keys.ENTER);

        // Ввод страны
        WebElement countryInput = driver.findElement(By.xpath("//label[./input[@name='country']]/div"));
        countryInput.click();
        // Получаем список стран
        // TODO Добавить ожидание появления всплывашки
        List<WebElement> countryList = driver.findElements(By.cssSelector("div[class*='select-scroll_country'] > button"));
        countryList.get(DataFaker.getRandomInDuration(countryList.size())).click();

        //Ввод города
        WebElement cityInput = driver.findElement(By.xpath("//label[./input[@name='city']]/div"));
        cityInput.click();
        // Получаем список стран
        // TODO Добавить ожидание появления всплывашки
        List<WebElement> cityList = driver.findElements(By.cssSelector("div[class*='select-scroll_city'] > button"));
        cityList.get(DataFaker.getRandomInDuration(cityList.size())).click();

    }


    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

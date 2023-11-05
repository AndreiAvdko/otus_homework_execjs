package ru.otus;

import factory.WebDriverFactory;
import jdk.jfr.internal.tool.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import utils.fakeData.DataFaker;
import utils.waiters.Waiters;

import java.time.Duration;
import java.util.List;


public class OtusTest {
    private WebDriver driver;

    @BeforeEach
    public void initDriver() {
        driver = new WebDriverFactory().newDriver();
        //driver = new ChromeDriver();
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

        // new Waiters(driver).waitForCondition(ExpectedConditions.elementToBeClickable(By.cssSelector("button.sc-mrx253-0")));
        WebElement inputButton = driver.findElement(By.cssSelector("button.sc-mrx253-0"));
        new Waiters(driver).waitForCondition(ExpectedConditions.elementToBeClickable(inputButton));
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
        List<WebElement> countryList = driver.findElements(By.cssSelector("div[class*='select-scroll_country'] > button"));
        countryList.get(DataFaker.getRandomInDuration(countryList.size())).click();
        new Waiters(driver).waitForCondition(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[class*='select-scroll_country']"))));


        //Ввод города
        WebElement cityInput = driver.findElement(By.xpath("//label[./input[@name='city']]/div"));
        cityInput.click();
        // Получаем список городов
        // TODO Добавить ожидание появления всплывашки
        List<WebElement> cityList = driver.findElements(By.cssSelector("div[class*='select-scroll_city'] > button"));
        cityList.get(DataFaker.getRandomInDuration(cityList.size())).click();
        new Waiters(driver).waitForCondition(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[class*='select-scroll_city']"))));


        // Ввод уровня языка и выбор из списка
        WebElement languageLevelInput = driver.findElement(By.xpath("//label[./input[@name='english_level']]"));
        languageLevelInput.click();
        List<WebElement> languageList = driver.findElements(By.xpath("//label[./input[@name='english_level']]/following-sibling::div/div/button"));
        languageList.get(DataFaker.getRandomInDuration(languageList.size())).click();


        // Выбор готовности к переезду
        WebElement readyToRelocateRadiButton = driver.findElement(By.xpath("//input[@name='ready_to_relocate' and @value='True']"));
        WebElement noReadyToRelocateRadiButton = driver.findElement(By.xpath("//input[@name='ready_to_relocate' and @value='False']"));
        /*
        for(int i = 0; i < 10; i++) {
            System.out.println("Готов к переезду - " + readyToRelocateRadiButton.isSelected());
            System.out.println("Не готов к переезду - " + noReadyToRelocateRadiButton.isSelected());
        }
        */

        // Выбор формата работы
        WebElement fullTimeCheckbox = driver.findElement(By.xpath("//label[./span[contains(text(), 'Полный день')]]"));
        WebElement flexibleWorkCheckbox = driver.findElement(By.xpath("//label[./span[contains(text(), 'Гибкий график')]]"));
        WebElement remoteWorkCheckbox = driver.findElement(By.xpath("//label[./span[contains(text(), 'Удаленно')]]"));
        fullTimeCheckbox.click();
        flexibleWorkCheckbox.click();
        remoteWorkCheckbox.click();

        //Добавление контактной информации
        WebElement addContactButton = driver.findElement(By.xpath("//div[./p[text()='Контактная информация']]/descendant::button[text()='Добавить']"));
        addContactButton.click();
        WebElement chooseCommunicationMethod = driver.findElement(By.xpath("//label[./div/span[text()='Способ связи']]"));
        chooseCommunicationMethod.click();

        List<WebElement> communicationTypeList = driver.findElements(By.xpath("//div[./button[contains(text(), 'Способ связи')]]/button"));
        communicationTypeList.get(DataFaker.getRandomInDuration(communicationTypeList.size())).click();

        WebElement textCommunicationMethodInput = driver.findElement(By.xpath("//div[./div/label[./div/span[text()='Способ связи']]]/input"));
        textCommunicationMethodInput.sendKeys("blablabla");

        // Выбрать пол
        WebElement genderSelector = driver.findElement(By.cssSelector("select[name='gender']"));
        genderSelector.click();
        List<WebElement> genderType = driver.findElements(By.xpath("//select[@name='gender']/option"));
        genderType.get(DataFaker.getRandomInDuration(genderType.size())).click();


        // Указать компанию
        WebElement companyNameInput = driver.findElement(By.cssSelector("input[name='company']"));
        companyNameInput.click();
        companyNameInput.sendKeys(DataFaker.getRandomCompanyName());


        // Указать должность
        WebElement positionNameInput = driver.findElement(By.cssSelector("input[name='work']"));
        positionNameInput.click();
        positionNameInput.sendKeys(DataFaker.getRandomCompanyName());

    }

    @Test
    public void mainpageTest() {
        new MainPage(driver).open();
    }


    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

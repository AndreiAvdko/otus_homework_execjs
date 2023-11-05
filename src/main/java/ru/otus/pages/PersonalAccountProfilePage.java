package ru.otus.pages;

import data.UserGender;
import data.WorkFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.fakeData.DataFaker;
import utils.waiters.Waiters;

import java.util.List;

import static data.UserGender.*;

public class PersonalAccountProfilePage extends AbsBasePage {
    public PersonalAccountProfilePage(WebDriver driver) {
        super(driver, "/lk/biography/personal/");
    }

    public PersonalAccountProfilePage fillNameWithLatinName() {
        String firstName = DataFaker.fakeRUFirstName();
        // Имя
        WebElement nameInput = driver.findElement(By.cssSelector("input[name='fname']"));
        nameInput.clear();
        nameInput.sendKeys(firstName);
        // Имя на латинице
        WebElement nameLatinInput = driver.findElement(By.cssSelector("input[name='fname_latin']"));
        nameLatinInput.clear();
        nameLatinInput.sendKeys(DataFaker.RuIntoLatin(firstName));
        return this;
    }

    public PersonalAccountProfilePage fillSurnameWithLatinName() {
        String surname = DataFaker.fakeRUSurnameName();
        // Фамилия
        WebElement surnameInput = driver.findElement(By.cssSelector("input[name='lname']"));
        surnameInput.clear();
        surnameInput.sendKeys(surname);
        // Фамилия на латинице
        WebElement surnameLatinInput = driver.findElement(By.cssSelector("input[name='lname_latin']"));
        surnameLatinInput.clear();
        surnameLatinInput.sendKeys(DataFaker.RuIntoLatin(surname));
        return this;
    }

    public PersonalAccountProfilePage fillBlogName() {
        // Имя в блоге
        WebElement nameInBlog = driver.findElement(By.cssSelector("input[id='id_blog_name']"));
        nameInBlog.clear();
        nameInBlog.sendKeys(DataFaker.RuIntoLatin(DataFaker.fakeRUFirstName()));
        return this;
    }

    public PersonalAccountProfilePage fillBirthDate() {
        // Дата рождения
        WebElement birthDate = driver.findElement(By.cssSelector("input[name='date_of_birth']"));
        birthDate.sendKeys(DataFaker.generateBirthDate());
        birthDate.sendKeys(Keys.ENTER);
        return this;
    }

    public PersonalAccountProfilePage fillCountry() {
        // Ввод страны
        WebElement countryInput = driver.findElement(By.xpath("//label[./input[@name='country']]/div"));
        countryInput.click();
        // Получаем список стран
        List<WebElement> countryList = driver.findElements(By.cssSelector("div[class*='select-scroll_country'] > button"));
        countryList.get(DataFaker.getRandomInDuration(countryList.size())).click();
        new Waiters(driver).waitForCondition(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[class*='select-scroll_country']"))));
        return this;
    }

    public PersonalAccountProfilePage fillCity() {
        //Ввод города
        WebElement cityInput = driver.findElement(By.xpath("//label[./input[@name='city']]/div"));
        cityInput.click();
        // Получаем список городов
        // TODO Добавить ожидание появления всплывашки
        List<WebElement> cityList = driver.findElements(By.cssSelector("div[class*='select-scroll_city'] > button"));
        cityList.get(DataFaker.getRandomInDuration(cityList.size())).click();
        new Waiters(driver).waitForCondition(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[class*='select-scroll_city']"))));
        return this;
    }

    public PersonalAccountProfilePage fillEnglishLanguageLevel() {
        // Ввод уровня языка и выбор из списка
        WebElement languageLevelInput = driver.findElement(By.xpath("//label[./input[@name='english_level']]"));
        languageLevelInput.click();
        List<WebElement> languageList = driver.findElements(By.xpath("//label[./input[@name='english_level']]/following-sibling::div/div/button"));
        languageList.get(DataFaker.getRandomInDuration(languageList.size())).click();
        return this;
    }

    public PersonalAccountProfilePage fillReadyToRelocation(boolean readyToRelocation) {
        // Выбор готовности к переезду
        WebElement readyToRelocateRadiButton = driver.findElement(By.xpath("//input[@name='ready_to_relocate' and @value='True']"));
        WebElement noReadyToRelocateRadiButton = driver.findElement(By.xpath("//input[@name='ready_to_relocate' and @value='False']"));

        if (readyToRelocation) {
            readyToRelocateRadiButton.click();
        } else {
            noReadyToRelocateRadiButton.click();
        }
        return this;
    }

    public PersonalAccountProfilePage fillWorkFormat(WorkFormat ... formats) {
        // Выбор формата работы
        WebElement fullTimeCheckbox = driver.findElement(By.xpath("//label[./span[contains(text(), 'Полный день')]]"));
        WebElement flexibleWorkCheckbox = driver.findElement(By.xpath("//label[./span[contains(text(), 'Гибкий график')]]"));
        WebElement remoteWorkCheckbox = driver.findElement(By.xpath("//label[./span[contains(text(), 'Удаленно')]]"));
        for (WorkFormat format : formats) {
            switch (format) {
                case FULL_TIME: fullTimeCheckbox.click();
                case FLEXIBLE_WORK: flexibleWorkCheckbox.click();
                case REMOTE_WORK: remoteWorkCheckbox.click();
            }
        }
        return this;
    }

    public PersonalAccountProfilePage addCommunicationContact() {
        //Добавление контактной информации
        WebElement addContactButton = driver.findElement(By.xpath("//div[./p[text()='Контактная информация']]/descendant::button[text()='Добавить']"));
        addContactButton.click();
        WebElement chooseCommunicationMethod = driver.findElement(By.xpath("//label[./div/span[text()='Способ связи']]"));
        chooseCommunicationMethod.click();

        List<WebElement> communicationTypeList = driver.findElements(By.xpath("//div[./button[contains(text(), 'Способ связи')]]/button"));
        communicationTypeList.get(DataFaker.getRandomInDuration(communicationTypeList.size())).click();

        WebElement textCommunicationMethodInput = driver.findElement(By.xpath("//div[./div/label[./div/span[text()='Способ связи']]]/input"));
        textCommunicationMethodInput.sendKeys("blablabla");
        return this;
    }

    public PersonalAccountProfilePage fillGender(UserGender gender) {
        // Выбрать пол
        switch (gender) {
            case MALE: driver.findElement(By.cssSelector("//select[@name='gender']/option[text()='Мужской']")).click();
            case FEMALE: driver.findElement(By.cssSelector("//select[@name='gender']/option[text()='Женский']"));
            case NO_SELECT: driver.findElement(By.cssSelector("//select[@name='gender']/option[text()='Не указано']"));
        }
        return this;
    }

    public PersonalAccountProfilePage fillCompanyAndPosition() {
        // Указать компанию
        WebElement companyNameInput = driver.findElement(By.cssSelector("input[name='company']"));
        companyNameInput.click();
        companyNameInput.sendKeys(DataFaker.getRandomCompanyName());

        // Указать должность
        WebElement positionNameInput = driver.findElement(By.cssSelector("input[name='work']"));
        positionNameInput.click();
        positionNameInput.sendKeys(DataFaker.getRandomCompanyName());
        return this;
    }
}

package ru.otus.pages;

import data.*;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.fakeData.DataFaker;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;


public class PersonalAccountProfilePage extends AbsBasePage {
    private String nameInputSelector = "input[name='fname']";
    private String nameLatinInputSelector = "input[name='fname_latin']";
    private String surnameInputSelector = "input[name='lname']";
    private String surnameLatinInputSelector = "input[name='lname_latin']";
    private String nameInBlogSelector = "input[id='id_blog_name']";
    private String birthDateSelector = "input[name='date_of_birth']";
    private String countryInputLocator = "//label[./input[@name='country']]/div";
    private String countryBlockSelector = "div[class*='select-scroll_country']";
    private String countryButtonTemplateLocator = "//div[contains(@class, 'select-scroll_country')]/button[contains(text(), '%s')]";
    private String cityInputLocator = "//label[./input[@name='city']]/div"; // //label[./input[@name='city']]/div
    private String cityBlockSelector = "div[class*='select-scroll_city']";
    private String cityButtonTemlateLocator = "//div[contains(@class, 'select-scroll_city')]/button[contains(text(), '%s')]";
    private String languageLevelInputLocator = "//label[./input[@name='english_level']]";
    private String languageLevelListLocator = "//label[./input[@name='english_level']]/following-sibling::div/div/button";
    private String readyToRelocateRadiButTemplateLocator = "//label[./input[@name='ready_to_relocate' and @value='%s']]";
    private String workFormatCheckboxTemplateSelector = "input[title='%s']";
    private String workFormatTemplateLocator = "//label[./span[contains(text(), '%s')]]";
    private String maleGenderLocator = "//select[@name='gender']/option[text()='Мужской']";
    private String femaleGenderLocator = "//select[@name='gender']/option[text()='Женский']";
    private String noChooseGenderLocator = "//select[@name='gender']/option[text()='Не указано']";
    private String companyNameInputSelector = "input[name='company']";
    private String positionNameInputSelector = "input[name='work']";
    private String communicateMethodInputTemplateSelector =  "input[name='contact-%s-value']";
    private String communicateTypeMessangerButtonTemlateLocator = "//div[./input[@name='contact-%s-value']]/div/div/div/button";
    private String openMessengerListButtonTemplateLocator = "//div[./input[@name='contact-%s-value']]/div";
    private String addCommunicateMethodButtonLocator = "//div[./p[text()='Контактная информация']]/descendant::button[text()='Добавить']";
    private String savePageChangesButtonSelector = "button[title='Сохранить и заполнить позже']";
    public PersonalAccountProfilePage(WebDriver driver) {
        super(driver, "/lk/biography/personal/");
    }
    @Override
    public PersonalAccountProfilePage open() {
        driver.manage().window().maximize();
        driver.get(BASE_URL + path);
        return this;
    }
    public PersonalAccountProfilePage fillNameWithLatinName(String firstName, String latinName) {
        $(nameInputSelector).clear();
        $(nameInputSelector).sendKeys(firstName);
        $(nameLatinInputSelector).clear();
        $(nameLatinInputSelector).sendKeys(latinName);
        return this;
    }

    public PersonalAccountProfilePage fillSurnameWithLatinName(String surname, String latinSurname) {
        $(surnameInputSelector).clear();
        $(surnameInputSelector).sendKeys(surname);
        $(surnameLatinInputSelector).clear();
        $(surnameLatinInputSelector).sendKeys(latinSurname);
        return this;
    }

    public PersonalAccountProfilePage fillBlogName(String blogName) {

        $(nameInBlogSelector).clear();
        $(nameInBlogSelector).sendKeys(blogName);
        return this;
    }

    public PersonalAccountProfilePage fillBirthDate(String birthDate) {
        WebElement birthDateInput = $(birthDateSelector);
        birthDateInput.clear();
        birthDateInput.sendKeys(birthDate);
        birthDateInput.sendKeys(Keys.ENTER);
        return this;
    }

    public PersonalAccountProfilePage fillCountry(Countries country) {
        $x(countryInputLocator).click();
        $x(String.format(countryButtonTemplateLocator, country.getName())).click();
        waiters.waitForCondition(ExpectedConditions.invisibilityOf($(countryBlockSelector)));
        return this;
    }

    public PersonalAccountProfilePage fillCity(RussianCities city) {
        WebElement cityListOpenButton = $x(cityInputLocator);
        waiters.waitForCondition(ExpectedConditions.elementToBeClickable(cityListOpenButton));
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.printf(e.toString());
        }
        $x("//input[@name= 'city']/following::div[1]").click();
        $x(String.format(cityButtonTemlateLocator, city.getName())).click();
        waiters.waitForCondition(ExpectedConditions.invisibilityOf($(cityBlockSelector)));
        return this;
    }

    public PersonalAccountProfilePage fillEnglishLanguageLevel(LanguageLevel languageLevel) {
        $x(languageLevelInputLocator).click();
        List<WebElement> languageLevelList = $$x(languageLevelListLocator);
        languageLevelList
                .stream()
                .filter(button -> button.getText().contains(languageLevel.getName()))
                .findFirst()
                .get()
                .click();
        return this;
    }

    public PersonalAccountProfilePage fillReadyToRelocation(boolean readyToRelocation) {
        String readyForRelocate = "" + readyToRelocation;
        readyForRelocate = readyForRelocate.substring(0, 1).toUpperCase() + readyForRelocate.substring(1);
        $x(String.format(readyToRelocateRadiButTemplateLocator, readyForRelocate)).click();
        return this;
    }

    public PersonalAccountProfilePage fillWorkFormat(WorkFormat ... formats) {
        for (WorkFormat format : formats) {
            switch (format) {
                case FULL_TIME: {
                    if (!($(String.format(workFormatCheckboxTemplateSelector, "Полный день")).isSelected())) {
                        $x(String.format(workFormatTemplateLocator, "Полный день")).click();
                    }
                }
                case FLEXIBLE_WORK: {
                    if (!($(String.format(workFormatCheckboxTemplateSelector, "Гибкий график")).isSelected())) {
                        $x(String.format(workFormatTemplateLocator, "Гибкий график")).click();
                    }
                }
                case REMOTE_WORK: {
                    if (!($(String.format(workFormatCheckboxTemplateSelector, "Удаленно")).isSelected())) {
                        $x(String.format(workFormatTemplateLocator, "Удаленно")).click();
                    }
                }
            }
        }
        return this;
    }

    public PersonalAccountProfilePage addCommunicationContact(Map<SocialNetworkType, String> userContact) {
        int counter = 0;
        for ( SocialNetworkType messenger : userContact.keySet()) {
            $x(String.format(openMessengerListButtonTemplateLocator, counter)).click();
            $$x(String.format(communicateTypeMessangerButtonTemlateLocator, counter))
                    .stream()
                    .filter(button -> button.getText().contains(messenger.getName()))
                    .findFirst()
                    .get()
                    .click();

            WebElement userContactInput = $(String.format(communicateMethodInputTemplateSelector, counter));
            userContactInput.clear();
            userContactInput.sendKeys(userContact.get(messenger));
            $x(addCommunicateMethodButtonLocator).click();
            counter++;
        }
        return this;
    }

    public PersonalAccountProfilePage fillGender(UserGender gender) {
        $x("//select[@name='gender']").click();
        switch (gender) {
            case MALE: $x(maleGenderLocator).click();
            case FEMALE: $x(femaleGenderLocator).click();;
            case NO_SELECT: $x(noChooseGenderLocator).click();
        }
        return this;
    }

    public PersonalAccountProfilePage fillCompanyAndPosition(String company, String position) {
        WebElement companyNameInput = $(companyNameInputSelector);
        companyNameInput.click();
        companyNameInput.clear();
        companyNameInput.sendKeys(company);
        WebElement positionNameInput = $(positionNameInputSelector);
        positionNameInput.click();
        positionNameInput.clear();
        positionNameInput.sendKeys(position);
        return this;
    }

    public PersonalAccountProfilePage saveAndReturnAfterTime() {
        $(savePageChangesButtonSelector).click();
        return this;
    }


    public PersonalAccountProfilePage checkCorrectFillingNameAndLatinName(String name, String latinName) {
        assertThat($(nameInputSelector).getAttribute("value")).isEqualTo(name);
        assertThat($(nameLatinInputSelector).getAttribute("value")).isEqualTo(latinName);
        return this;
    }

    public PersonalAccountProfilePage checkCorrectFillingSurnameAndLatinSurname(String surname, String latinSurname) {
        assertThat($(surnameInputSelector).getAttribute("value")).isEqualTo(surname);
        assertThat($(surnameLatinInputSelector).getAttribute("value")).isEqualTo(latinSurname);
        return this;
    }

    public PersonalAccountProfilePage checkCorrectFillingBlogName(String blogName) {
        assertThat($(nameInBlogSelector).getAttribute("value")).isEqualTo(blogName);
        return this;
    }

    public PersonalAccountProfilePage checkCorrectFillingBirthDay(String birthDay) {
        assertThat($(birthDateSelector).getAttribute("value")).isEqualTo(birthDay);
        return this;
    }

    public PersonalAccountProfilePage checkCorrectFillingCountryAndCity(String country, String city) {
        assertThat($x(String.format(countryButtonTemplateLocator, country)).getAttribute("title")).isEqualTo(country);
        assertThat($x(String.format(cityButtonTemlateLocator, city)).getAttribute("title")).isEqualTo(city);
        return this;
    }

    public PersonalAccountProfilePage checkCorrectFillingEnglishLevel(String engLevel) {
        assertThat($x(languageLevelInputLocator).getText()).isEqualTo(engLevel);
        return this;
    }

    public PersonalAccountProfilePage checkCorrectFillingReadyToRelocate(boolean ready) {
        assertThat($x("//label[./input[@name='ready_to_relocate']]").isSelected()).isEqualTo(ready);
        return this;
    }
    public PersonalAccountProfilePage checkCorrectFillingWorkFormat(WorkFormat ... formats) {
        for (WorkFormat format : formats) {
            switch (format) {
                case FULL_TIME: {
                    assertThat(($(String.format(workFormatCheckboxTemplateSelector, "Полный день")).isSelected())).isTrue();
                }
                case FLEXIBLE_WORK: {
                    assertThat(($(String.format(workFormatCheckboxTemplateSelector, "Гибкий график")).isSelected())).isTrue();
                }
                case REMOTE_WORK: {
                    assertThat(($(String.format(workFormatCheckboxTemplateSelector, "Удаленно")).isSelected())).isTrue();
                }
            }
        }
        return this;
    }

    public PersonalAccountProfilePage checkCorrectFillingCompanyAndPosition(String company, String position) {
        assertThat($(companyNameInputSelector).getAttribute("value")).isEqualTo(company);
        assertThat($(positionNameInputSelector).getAttribute("value")).isEqualTo(position);
        return this;
    }
}

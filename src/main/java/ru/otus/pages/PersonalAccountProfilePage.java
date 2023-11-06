package ru.otus.pages;

import data.SocialNetworkType;
import data.LanguageLevel;
import data.UserGender;
import data.WorkFormat;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.fakeData.DataFaker;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PersonalAccountProfilePage extends AbsBasePage {
    String nameInputSelector = "input[name='fname']";
    String nameLatinInputSelector = "input[name='fname_latin']";
    String surnameInputSelector = "input[name='lname']";
    String surnameLatinInputSelector = "input[name='lname_latin']";
    String nameInBlogSelector = "input[id='id_blog_name']";
    String birthDateSelector = "input[name='date_of_birth']";
    String countryInputLocator = "//label[./input[@name='country']]/div";
    String countryBlockSelector = "div[class*='select-scroll_country']";
    String countryListSelector = "div[class*='select-scroll_country'] > button";
    String cityInputLocator = "//label[./input[@name='city']]/div";
    String cityBlockSelector = "div[class*='select-scroll_city']";
    String cityListSelector = "div[class*='select-scroll_city'] > button";
    String languageLevelInputLocator = "//label[./input[@name='english_level']]";
    String languageLevelListLocator = "//label[./input[@name='english_level']]/following-sibling::div/div/button";
    String readyToRelocateRadiButTemplateLocator = "//label[./input[@name='ready_to_relocate' and @value='%s']]";
    String workFormatCheckboxTemplateSelector = "input[title='%s']";
    String workFormatTemplateLocator = "//label[./span[contains(text(), '%s')]]";
    String maleGenderLocator = "//select[@name='gender']/option[text()='Мужской']";
    String femaleGenderLocator = "//select[@name='gender']/option[text()='Женский']";
    String noChooseGenderLocator = "//select[@name='gender']/option[text()='Не указано']";
    String companyNameInputSelector = "input[name='company']";
    String positionNameInputSelector = "input[name='work']";
    String communicateMethodInputTemplateSelector =  "input[name='contact-%s-value']";
    String communicateTypeMessangerButtonTemlateLocator = "//div[./input[@name='contact-%s-value']]/div/div/div/button";
    String openMessengerListButtonTemplateLocator = "//div[./input[@name='contact-%s-value']]/div";
    String addCommunicateMethodButtonLocator = "//div[./p[text()='Контактная информация']]/descendant::button[text()='Добавить']";
    String savePageChangesButtonSelector = "button[title='Сохранить и заполнить позже']";
    public PersonalAccountProfilePage(WebDriver driver) {
        super(driver, "/lk/biography/personal/");
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

    public PersonalAccountProfilePage fillCountry() {
        $x(countryInputLocator).click();
        List<WebElement> countryElementList = $$(countryListSelector);
        countryElementList.get(DataFaker.getRandomInDuration(countryElementList.size())).click();
        waiters.waitForCondition(ExpectedConditions.invisibilityOf($(countryBlockSelector)));
        return this;
    }

    public PersonalAccountProfilePage fillCity() {
        $x(cityInputLocator).click();
        List<WebElement> cityElementList = $$(cityListSelector);
        waiters.waitForCondition(ExpectedConditions.visibilityOf($(cityBlockSelector)));
        cityElementList.get(DataFaker.getRandomInDuration(cityElementList.size()-1)).click();
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

    public PersonalAccountProfilePage fillCompanyAndPosition() {
        WebElement companyNameInput = $(companyNameInputSelector);
        companyNameInput.click();
        companyNameInput.clear();
        companyNameInput.sendKeys(DataFaker.getRandomCompanyName());
        WebElement positionNameInput = $(positionNameInputSelector);
        positionNameInput.click();
        positionNameInput.clear();
        positionNameInput.sendKeys(DataFaker.getRandomCompanyName());
        return this;
    }

    public PersonalAccountProfilePage saveAndReturnAfterTime() {
        $(savePageChangesButtonSelector).click();
        return this;
    }

}

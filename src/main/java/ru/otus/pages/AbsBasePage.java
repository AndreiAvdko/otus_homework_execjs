package ru.otus.pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

import java.util.Set;

public abstract class AbsBasePage extends AbsPageObject {
    public String BASE_URL = System.getProperty("base.url", "https://otus.ru");
    public String path = "";
    public AbsBasePage(WebDriver driver, String path) {
        super(driver);
        this.path = path;
    }

    public AbsBasePage open() {
        driver.get(BASE_URL + path);
        return this;
    }
    public void close() {
        this.driver.close();
    }
}

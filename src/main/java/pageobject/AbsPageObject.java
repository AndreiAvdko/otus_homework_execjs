package pageobject;

import org.openqa.selenium.WebDriver;

public abstract class AbsPageObject {
    protected WebDriver driver;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
    }
}

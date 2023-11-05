package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.waiters.Waiters;

public abstract class AbsPageObject {
    protected WebDriver driver;
    protected Waiters waiters;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement $(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public WebElement $x(String xPathLocator) {
        return driver.findElement(By.xpath(xPathLocator));
    }


}

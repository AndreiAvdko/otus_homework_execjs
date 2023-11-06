package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.waiters.Waiters;

import java.util.List;

public abstract class AbsPageObject {
    protected WebDriver driver;
    protected Waiters waiters;

    protected Actions action;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.waiters = new Waiters(driver);
        action = new Actions(driver);
    }
    public WebElement $(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public List<WebElement> $$(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector));
    }
    public WebElement $x(String xPathLocator) {
        return driver.findElement(By.xpath(xPathLocator));
    }

    public List<WebElement> $$x(String xPathLocator) {
        return driver.findElements(By.xpath(xPathLocator));
    }



}

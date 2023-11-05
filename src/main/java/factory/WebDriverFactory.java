package factory;

import data.Browsers;
import exceptions.BrowserNotSupportedException;
import factory.impl.ChromeDriverOptions;
import factory.impl.IBrowserOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Locale;

public class WebDriverFactory {
    private String BROWSER_NAME = System.getProperty("browser.name", "chrome");

    public WebDriver newDriver(){
        Browsers userBrowser = null;
        for (Browsers browser: Browsers.values()) {
            if (browser.getName().equals(BROWSER_NAME.trim().toLowerCase(Locale.ROOT))) {
                userBrowser = Browsers.valueOf(BROWSER_NAME.toUpperCase());
                break;
            }
        }

        if (userBrowser == null) {
            throw new BrowserNotSupportedException(BROWSER_NAME);
        }
        switch (userBrowser) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                IBrowserOptions options = new ChromeDriverOptions();
                return new ChromeDriver((ChromeOptions) options.getOptions());
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default: {
                return null;
            }
        }
    }
}

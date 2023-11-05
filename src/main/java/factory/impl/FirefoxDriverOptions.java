package factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverOptions implements IBrowserOptions {

    @Override
    public MutableCapabilities getOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--homepage=about:blank");
        firefoxOptions.addArguments("--ingnore-certificate-errors");
        return firefoxOptions;
    }
}

package factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverOptions implements IBrowserOptions {
    @Override
    public MutableCapabilities getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--homepage=about:blank");
        chromeOptions.addArguments("--ingnore-certificate-errors");
        return chromeOptions;
    }
}

package factory.impl;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverOptions implements IBrowserOptions {
    @Override
    public MutableCapabilities getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--homepage=about:blank");
        chromeOptions.addArguments("--ingnore-certificate-errors");
        chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Mobile Safari/537.36");
        return chromeOptions;
    }
}

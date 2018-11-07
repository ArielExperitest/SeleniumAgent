package SingleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ChromeExtension {
    private RemoteWebDriver driver;
    private DesiredCapabilities dc;

    @Before
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addExtensions(new File("ChromeExtension/extension_35_4_5_0.crx"));//36 MB
//        chromeOptions.addExtensions(new File("ChromeExtension/extension_7_7_0_0.crx"));//3 MB

        dc = new DesiredCapabilities();
        dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        dc.setCapability("testName", "Chrome Extension");
        dc.setCapability("generateReport", true);
        dc.setCapability("username", "ariel");
        dc.setCapability("password", "Experitest2012");
        dc.setCapability("projectName", "Default");
    }

    @Test
    public void name() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("https://qacloud.experitest.com/wd/hub"), dc);
        driver.get("chrome://extensions/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

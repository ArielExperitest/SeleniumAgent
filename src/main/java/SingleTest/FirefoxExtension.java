package SingleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.List;

public class FirefoxExtension {
    RemoteWebDriver driver;

    /*
    https://stackoverflow.com/questions/48036897/how-selenium-webdriver-can-enable-the-firefox-addons
    https://stackoverflow.com/questions/8070867/how-do-you-use-a-firefox-plugin-within-a-selenium-webdriver-program-written-in-j
     */
    @Before
    public void setUp() throws Exception {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(new File("ChromeExtension/Visual-Testing_v1.20.crx"));


        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        dc.setCapability("testName", "Chrome Extension");
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        dc.setCapability("generateReport", true);

        driver = new RemoteWebDriver(new URL("https://qacloud.experitest.com/wd/hub"), dc);
        List<String> strings = driver.manage().ime().getAvailableEngines();
        System.out.println(strings);
    }

    @Test
    public void name() {

    }

    @After
    public void tearDown() throws Exception {

    }
}

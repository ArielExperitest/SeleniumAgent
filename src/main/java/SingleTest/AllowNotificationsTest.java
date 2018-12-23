package SingleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AllowNotificationsTest {
    protected URL url;
    protected RemoteWebDriver driver;
    protected DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() {
        try {
            url = new URL("https://qacloud.experitest.com/wd/hub");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        dc.setCapability("username", "ariel");
        dc.setCapability("password", "Experitest2012");

        dc.setCapability("newSessionWaitTimeout", 300);//default is 300
        dc.setCapability("newCommandTimeout", 300);//default is 300

    }

    @Test
    public void chrome() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();
        contentSettings.put("notifications", 2);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-plugins");
        options.addArguments("--start-maximized");
        dc.setCapability("browser", "Chrome");
        dc.setCapability("browser_version", "49.0");
        dc.setCapability("os", "Windows");
        dc.setCapability("os_version", "10");
        dc.setCapability("resolution", "1280x1024");
        dc.setCapability("browserstack.debug", "true");
//        dc.setCapability(ChromeOptions.CAPABILITY, options);
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);

        driver = new RemoteWebDriver(url, dc);
    }

    @Test
    public void firefox() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("dom.webnotifications.enabled", false);
        dc.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);

        driver = new RemoteWebDriver(url, dc);
    }

    @Test
    public void microsoftEdge() {
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.EDGE);
        driver = new RemoteWebDriver(url, dc);
    }

    @After
    public void tearDown() throws Exception {
        driver.get("http://jsbin.testim.io/ses/3");

        Thread.sleep(10_000);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        BufferedImage bufferedImage = ImageIO.read(scrFile);
        ImageIO.write(bufferedImage, "png", new File("reports/images/screenshot_" + System.currentTimeMillis() + ".png"));
        driver.quit();
    }
}

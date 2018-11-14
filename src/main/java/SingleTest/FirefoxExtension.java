package SingleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import javax.swing.plaf.TableHeaderUI;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FirefoxExtension {
    private RemoteWebDriver driver;
    private DesiredCapabilities dc = new DesiredCapabilities();

    /*
    https://stackoverflow.com/questions/48036897/how-selenium-webdriver-can-enable-the-firefox-addons
    https://stackoverflow.com/questions/8070867/how-do-you-use-a-firefox-plugin-within-a-selenium-webdriver-program-written-in-j
     */
    @Before
    public void setUp()  {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(new File("C:\\Users\\ariel.hazan\\Downloads\\Firefox Extension\\midnight_lizard-10.1.4-an+fx.xpi"));
//        firefoxProfile.setPreference("extensions.firebug.onByDefault", true);

        dc.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        dc.setCapability("testName", "Firefox Extension");
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        dc.setCapability("generateReport", true);
        dc.setCapability("username", "ariel");
        dc.setCapability("password", "Experitest2012");
        dc.setCapability("projectName", "Default");
        dc.setCapability(CapabilityType.BROWSER_VERSION, "63.0.1");
    }

    @Test
    public void name() throws IOException, InterruptedException {
        driver = new RemoteWebDriver(new URL("https://qacloud.experitest.com/wd/hub"), dc);
        System.out.println((String) driver.getCapabilities().getCapability("reportUrl"));
        driver.get("about:addons");
        Thread.sleep(2000);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        BufferedImage bufferedImage = ImageIO.read(scrFile);
        ImageIO.write(bufferedImage, "png", new File("reports/images/screenshot_" + System.currentTimeMillis() + ".png"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


/**
 * Created by ariel.hazan on 11-Feb-18.
 */
public class Basic extends TestBase {
    DesiredCapabilities desiredCapabilities = null;

    public Basic(String browserType, DesiredCapabilities desiredCapabilities) {
        super(desiredCapabilities);
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        try {
            driver = new RemoteWebDriver(url, dc);
            driver.get("https://www.google.com");
        } finally {
            System.out.println("--------------" + dc);
        }
    }
}

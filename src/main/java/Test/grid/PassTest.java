package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;


public class PassTest extends TestBase {

    public PassTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        driver = new RemoteWebDriver(url, dc);
        initProperty();

        if (!browserType.equals(BrowserType.IE)) {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        }
        driver.get("https://www.google.co.il");
//        driver.get("https://www.ynet.co.il");
    }
}

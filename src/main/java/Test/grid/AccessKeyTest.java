package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by ariel.hazan on 10-Dec-17
 */
public class AccessKeyTest extends TestBase {

    public AccessKeyTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
        USE_ACCESS_KEY = true;
    }


    @Override
    public void test() {
        driver = new RemoteWebDriver(url, dc);
        platform = String.valueOf(driver.getCapabilities().getPlatform());
        reportUrl = (String) driver.getCapabilities().getCapability("reportUrl");
        driver.get("https://www.ynet.co.il");
    }

}

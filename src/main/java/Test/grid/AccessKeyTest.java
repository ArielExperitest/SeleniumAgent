package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by ariel.hazan on 10-Dec-17
 */
public class AccessKeyTest extends TestBase {

    public AccessKeyTest(String browserType) {
        this.browserName = browserType;
        testName = this.getClass().getSimpleName() + " " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
        USE_ACCESS_KEY = true;
    }


    @Override
    public void test() {
        driver.get("https://www.ynet.co.il");
    }

}

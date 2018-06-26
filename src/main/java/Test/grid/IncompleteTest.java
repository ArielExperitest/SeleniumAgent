package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;


public class IncompleteTest extends TestBase {

    public IncompleteTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        dc.setCapability("newSessionWaitTimeout", 1);
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://www.ynet.co.il");
    }
}

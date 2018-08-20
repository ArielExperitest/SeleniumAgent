package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by ariel.hazan on 11-Feb-18.
 */
public class Basic extends TestBase {

    public Basic(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
//        dc.setCapability("testName", testName);
//        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://mail.google.com/mail/?tab=wm");
    }

}

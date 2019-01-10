package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;


/**
 * Created by ariel.hazan on 11-Feb-18.
 */
public class BasicGoogleTest extends TestBase {

    public BasicGoogleTest(String browserType) {
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    public BasicGoogleTest() {
    }

    @Override
    public void test() {
        driver.get("https://www.ynet.co.il");
        System.out.println((String) driver.getCapabilities().getCapability("viewUrl"));
//        driver.getTitle();
        try {
            Thread.sleep(5 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

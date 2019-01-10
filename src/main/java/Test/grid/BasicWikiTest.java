package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;


/**
 * Created by ariel.hazan on 11-Feb-18.
 */
public class BasicWikiTest extends TestBase {

    public BasicWikiTest(String browserType) {
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        System.out.println((String) driver.getCapabilities().getCapability("viewUrl"));
        driver.get("https://en.wikipedia.org/wiki/Special:Random");
//        driver.getTitle();
        try {
            Thread.sleep(7 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

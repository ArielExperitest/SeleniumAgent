package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;


/**
 * Created by ariel.hazan on 11-Feb-18.
 */
public class BingTest extends TestBase {

    public BingTest(String browserType) {
        testName = this.getClass().getSimpleName() + " " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        int i = 0;
        while (i < 30 * 3) {//6 mini
            driver.get("https://www.bing.com/search?q=" + i);
            sleep();
            i++;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

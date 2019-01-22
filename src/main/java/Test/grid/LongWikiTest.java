package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;


/**
 * Created by ariel.hazan on 11-Feb-18.
 */
public class LongWikiTest extends TestBase {

    public LongWikiTest(String browserType) {
        testName = this.getClass().getSimpleName() + " " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() throws InterruptedException {
        int i = 0;
        while (i < 30 * 4) {//6 mini
            driver.get("https://en.wikipedia.org/wiki/Special:Random");
            Thread.sleep(2_000);
            i++;
        }
    }
}

package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;

public class LongTest extends TestBase {

    public LongTest(String browserType) {
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    protected void test() throws Exception {
        int i = 0;
        while (i < 1000) {
            driver.get("https://www.google.com");
            Thread.sleep(4_000);
            i++;
        }
    }
}

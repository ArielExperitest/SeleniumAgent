package Test;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PassTest extends TestBase {

    public PassTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        startTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));

        driver = new RemoteWebDriver(url, dc);
        driver.get("https://www.ynet.co.il");

//            System.out.println(driver.getCapabilities().getCapability("reportUrl"));
        platformName = (String) dc.getCapability("platformName");
    }
}

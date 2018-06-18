package Test;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ariel.hazan on 10-Dec-17
 */
public class AccessKeyTest extends TestBase {

    public AccessKeyTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
        USE_AK_Flag = true;
    }


    @Override
    public void test() {
        startTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://www.ynet.co.il");
        driver.get("https://www.google.co.il");
        driver.get("https://www.cnn.com");

    }

}

package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;


public class PassTest extends TestBase {

    public PassTest(String browserType) throws InterruptedException {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() throws InterruptedException {

        System.out.println("starting.. " + driver.getCapabilities().getBrowserName() + " " + driver.getCapabilities().getVersion());
        driver.get("https://www.google.co.il");
        //        driver.get("https://www.ynet.co.il");
        Thread.sleep(2 * 60 * 1000);
    }
}

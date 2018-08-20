package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FailTest extends TestBase {

    public FailTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        driver = new RemoteWebDriver(url, dc);
        platform = String.valueOf(driver.getCapabilities().getPlatform());
        reportUrl = (String) driver.getCapabilities().getCapability("reportUrl");
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("Go To Fail Test!!!"));
    }
}

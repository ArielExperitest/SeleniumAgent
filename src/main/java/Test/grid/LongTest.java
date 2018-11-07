package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LongTest extends TestBase {

    public LongTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    protected void test() throws Exception {
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://duckduckgo.com/");
        sleep(1000);
        for (int i = 0; i < 30; i++) {
            WebElement webElement = driver.findElement(By.xpath("//*[@id=\"search_form_input_homepage\"]"));
            webElement.sendKeys("" + i);
            webElement.click();
            log.info("Interaction #" + i);
        }
driver.quit();
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://duckduckgo.com/");
        sleep(1000);
        for (int i = 0; i < 30; i++) {
            WebElement webElement = driver.findElement(By.xpath("//*[@id=\"search_form_input_homepage\"]"));
            webElement.sendKeys("" + i);
            webElement.click();
            log.info("Interaction #" + i);
        }
    }


}

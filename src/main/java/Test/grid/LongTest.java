package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;

import static FrameWork.Credentials.*;

public class LongTest extends TestBase {

    public LongTest(String browserType) {
        testName = this.getClass().getSimpleName() + " " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    protected void test() throws Exception {
        loginTOCloud();
        int i = 0;
        while (i < 1000) {
            driver.navigate().refresh();
            Thread.sleep(4_000);
            i++;
        }
    }

    private void loginTOCloud() {
        driver.get("https://qacloud.experitest.com");
        WebElement username = driver.findElement(By.xpath("//*[@name=\"username\"]"));
        username.sendKeys(USER);
        driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys(PASS);
        driver.findElement(By.xpath("//*[@name=\"login\"]")).click();
        //Firefox open a new tab when try to click on log in button
        if (driver.getWindowHandles().size() > 1) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
            driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys(Keys.ENTER);
        }
    }
}

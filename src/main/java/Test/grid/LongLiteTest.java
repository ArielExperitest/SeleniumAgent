package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;


/**
 * Created by ariel.hazan on 11-Feb-18.
 */
public class LongLiteTest extends TestBase {

    public LongLiteTest(String browserType) {
        testName = this.getClass().getSimpleName() + " " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        int i = 0;
        driver.get("http://192.168.2.119:8000/");
        while (i < 30 * 4) {//6 mini
            driver.findElement(By.xpath("//*[@href=\"Documents/\"]")).click();
            driver.navigate().back();
            driver.findElement(By.xpath("//*[@href=\"Downloads/\"]")).click();
            driver.navigate().back();
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

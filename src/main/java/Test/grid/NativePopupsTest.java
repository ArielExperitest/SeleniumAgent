package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;

public class NativePopupsTest extends TestBase {

    public NativePopupsTest(String browserType) {
        testName = this.getClass().getSimpleName() + " " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
        dc.setCapability("closePopups", "Windows Security::OK");
//        dc.setCapability("closePopups", new String[]{"Windows Security::OK", "TitleRegex::Button"});
    }

        @Override
    protected void test() throws Exception {
        driver.get("http://infoworks.rbc.com");
        Thread.sleep(5_000);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).getText();
    }
//    @Override
//    protected void test() throws Exception {
//        driver.get("https://www.google.co.il");
//        driver.findElement(By.xpath("//*[@name=\"q\"]"));
//    }
}

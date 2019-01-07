package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.LocalFileDetector;

public class UploadFile extends TestBase {

    public UploadFile(String browserType) {
        this.browserName = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        driver.setFileDetector(new LocalFileDetector());
        driver.get("http://the-internet.testim.io:7080/upload");
        WebElement input = driver.findElement(By.id("file-upload"));
        WebElement submit = driver.findElement(By.id("file-submit"));
        input.sendKeys("C:\\IntelljiProject\\SeleniumAgent\\src\\main\\java\\Utils\\simple.txt");
        submit.click();
    }

}

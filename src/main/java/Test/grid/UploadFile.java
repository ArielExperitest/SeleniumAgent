package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.LocalFileDetector;

public class UploadFile extends TestBase {

    public UploadFile(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        System.out.println("1");
        driver.setFileDetector(new LocalFileDetector());
        System.out.println("1.1");
        driver.get("http://the-internet.testim.io:7080/upload");
        System.out.println("1.2");
        WebElement input = driver.findElement(By.id("file-upload"));
        System.out.println("1.3");
        WebElement submit = driver.findElement(By.id("file-submit"));
        System.out.println("1.3");
        input.sendKeys("C:\\IntellijProjects\\SeleniumAgent\\src\\main\\java\\Utils\\simple.txt");
        System.out.println("1.4");
//        input.sendKeys("C:\\IntellijProjects\\SeleniumAgent\\reports\\images\\screenshot_internet explorer_1530186298876.png");
        submit.click();
    }

}

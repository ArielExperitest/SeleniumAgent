package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;

import java.util.List;

public class TimeTest extends TestBase {

    public TimeTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    protected void test() {

        int i = 0;

        driver.get("http://the-internet.herokuapp.com");
        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[5]/a")).click();

        while (i < 3) {

            List<WebElement> isChecked = driver.findElements(By.xpath("//*[@checked]"));
            if (isChecked.size() > 0) {
                driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click();
            } else {
                driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
            }
            i++;
        }
        driver.navigate().back();
        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[9]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"dropdown\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[2]")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[16]/a")).click();
        JavascriptExecutor jse = driver;
        jse.executeScript("scroll(0, 500);"); //Down
        jse.executeScript("scroll(0, 250);"); //Down
        jse.executeScript("scroll(0, -250);");//Up
        jse.executeScript("scroll(0, -600);");//Up
        driver.get("https://www.google.com");
        WebElement searchBar = driver.findElement(By.id("lst-ib"));
        searchBar.click();

        searchBar.sendKeys("Jerusalem wiki");

        driver.quit();
    }

}


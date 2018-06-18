package Test;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * Created by ariel.hazan on 28-Nov-17
 */
public class SpeedTestGenerateReport extends TestBase {


    public SpeedTestGenerateReport(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    protected void test() {
        dc.setCapability("generateReport", true);
        driver = new RemoteWebDriver(url, dc);
        long startTime = System.nanoTime();
        cloudTest();
        long endTime = System.nanoTime();
        long slow = (endTime - startTime);


        dc.setCapability("generateReport", false);
        driver = new RemoteWebDriver(url, dc);
        startTime = System.nanoTime();
        cloudTest();
        endTime = System.nanoTime();
        long fast = (endTime - startTime);
        driver.quit();
        if (fast > slow) {
            System.out.println(">>>> Speed Generate Report Test on " + browserType + " has failed.");

        }
    }

    private void cloudTest() {

        driver.get("http://192.168.1.64");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys("ariel");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Experitest2012");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='login']")).click();
        sleep(3000);
        while (driver.getCurrentUrl().contains("/index.html#/login")) {
            driver.findElement(By.xpath("//*[@name='login']")).click();
            sleep(3000);
        }
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]")).click();
        sleep(1500);
        int i = 0;
        while (i < 10) {
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]")).click();
            sleep(1500);
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();
            sleep(1500);
            i++;
        }

    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

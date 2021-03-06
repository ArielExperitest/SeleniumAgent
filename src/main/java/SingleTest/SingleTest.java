package SingleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SingleTest {
    protected URL url;
    protected RemoteWebDriver driver;
    protected DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() {
        try {
            url = new URL("https://qacloud.experitest.com:443/wd/hub");
//            url = new URL("http://192.168.2.173/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        dc.setCapability("username", "ariel");
        dc.setCapability("password", "Experitest2012");
        dc.setCapability("projectName", "default");
//        desiredCapabilities.setCapability("platform", Platform.MAC);

//        dc.setCapability("newSessionWaitTimeout", 30);//default is 300
        dc.setCapability("newCommandTimeout", 20);//default is 300
//        dc.setCapability(CapabilityType.BROWSER_NAME, "firefox");
//        dc.setCapability("bbb", "firefox");

//        dc.setCapability(CapabilityType.BROWSEzR_VERSION, "52.6.0");

    }


    @Test
    public void testExperitest() {
        driver = new RemoteWebDriver(url, dc);
        System.out.println((String) driver.getCapabilities().getCapability("reportUrl"));
        System.out.println(driver.getCapabilities());
//
//        driver.get("about:addons");
//        driver.findElement(By.xpath("//*[@id=\"category-extension\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"addon-list\"]")).click();
//        driver.findElement(By.xpath("//*[@label=\"Disable\"]")).click();
//        List<WebElement> webElements = driver.findElements(By.xpath("//*[@class=\"content-container\"]"));
//        webElements.get(0).findElement(By.xpath("//*[@label=\"Disable\"]")).click();
//        driver.get("about:support");
//        Thread.sleep(10_000);
    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }
}

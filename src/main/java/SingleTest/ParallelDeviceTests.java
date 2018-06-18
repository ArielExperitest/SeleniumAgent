package SingleTest;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by ariel.hazan on 04-Dec-17
 */
public class ParallelDeviceTests {


    protected DesiredCapabilities dc = new DesiredCapabilities();
    protected URL url;
    protected RemoteWebDriver driver;

    String USER = "admin";//TODO
    String PASS = "Experitest2012";//TODO
    String projectName = "default";//TODO
    String HOST = "192.168.2.91";//TODO
    String PORT = "80";//TODO
    boolean isSecure = false;//TODO

    int numOfAndroid =2;
    int numOfIOS = 2;


    @Before
    public void run() {
        dc.setCapability("username", USER);
        dc.setCapability("password", PASS);
        dc.setCapability("projectName", projectName);

        dc.setCapability("testName", "Ariel Test");

        try {
            url = new URL("http://" + HOST + ":" + PORT + "/wd/hub");
            if (isSecure)
                url = new URL("https://" + HOST + ":" + PORT + "/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test() {
        //Android
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.ANDROID);
        int i = 0;
        while (i < numOfAndroid) {
            i++;
            new Thread(new arielTest());
        }
        //iOS
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IPHONE);
        i = 0;
        while (i < numOfIOS) {
            i++;
            new Thread(new arielTest());
        }

    }

    public static class arielTest extends ParallelDeviceTests implements Runnable {
        @Override
        public void run() {
            try {
                driver = new RemoteWebDriver(url, dc);
                driver.get("https://www.google.com");
                Thread.sleep(8000);
                WebElement searchBar = driver.findElement(By.id("lst-ib"));
                searchBar.click();
                Thread.sleep(5000);
                searchBar.sendKeys("Jerusalem wiki");
                searchBar.sendKeys(Keys.RETURN);
//            driver.findElement(By.xpath("//*[@id=\"hplogo\"]")).click();
                Thread.sleep(5000);
                driver.findElement(By.xpath("//*[@href=\"https://en.wikipedia.org/wiki/Jerusalem\"]")).click();
                Thread.sleep(5000);

                driver.findElement(By.xpath("//*[contains(text(), 'History of Jerusalem')]")).click();
                Thread.sleep(5000);
                driver.getCurrentUrl();
                driver.getTitle();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (driver != null)
                    driver.quit();
            }
        }
    }
}

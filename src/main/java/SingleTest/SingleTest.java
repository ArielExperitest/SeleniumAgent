package SingleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;


public class SingleTest {
    private static final long TIMEOUT = 2000;
    private final String accessKey = "eyJ4cC51IjozMywieHAucCI6MTYsInhwLm0iOiJNVFV4TkRNNE9UWTBOVGM0TVEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4MzUxNjg2MjAsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.RxaEruBiGdr62faBbzDs3D2AKvreVksNneHM6Kecapw";

    protected URL url;
    protected RemoteWebDriver driver;
    protected DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() {

        try {
            url = new URL("https://qacloud.experitest.com/wd/hub");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        dc.setCapability("username", "ariel");
        dc.setCapability("password", "Experitest2012");

        dc.setCapability("newSessionWaitTimeout", 300);//default is 300
        dc.setCapability("newCommandTimeout", 300);//default is 300
//        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.EDGE);

    }


    @Test
    public void testExperitest() throws InterruptedException {
        driver = new RemoteWebDriver(url, dc);
        System.out.println((String) driver.getCapabilities().getCapability("reportUrl"));
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("aaaaaa"));

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    protected void setAK() {
        try {
            url = new URL("https://:" + accessKey + "@192.168.2.91:80/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

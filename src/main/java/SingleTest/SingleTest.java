package SingleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


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

        //        dc.setCapability("accessKey", accessKey);

        dc.setCapability("deviceQuery", "os='ios'");//default is 300
        dc.setCapability("newSessionWaitTimeout", "60");//default is 300
        dc.setCapability("newCommandTimeout", "30");//default is 300

        driver = new RemoteWebDriver(url, dc);
        System.out.println(driver.getCapabilities().getCapability("reportUrl"));
    }


    @Test
    public void testExperitest() {

        driver.get("https://lnx-candyland.pcfdev.atb.atb.com");
        System.out.println((String) driver.getCapabilities().getCapability("reportUrl"));
    }

    @After
    public void tearDown() throws Exception {
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

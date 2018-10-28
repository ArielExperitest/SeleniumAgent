package SingleTest;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class SingleTest {
    private static final long TIMEOUT = 2000;
    private final String accessKey = "eyJ4cC51IjozMywieHAucCI6MTYsInhwLm0iOiJNVFV4TkRNNE9UWTBOVGM0TVEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4MzUxNjg2MjAsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.RxaEruBiGdr62faBbzDs3D2AKvreVksNneHM6Kecapw";
    private boolean SECURE = false;

    protected URL url;
    protected RemoteWebDriver driver;
    protected DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() {

        try {
//            url = new URL("https://qacloud.experitest.com/wd/hub");
            url = new URL("http://192.168.1.59:8090/wd/hub");
//            url = new URL("https://ariel-mac.experitest.local/wd/hub");
//                url = new URL("https://192.168.2.91:443");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        dc.setCapability("testName", "FireFox Tes111111111111111t");
        dc.setCapability("username", "admin");
        dc.setCapability("password", "Experitest2012");
//        dc.setCapability("projectName", "Test@ariel.com"); //only required if your user has several projects assigned to it. Otherwise, exclude this capability.
        dc.setCapability("projectName", "Default"); //only required if your user has several projects assigned to it. Otherwise, exclude this capability.
//        dc.setCapability("accessKey", accessKey);
        dc.setCapability("newSessionWaitTimeout", "600");//default is 300


        driver = new RemoteWebDriver(url, dc);
        System.out.println(driver.getCapabilities().getCapability("reportUrl"));
    }

    @Test
    public void test1() {
        driver.get("https://lnx-candyland.pcfdev.atb.atb.com");
        try {
            presenceOfElementLocated(By.id("lst-ib"));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        driver.get("https://www.ynet.co.il");
//        driver.get("https://www.experitest.com");
//        driver.get("https://www.cnn.com");

        driver.quit();
    }

    protected void setAK() {
        try {
            url = new URL("http://:" + accessKey + "@192.168.2.91:80/wd/hub");
            if (SECURE)
                url = new URL("https://:" + accessKey + "@192.168.2.91:80/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

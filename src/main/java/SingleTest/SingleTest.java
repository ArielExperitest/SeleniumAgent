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


public class SingleTest {
    private static final long TIMEOUT = 2000;
    private final String accessKey = "eyJ4cC51IjozMywieHAucCI6MTYsInhwLm0iOiJNVFV4TkRNNE9UWTBOVGM0TVEiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE4MzUxNjg2MjAsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.RxaEruBiGdr62faBbzDs3D2AKvreVksNneHM6Kecapw";
    private boolean SECURE = false;

    protected URL url;
    protected WebDriver driver;
    protected DesiredCapabilities dc = new DesiredCapabilities();

//    static {
//        System.getProperties().setProperty("javax.net.ssl.trustStore","C:\\Program Files (x86)\\Experitest\\Ariel_Mac_Keystore.jks");
//        System.getProperties().setProperty("javax.net.ssl.trustStorePassword","");
//
//    }


    //    @Before
    public void setUp() {

        try {
            url = new URL("http://192.168.2.108:4444/wd/hub");
//            url = new URL("https://ariel-mac.experitest.local/wd/hub");
            if (SECURE)
                url = new URL("https://192.168.2.91:443");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        dc.setCapability("testName", "FireFox Tes111111111111111t");
//        dc.setCapability("username", "ariel-user");
//        dc.setCapability("username", "admin");
//        dc.setCapability("password", "Experitest2012");
//        dc.setCapability("projectName", "Test@ariel.com"); //only required if your user has several projects assigned to it. Otherwise, exclude this capability.
//        dc.setCapability("projectName", "Default"); //only required if your user has several projects assigned to it. Otherwise, exclude this capability.
//        dc.setCapability("projectName", "Default"); //only required if your user has several projects assigned to it. Otherwise, exclude this capability.
//        dc.setCapability("accessKey", accessKey);


//        dc.setCapability("generateReport", true);
//        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);
//        dc.setCapability(CapabilityType.BROWSER_VERSION, "11");

        driver = new RemoteWebDriver(url, dc);
    }

    @Test
    public void test1() {

//        driver.get("https://www.ynet.co.il");
//        driver.get("https://www.experitest.com");
//        driver.get("https://www.cnn.com");
        driver.get("https://www.google.com");
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    //@Test
    public void LoginToCloud() {
        driver.get("http://192.168.1.64");
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys("ariel");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Experitest2012");
        driver.findElement(By.xpath("//*[@name='login']")).click();
        driver.findElement(By.xpath("//*[@href='#/launchpad']")).click();
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();
        int i = 0;
        while (i < 20) {
            driver.findElement(By.xpath("//*[@class='icon icon-smartphone']")).click();
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();
            i++;
        }

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

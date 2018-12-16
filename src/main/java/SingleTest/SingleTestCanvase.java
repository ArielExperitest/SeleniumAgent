package SingleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class SingleTestCanvase {
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
//        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; return performance;";

        driver = new RemoteWebDriver(url, dc);
        System.out.println((String) driver.getCapabilities().getCapability("reportUrl"));

        driver.get("https://qacloud.experitest.com/index.html#/browsers");
        Thread.sleep(30_000);
        String currentWindowHandle = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {
            //if it contains the current window we want to eliminate that from switchTo();
            if (!window.equals(currentWindowHandle)) {
                driver.switchTo().window(window);
                System.out.println("-----------------------------------");
                System.out.println(driver.manage().getCookies());
                System.out.println("-----------------------------------");
                System.out.println(driver.getPageSource());
                System.out.println("-----------------------------------");
                String netData = ((JavascriptExecutor) driver).executeScript(scriptToExecute).toString();
                System.out.println(netData);
                driver.findElement(By.xpath("//*[@id=\"screenCanvas\"]")).click();
                System.out.println("-----------------------------------");
                netData = ((JavascriptExecutor) driver).executeScript(scriptToExecute).toString();
                System.out.println(netData);

            }
        }

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

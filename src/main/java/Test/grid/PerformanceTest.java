package Test.grid;

import FrameWork.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by ariel.hazan on 02-Jan-18.
 */
public class PerformanceTest extends TestBase {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    public PerformanceTest(String browserType, String browserVersion) {
        super(null);
        config(browserType);
    }


    public PerformanceTest(String browserType) {
        config(browserType);
    }

    public PerformanceTest(String browserType, DesiredCapabilities desiredCapabilities) {
        super(desiredCapabilities);
        config(browserType);
    }


    private void config(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);

    }

    @Override
    protected void test() {

        driver = new RemoteWebDriver(url, dc);

        try {
            driver.manage().window().maximize();
        } catch (Exception e) {
            log.info("Fail to resize window.");
        }
        sleep(1000);
        browserVersion = driver.getCapabilities().getVersion();
        platform = String.valueOf(driver.getCapabilities().getPlatform());
        reportUrl = (String) driver.getCapabilities().getCapability("reportUrl");
        sessionId = (String) driver.getCapabilities().getCapability("sessionId");
        log.info("driver= " + platform + " - " + sessionId + " - " + reportUrl + " - " + driver.getCapabilities().getVersion() + " - " + driver.getCapabilities());

        if (!browserType.equals(BrowserType.IE)) {
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        }
        sleepSafari(5 * 1000);
        driver.get("https://qacloud.experitest.com");
        sleep(3 * 1000);

        WebElement username = driver.findElement(By.xpath("//*[@name=\"username\"]"));
        username.sendKeys("ariel");
        driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys("Experitest2012");
        driver.findElement(By.xpath("//*[@name=\"login\"]")).click();
        sleep(2 * 1000);
        //Firefox open a new tab when try to click on log in button
        if (driver.getWindowHandles().size() > 1 || !driver.getCurrentUrl().contains("qacloud.experitest.com")) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
            driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys(Keys.ENTER);

        }
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]")).click();
        int i = 0;
        while (i < 5) {
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]")).click();
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();
            i++;
        }
        //Wikipedia
        sleepSafari(5 * 1000);
        driver.get("https://en.wikipedia.org/wiki/Special:Random");

        sleep(2 * 1000);
        String currentUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("Experitest");

        int j = 0;
        sleepSafari(5 * 1000);
        driver.get("http://the-internet.herokuapp.com");
        sleep(2 * 1000);
        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[5]/a")).click();
        sleep(2 * 1000);
        while (j < 3) {
            if (driver.findElements(By.xpath("//*[@checked]")).size() > 0) {
                driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click();
            } else {
                driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
            }
            j++;
        }
        if (!browserType.equals(BrowserType.SAFARI)) {
            driver.navigate().back();
            driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[9]/a")).click();
            sleepSafari(5 * 1000);
            driver.get("http://the-internet.herokuapp.com/dropdown");
            WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"dropdown\"]"));
            dropdown.click();
            dropdown.findElement(By.xpath("//*[@id=\"dropdown\"]/option[2]")).click();
            driver.navigate().back();
            driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[16]/a")).click();
            JavascriptExecutor jse = driver;
            jse.executeScript("scroll(0, 500);"); //Down
            jse.executeScript("scroll(0, 250);"); //Down
            jse.executeScript("scroll(0, -250);");//Up
            jse.executeScript("scroll(0, -600);");//Up
        }
        sleepSafari(5 * 1000);
        sleepSafari(5 * 1000);
        driver.get("https://www.google.com");
        sleep(2 * 1000);
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"lst-ib\"]"));
        searchBar.click();
        searchBar.sendKeys("Jerusalem wiki");

        //uploadFile

        sleepSafari(5 * 1000);
//            driver.get("http://www.csm-testcenter.org/test?do=show&subdo=common&test=file_upload");
//            driver.findElement(By.xpath("//*[@type=\"file\"]")).sendKeys("C:\\SeleniumAgent\\SeleniumAgent\\Selenium\\Example_File.txt");
//            driver.findElement(By.xpath("//*[@name=\"http_submit\"]")).click();

        //Basic Operations
        driver.getPageSource();
        driver.getTitle();
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();
        driver.navigate().to(currentUrl);
        try {
            driver.navigate().to(new URL(currentUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        driver.manage().window().setSize(new Dimension(500, 500));
//        driver.manage().window().maximize();
//        driver.manage().window().setSize(new Dimension(500, 500));
//        driver.manage().window().fullscreen();
//        driver.manage().window().getSize();
        //------- Add 11.6.18 --------------
        driver.manage().deleteAllCookies();

        //How to get HPROF file :)
//        if (!browserType.equals(BrowserType.SAFARI)) {
//            for (String logTypes :
//                    driver.manage().logs().getAvailableLogTypes()) {
//        sleepSafari(25* 1000);
//                driver.manage().logs().get(logTypes);
//            }
//        }
        driver.getTitle();
    }
}

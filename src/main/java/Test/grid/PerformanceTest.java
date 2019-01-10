package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ariel.hazan on 02-Jan-18.
 */
public class PerformanceTest extends TestBase {
    public PerformanceTest(String browserType) {
        testName = this.getClass().getSimpleName() + " " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    public PerformanceTest() {
    }

    @Override
    protected void test() {
        driver.get("https://qacloud.experitest.com");
        WebElement username = myFindElement(By.xpath("//*[@name=\"username\"]"));
//        WebElement username = myFindElement(By.xpath("//*[@name=\"username\"]"));
        username.sendKeys("ariel");
        myFindElement(By.xpath("//*[@name=\"password\"]")).sendKeys("Experitest2012");
        myFindElement(By.xpath("//*[@name=\"login\"]")).click();
        //Firefox open a new tab when try to click on log in button
        if (platform.equals(BrowserType.FIREFOX) &&
                (driver.getWindowHandles().size() > 1 ||
                        !driver.getCurrentUrl().contains("qacloud.experitest.com"))) {
            myFindElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
            myFindElement(By.xpath("//*[@name=\"password\"]")).sendKeys(Keys.ENTER);
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"side-menu\"]/li[5]"))).click();
//        myFindElement(By.xpath("//*[@id=\"side-menu\"]/li[5]")).click();
        int i = 0;
        while (i < 5) {
            myFindElement(By.xpath("//*[@id=\"side-menu\"]/li[2]")).click();
            myFindElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();
            i++;
        }

        //Wikipedia
        driver.get("https://en.wikipedia.org/wiki/Special:Random");
        String currentUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchInput\"]"))).sendKeys("Experitest");

        int j = 0;
        driver.get("http://the-internet.herokuapp.com");
        myFindElement(By.xpath("//*[@id=\"content\"]/ul/li[5]/a")).click();
        while (j < 3) {
            if (driver.findElements(By.xpath("//*[@checked]")).size() > 0) {
                myFindElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click();
            } else {
                myFindElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
            }
            j++;
        }
        if (!browserName.equals(BrowserType.SAFARI)) {
            driver.navigate().back();
            myFindElement(By.xpath("//*[@id=\"content\"]/ul/li[9]/a")).click();
            driver.get("http://the-internet.herokuapp.com/dropdown");
            WebElement dropdown = myFindElement(By.xpath("//*[@id=\"dropdown\"]"));
            dropdown.click();
            myFindElement(By.xpath("//*[@id=\"dropdown\"]/option[2]")).click();
            driver.navigate().back();
            myFindElement(By.xpath("//*[@id=\"content\"]/ul/li[16]/a")).click();
            JavascriptExecutor jse = driver;
            jse.executeScript("scroll(0, 500);"); //Down
            jse.executeScript("scroll(0, 250);"); //Down
            jse.executeScript("scroll(0, -250);");//Up
            jse.executeScript("scroll(0, -600);");//Up
        }

        driver.get("https://www.google.com");
        WebElement searchBar = myFindElement(By.xpath("//*[@name=\"q\"]"));
        searchBar.click();
        driver.getPageSource();
        searchBar.sendKeys("Jerusalem wiki");
        searchBar.sendKeys(Keys.ENTER);

        //BasicGoogleTest Operations
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

        //uploadFile
        driver.setFileDetector(new LocalFileDetector());
        driver.get("http://the-internet.herokuapp.com/upload");
        WebElement input = myFindElement(By.id("file-upload"));
        WebElement submit = myFindElement(By.id("file-submit"));
        input.sendKeys("C:\\IntelljiProject\\SeleniumAgent\\src\\main\\java\\Utils\\simple.txt");
        submit.click();

//        driver.manage().window().setSize(new Dimension(500, 500));
//        driver.manage().window().maximize();
//        driver.manage().window().setSize(new Dimension(500, 500));
//        driver.manage().window().fullscreen();
//        driver.manage().window().getSize();
        //------- Add 11.6.18 --------------
        driver.manage().deleteAllCookies();

        //How to get HPROF file :)
//        if (!browserName.equals(BrowserType.SAFARI)) {
//            for (String logTypes :
//                    driver.manage().logs().getAvailableLogTypes()) {
//                driver.manage().logs().get(logTypes);
//            }
//        }
    }
}

package Test.grid;

import FrameWork.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Random;

public class WatchCloudTest extends TestBase {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private static final String INDEX_HTML = "/index.html#/";
    private String cloudUrl, cloudUser, cloudPassword;
    private String[] cloudPages;

    public WatchCloudTest(String browserType, String cloudUrl, String cloudUser, String cloudPassword) {
        cloudPages = new String[]{"dashboard", "users", "testrequests", "applications", "projects"};
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " on " + browserType;
        dc.setCapability("testName", testName);
        this.cloudUrl = cloudUrl;
        this.cloudUser = cloudUser;
        this.cloudPassword = cloudPassword;
    }

    @Override
    public void test() {
        driver = new RemoteWebDriver(url, dc);
        loginTOCloud();
        int randomIndex = new Random().nextInt(cloudPages.length);
        int i = 0;
        driver.get(cloudUrl + INDEX_HTML + cloudPages[randomIndex]);
        log.info("Go to " + cloudPages[randomIndex] + " page");
        while (i < 5000) {
            i++;
            driver.findElement(By.xpath("//*[@id=\"hourglass\"]")).click();
            log.info("Refresh " + i + " times");
        }
    }

    private void loginTOCloud() {
        log.info("Start to login to Cloud");
        driver.get(cloudUrl);
        WebElement username = driver.findElement(By.xpath("//*[@name=\"username\"]"));
        username.sendKeys(cloudUser);
        driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys(cloudPassword);
        driver.findElement(By.xpath("//*[@name=\"login\"]")).click();
        //Firefox open a new tab when try to click on log in button
        if (driver.getWindowHandles().size() > 1) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
            driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys(Keys.ENTER);
        }
        log.info("Finish to login to Cloud");
    }
}

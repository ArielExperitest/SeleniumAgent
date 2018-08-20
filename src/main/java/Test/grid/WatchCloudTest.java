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
    private String cloudUrl = "https://qa-win2016.experitest.com", cloudUser, cloudPassword;
    private String[] cloudPages;

    public WatchCloudTest(String browserType, String cloudUser, String cloudPassword) {
        cloudPages = new String[]{"dashboard", "users", "testrequests", "applications", "projects"};
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " on " + browserType;
        dc.setCapability("testName", testName);

        this.cloudUser = cloudUser;
        this.cloudPassword = cloudPassword;
    }


    @Override
    public void test() {
        driver = new RemoteWebDriver(url, dc);
        sleep(3 * 1000);
        loginTOCloud();

        int randomIndex = new Random().nextInt(cloudPages.length);
        int i = 0;
        driver.get(cloudUrl + INDEX_HTML + cloudPages[randomIndex]);
        log.info("Go to " + cloudPages[randomIndex] + " page");
        while (i < 100) {
            sleep(7 * 1000);
            driver.navigate().refresh();
            i++;
            log.info("Refresh " + i + " times");
        }
    }

    private void loginTOCloud() {
        driver.get(cloudUrl);
        sleep(3 * 1000);
        WebElement username = driver.findElement(By.xpath("//*[@name=\"username\"]"));
        username.sendKeys(cloudUser);
        driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys(cloudPassword);
        driver.findElement(By.xpath("//*[@name=\"login\"]")).click();
        sleep(3 * 1000);
        //Firefox open a new tab when try to click on log in button
        if (driver.getWindowHandles().size() > 1 || !driver.getCurrentUrl().contains("qa-win2016.experitest.com")) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.TAB);
            driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys(Keys.ENTER);
        }
        log.info("Finish to login to Cloud");
    }
}

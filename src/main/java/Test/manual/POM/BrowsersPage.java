package Test.manual.POM;

import Test.manual.POM.Cards.ChromeCard;
import Test.manual.POM.Cards.FirefoxCard;
import Test.manual.POM.Cards.IECard;
import Test.manual.POM.Cards.SafariCard;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class BrowsersPage {
    protected RemoteWebDriver driver;
    private static final String HTTPS_QACLOUD_EXPERITEST_COM = "https://qacloud.experitest.com";
    private static final String HTTPS_QACLOUD_EXPERITEST_COM_BROWSERS = "https://qacloud.experitest.com/index.html#/browsers";
    private static final String USERNAME_XPATH = "//*[@name=\"username\"]";
    private static final String PASSWORD_XPATH = "//*[@name=\"password\"]";
    private static final String LOGIN_XPATH = "//*[@name=\"login\"]";

    public BrowsersPage(RemoteWebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contains("browsers"))
            loginToCloudGoTOBrowsers();
    }

    private void loginToCloudGoTOBrowsers() {
        driver.manage().window().maximize();
        sleep(500);
        driver.get(HTTPS_QACLOUD_EXPERITEST_COM);
        sleep(500);
        driver.findElement(By.xpath(USERNAME_XPATH)).sendKeys("ariel");
        sleep(500);
        driver.findElement(By.xpath(PASSWORD_XPATH)).sendKeys("Experitest2012");
        sleep(500);
        driver.findElement(By.xpath(LOGIN_XPATH)).click();
        sleep(2000);
//        driver.get(HTTPS_QACLOUD_EXPERITEST_COM_BROWSERS);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();

        System.out.println("--Finish constructor-----");
    }

    public ChromeCard getChromeCard() {
        return new ChromeCard(driver);
    }

    public FirefoxCard getFirefoxCard() {
        return new FirefoxCard(driver);
    }

    public IECard getIECard() {
        return new IECard(driver);
    }

    public SafariCard getSafariCard() {
        return new SafariCard(driver);
    }

    protected List<WebElement> getSelector() {
        return driver.findElements(By.xpath("//*[@class=\"md-select-menu-container md-active md-clickable\"]/md-select-menu/md-content"));
    }

    protected boolean isSelectorVisible() {
        return driver.findElements(By.xpath("//*[@class=\"md-select-menu-container md-active md-clickable\"]/md-select-menu/md-content")).size() > 0;
    }

    protected void refresh() {
        driver.getKeyboard().pressKey(Keys.ESCAPE);
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

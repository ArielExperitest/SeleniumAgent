package Test;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;


public class OpenManualBrowser extends TestBase {


    private static final String HTTPS_QACLOUD_EXPERITEST_COM = "https://qacloud.experitest.com";
    private static final String HTTPS_QACLOUD_EXPERITEST_COM_BROWSERS = "https://qacloud.experitest.com/index.html#/browsers";
    private static final By BROWSER_CARD = By.xpath("//*[@class=\"browser-card _md\" and not(contains(@class, \"browsers-image\"))]");
    private static final By VERSION_OS_SELECTOR = By.xpath("//*[@class=\"md-select-value\"]");

    @Override
    protected void test() {
        driver = new RemoteWebDriver(url, dc);
        driver.get(HTTPS_QACLOUD_EXPERITEST_COM);
        driver.findElement(By.xpath("//*[@name=\"username\"]")).sendKeys("ariel");
        driver.findElement(By.xpath("//*[@name=\"password\"]")).sendKeys("Experitest2012");
        driver.findElement(By.xpath("//*[@name=\"login\"]")).click();
        driver.get(HTTPS_QACLOUD_EXPERITEST_COM_BROWSERS);
        List<WebElement> browserCard = driver.findElements(BROWSER_CARD);
        int randomNum = (int) (Math.random() * 5);
        List<WebElement> selector = browserCard.get(randomNum).findElements(VERSION_OS_SELECTOR);
        WebElement version = selector.get(0);
        version.click();
        List<WebElement> manueSelector = browserCard.get(randomNum).findElements(By.xpath("//*[@class=\"md-select-menu-container md-active md-clickable\"]/md-select-menu/md-content/md-option"));

        driver.findElement(By.xpath("//*[@class=\"md-select-menu-container md-active md-clickable\"]/md-select-menu/md-content/md-option[" + 1 + "]"));
        WebElement os = selector.get(1);


    }
}

package Test;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by ariel.hazan on 04-Dec-17
 */
public class VersionCheck extends TestBase {

    private String versionToCheck;

    public VersionCheck(String browserType, String versionToCheck) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType + " " + versionToCheck;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
        this.versionToCheck = versionToCheck;
        dc.setCapability(CapabilityType.BROWSER_VERSION, versionToCheck);

    }

    @Override
    public void test() {
        startTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));

        switch (browserType) {
            case BrowserType.FIREFOX: {
                driver = new RemoteWebDriver(url, dc);
                driver.get("about:support");
                WebElement ver = driver.findElement(By.xpath("//*[@id=\"version-box\"]"));
                if (!ver.getText().equals(versionToCheck)) {
                    System.out.println("Version to check= " + versionToCheck);
                    System.out.println("Version= " + ver.getText());
                }
                break;

            }
            case BrowserType.IE: {
                driver = new RemoteWebDriver(url, dc);
                driver.get("http://www.whatversion.net/internet-explorer/");
                WebElement ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]/h2")); //Your IE version is 11.0
                System.out.println("Version to check= " + versionToCheck);
                System.out.println(ver.getText());
                break;

            }
            case BrowserType.CHROME: {
                driver = new RemoteWebDriver(url, dc);
                driver.get("chrome://version");
                WebElement ver = driver.findElement(By.xpath("//*[@id=\"version\"]/span[1]"));
                if (!(ver.getText().split("\\.")[0]).equals(versionToCheck)) {
                    System.out.println("Version to check= " + versionToCheck);
                    System.out.println("Version= " + ver.getText());
                }
                break;

            }
            case BrowserType.SAFARI: {
                driver = new RemoteWebDriver(url, dc);
                driver.get("http://www.whatversion.net/safari/");
                WebElement ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]/h2")); //Your IE version is 11.0
                System.out.println("Version to check= " + versionToCheck);
                System.out.println("Version= " + ver.getText());
                break;

            }
        }
        platformName = (String) dc.getCapability("platformName");
    }
}

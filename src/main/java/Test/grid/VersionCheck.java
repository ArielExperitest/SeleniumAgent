package Test.grid;

import FrameWork.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;


/**
 * Created by ariel.hazan on 04-Dec-17
 */
public class VersionCheck extends TestBase {

    private String versionToCheck;
    private final Logger log = Logger.getLogger(this.getClass().getName());

    public VersionCheck(String browserType, String versionToCheck) {
        this.browserType = browserType;
        this.versionToCheck = versionToCheck;
        testName = this.getClass().getSimpleName() + " Test " + browserType + " " + versionToCheck;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
        dc.setCapability(CapabilityType.BROWSER_VERSION, versionToCheck);

    }

    @Override
    public void test() {
        driver = new RemoteWebDriver(url, dc);
        platform = String.valueOf(driver.getCapabilities().getPlatform());
        reportUrl = (String) driver.getCapabilities().getCapability("reportUrl");
        sessionId = (String) driver.getCapabilities().getCapability("sessionId");

        WebElement ver = null;
        switch (browserType) {
            case BrowserType.FIREFOX: {
                driver.get("about:support");
                ver = driver.findElement(By.xpath("//*[@id=\"version-box\"]"));
                break;
            }
            case BrowserType.IE: {
                driver.get("http://www.whatversion.net/internet-explorer/");
                ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]")); //Your IE version is 11.0
                break;
            }

            case BrowserType.SAFARI: {
                driver.get("http://www.whatversion.net/safari/");
                ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]/h2")); //Your IE version is 11.0\
                break;
            }
            case BrowserType.CHROME: {
                driver.get("chrome://version");
                ver = driver.findElement(By.xpath("//*[@id=\"version\"]/span[1]"));
                break;
            }
        }

        if (Objects.nonNull(ver) && !ver.getText().contains(versionToCheck))
            log.error("Check for version " + versionToCheck + " get " + ver.getText());
    }
}
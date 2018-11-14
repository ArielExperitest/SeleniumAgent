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
        driver.get("http://www.whatversion.net" + getBrowserUrl());
        WebElement ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]")); //Your IE version is 11.0
        if (Objects.nonNull(ver) && !ver.getText().contains(versionToCheck))
            log.error("Check for version " + versionToCheck + " get " + ver.getText());
    }

    private String getBrowserUrl() {
        switch (browserType) {
            case BrowserType.FIREFOX: {
                return "/firefox/";
            }
            case BrowserType.IE: {
                return "/internet-explorer/";
            }
            case BrowserType.SAFARI: {
                return "/safari/";
            }
            case BrowserType.CHROME: {
                return "/safari/";
            }
            case BrowserType.EDGE: {
                return "/edge/";
            }
        }
        return null;
    }
}
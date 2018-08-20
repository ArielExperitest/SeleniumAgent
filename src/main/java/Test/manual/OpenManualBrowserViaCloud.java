package Test.manual;

import FrameWork.TestBase;
import Test.manual.POM.BrowsersPage;
import Test.manual.POM.Cards.ChromeCard;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Set;

public class OpenManualBrowserViaCloud extends TestBase {
    private final Logger log = Logger.getLogger(this.getClass().getName());


    public OpenManualBrowserViaCloud(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    protected void test() throws Exception {
        driver = new RemoteWebDriver(url, dc);
        BrowsersPage browsersPage = new BrowsersPage(driver);
        checkCardVersion(browsersPage.getChromeCard());
//        checkCardVersionAndOS(browsersPage.getChromeCard());
        driver.quit();
    }

    private void checkCardVersion(ChromeCard chromeCard) throws Exception {
        if (chromeCard.isCardVisible()) {
            int versionSelectorSize = chromeCard.getVersionSelector().size();
            chromeCard.hideSelector();
            log.info("Start the for loop");
            for (int i = 0; i < versionSelectorSize; i++) {
                chromeCard.hideSelector();
                log.info("Click on selector in " + i);
                chromeCard.getVersionSelector().get(i).click();

                log.info("Click on button");
                String versionName = chromeCard.getVersionField().getText();
                chromeCard.getButton().click();

                log.info("<<<<<checkNewWindow");
                if (i == 0)
                    versionName = versionName.split(" ")[0];
                checkNewWindow(versionName);
                log.info("checkNewWindow>>>>>>>>>");
            }
        } else {
            log.info("Agent is offline");
        }
    }

    private void checkNewWindow(String windowTitle) throws Exception {
        //Wait to manual browser to open
        while (true) {
            sleep(10 * 1000);
            if (driver.getWindowHandles().size() > 1) {
                log.info("Found it!");
                break;
            }

            if (driver.findElements(By.xpath("//*[@class=\"modal-dialog \"]")).size() > 0)
                throw new RuntimeException("--- Popup failed to open " + windowTitle);
//            Assert.assertFalse("--- Popup failed to open " + windowTitle + " !!", driver.findElements(By.xpath("//*[@class=\"modal-dialog \"]")).size() > 0);

            log.info("New window not found!");
        }
        String currentWindowHandle = driver.getWindowHandle();
        log.info("- Open a new tab");
        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {
            //if it contains the current window we want to eliminate that from switchTo();
            if (!window.equals(currentWindowHandle)) {
                driver.switchTo().window(window);
                log.info("- Ask " + windowTitle + " and get " + driver.getTitle());

                while (true) {
                    if (driver.findElements(By.xpath("//*[@id=\"screenCanvas\"]")).size() > 0) break;
                }
                //check the window
                if (!driver.getTitle().contains(windowTitle))
                    throw new RuntimeException("Ask " + windowTitle + " and get " + driver.getTitle());

//                Assert.assertTrue("Ask " + windowTitle + " and get " + driver.getTitle(), driver.getTitle().contains(windowTitle));

                //Close the newly opened tab
                driver.close();
                log.info("- Close the new tab");
                driver.switchTo().window(currentWindowHandle);
                log.info("- Switch back to Cloud");
            }
        }
    }
}


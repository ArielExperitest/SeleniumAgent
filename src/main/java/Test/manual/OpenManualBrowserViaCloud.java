package Test.manual;

import FrameWork.TestBase;
import Test.manual.POM.BrowsersPage;
import Test.manual.POM.Cards.ChromeCard;
import Test.manual.POM.Cards.FirefoxCard;
import Test.manual.POM.Cards.IECard;
import Test.manual.POM.Cards.SafariCard;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OpenManualBrowserViaCloud extends TestBase {


    public OpenManualBrowserViaCloud(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    protected void test() {
        driver = new RemoteWebDriver(url, dc);

        BrowsersPage browsersPage = new BrowsersPage(driver);

        checkChrome(browsersPage);
//        checkFirefox(browsersPage);
//        checkIE(browsersPage);
//        checkSafari(browsersPage);
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkChrome(BrowsersPage browsersPage) {
        ChromeCard card = browsersPage.getChromeCard();
        if (card.isCardVisible()) {

            List<WebElement> chromeVersion = card.getVersionSelector();
            System.out.println("Number of chromeVersion: " + chromeVersion.size());
            for (WebElement anChromeVersion : chromeVersion) {
                card.escapeButton();
                card.getChosenVersionField().sendKeys(Keys.ENTER);
                anChromeVersion.click();
                List<WebElement> chromeOS = card.getOSSelector();

                System.out.println("Number of chromeOS: " + chromeOS.size());
                for (WebElement anChromeOS : chromeOS) {
                    card.escapeButton();
                    System.out.println("Open: " + card.getCurrentOS() + " " + card.getCurrentVersion().split("\\(")[0]);
                    card.getChosenOSField().sendKeys(Keys.ENTER);
                    anChromeOS.click();
                    card.getButton().click();
                    checkNewWindow("Chrome " + card.getCurrentVersion().split("\\(")[0]);
                }
            }
            System.out.println("Finish to check Chrome");

        } else {
            System.out.println("Agent is offline");
        }
    }

    private void checkNewWindow(String windowTitle) {
        String currentWindowHandle = driver.getWindowHandle();
        //Wait to manual browser to open
        while (true) if (driver.getWindowHandles().size() > 1) break;
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            //if it contains the current window we want to eliminate that from switchTo();
            if (!window.equals(currentWindowHandle)) {
                driver.switchTo().window(window);

                //check the window
                if (driver.getTitle().equals(windowTitle)) {
                    System.out.println("Ask " + driver.getTitle() + " and get " + windowTitle);
//                    throw new RuntimeException("Ask " + driver.getTitle() + " and get " + windowTitle);
                }
//                driver.findElement(By.xpath("//*[@id=\"screenCanvas\"]"));

                //Close the newly opened tab
                driver.close();
                driver.switchTo().window(currentWindowHandle);
            }
        }
    }

    public void checkFirefox(BrowsersPage browsersPage) {
        System.out.println("-------FF---------");
        FirefoxCard firefoxCard = browsersPage.getFirefoxCard();
//        if (firefoxCard.isCardVisible()) {

        List<WebElement> a = firefoxCard.getOSSelector();
        for (WebElement anA : a) {
            System.out.println(anA.getText());
        }
        a = firefoxCard.getVersionSelector();
        for (WebElement anA : a) {
            System.out.println(anA.getText());
        }
//        }else{
//            System.out.println("Agent is offline");
//        }
    }

    public void checkIE(BrowsersPage browsersPage) {
        System.out.println("-------IE---------");
        IECard ieCard = browsersPage.getIECard();
        if (ieCard.isCardVisible()) {

            List<WebElement> a = ieCard.getOSSelector();
            for (WebElement anA : a) {
                System.out.println(anA.getText());
            }
            a = ieCard.getVersionSelector();
            for (WebElement anA : a) {
                System.out.println(anA.getText());
            }
        } else {
            System.out.println("Agent is offline");
        }
    }

    public void checkSafari(BrowsersPage browsersPage) {
        System.out.println("-------Safari---------");
        SafariCard safariCard = browsersPage.getSafariCard();
        if (safariCard.isCardVisible()) {
            List<WebElement> a = safariCard.getOSSelector();
            for (WebElement anA : a) {
                System.out.println(anA.getText());
            }
            a = safariCard.getVersionSelector();
            for (WebElement anA : a) {
                System.out.println(anA.getText());
            }

        } else {
            System.out.println("Agent is offline");
        }
    }
}


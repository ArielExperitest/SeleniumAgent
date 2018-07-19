package Test.manual;

import FrameWork.TestBase;
import Test.manual.POM.BrowsersPage;
import Test.manual.POM.Cards.ChromeCard;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
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
        checkCardVersion(browsersPage.getChromeCard());
//        checkCardVersionAndOS(browsersPage.getChromeCard());
        driver.quit();
    }

    private void checkCardVersion(ChromeCard chromeCard) {
        if (chromeCard.isCardVisible()) {
            int versionSelectorSize = chromeCard.getVersionSelector().size();
            chromeCard.hideSelector();
            System.out.println("Start the for loop");
            for (int i = 0; i < versionSelectorSize; i++) {
                chromeCard.hideSelector();
                System.out.println("Click on selector in " + i);
                chromeCard.getVersionSelector().get(i).click();

                System.out.println("Click on button");
                String versionName = chromeCard.getVersionField().getText();
                chromeCard.getButton().click();

                System.out.println("<<<<<checkNewWindow");
                checkNewWindow(versionName);
                System.out.println("checkNewWindow>>>>>>>>>");
            }
        } else {
            System.out.println("Agent is offline");
        }
    }

    private void checkNewWindow(String windowTitle) {
        boolean findNewTab = true;
        //Wait to manual browser to open
        while (true) {
            if (driver.getWindowHandles().size() > 1) {
                System.out.println("Found it!");
                break;
            }
            if (driver.findElements(By.xpath("//*[@class=\"modal-dialog \"]")).size() > 0) {
                driver.findElement(By.xpath("//*[@ng-click=\"deviceOpenFailureCtrl.cancel()\"]")).click();
                findNewTab = false;
                System.out.println("--- Failed to open a new tab!!");
            }
            System.out.println("new opend window not found!");
        }
        if (findNewTab) {
            String currentWindowHandle = driver.getWindowHandle();
            System.out.println("- Open a new tab");
            Set<String> windowHandles = driver.getWindowHandles();
            for (String window : windowHandles) {
                //if it contains the current window we want to eliminate that from switchTo();
                if (!window.equals(currentWindowHandle)) {
                    driver.switchTo().window(window);
                    System.out.println("- Ask " + windowTitle + " and get " + driver.getTitle());

                    //check the window
                    if (!driver.getTitle().equals(windowTitle)) {
//                    System.out.println("Ask " + driver.getTitle() + " and get " + windowTitle);
//                    throw new RuntimeException("Ask " + driver.getTitle() + " and get " + windowTitle);
                    }
//                driver.findElement(By.xpath("//*[@id=\"screenCanvas\"]"));

                    //Close the newly opened tab
                    driver.close();
                    System.out.println("- Close the new tab");
                    driver.switchTo().window(currentWindowHandle);
                    System.out.println("- Switch back to Cloud");
                }
            }
        }
    }
//
//    public void checkFirefox(BrowsersPage browsersPage) {
//        System.out.println("-------FF---------");
//        FirefoxCard firefoxCard = browsersPage.getFirefoxCard();
////        if (firefoxCard.isCardVisible()) {
//
//        List<WebElement> a = firefoxCard.getOSSelector();
//        for (WebElement anA : a) {
//            System.out.println(anA.getText());
//        }
//        a = firefoxCard.getVersionSelector();
//        for (WebElement anA : a) {
//            System.out.println(anA.getText());
//        }
////        }else{
////            System.out.println("Agent is offline");
////        }
//    }
//
//    public void checkIE(BrowsersPage browsersPage) {
//        System.out.println("-------IE---------");
//        IECard ieCard = browsersPage.getIECard();
//        if (ieCard.isCardVisible()) {
//
//            List<WebElement> a = ieCard.getOSSelector();
//            for (WebElement anA : a) {
//                System.out.println(anA.getText());
//            }
//            a = ieCard.getVersionSelector();
//            for (WebElement anA : a) {
//                System.out.println(anA.getText());
//            }
//        } else {
//            System.out.println("Agent is offline");
//        }
//    }

//    public void checkSafari(BrowsersPage browsersPage) {
//        System.out.println("-------Safari---------");
//        SafariCard safariCard = browsersPage.getSafariCard();
//        if (safariCard.isCardVisible()) {
//            List<WebElement> a = safariCard.getOSSelector();
//            for (WebElement anA : a) {
//                System.out.println(anA.getText());
//            }
//            a = safariCard.getVersionSelector();
//            for (WebElement anA : a) {
//                System.out.println(anA.getText());
//            }
//
//        } else {
//            System.out.println("Agent is offline");
//        }
//    }
}


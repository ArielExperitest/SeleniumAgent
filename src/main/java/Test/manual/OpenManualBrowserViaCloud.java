package Test.manual;

import FrameWork.TestBase;
import Test.manual.POM.BrowsersPage;
import Test.manual.POM.Cards.ChromeCard;
import Test.manual.POM.Cards.FirefoxCard;
import Test.manual.POM.Cards.IECard;
import Test.manual.POM.Cards.SafariCard;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

import static java.lang.Thread.sleep;


public class OpenManualBrowserViaCloud extends TestBase {


    @Override
    protected void test() {
        driver = new RemoteWebDriver(url, dc);

        BrowsersPage browsersPage = new BrowsersPage(driver);

        checkChrome(browsersPage);
        checkFirefox(browsersPage);
        checkIE(browsersPage);
        checkSafari(browsersPage);
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkChrome(BrowsersPage browsersPage) {
        System.out.println("-------Chrome---------");
        ChromeCard chromeCard = browsersPage.getChromeCard();
        if (chromeCard.isCardVisible()) {
            List<WebElement> a = chromeCard.getOSSelector();
            for (WebElement anA : a) {
                System.out.println(anA.getText());
            }
            a = chromeCard.getVersionSelector();
            for (WebElement anA : a) {
                System.out.println(anA.getText());
            }
        }else{
            System.out.println("Agent is offline");
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
        }else{
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

        }else{
            System.out.println("Agent is offline");
        }
    }
}


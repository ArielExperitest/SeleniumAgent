package Test.manual.POM.Cards;

import Test.manual.POM.BrowsersPage;
import Test.manual.POM.Cards.BrowserCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class FirefoxCard extends BrowsersPage implements BrowserCard {

    private static final By OS_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.firefox.os\"]");
    private static final By VERSION_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.firefox.version\"]");
    private static final By OPEN_BUTTON = By.xpath("//*[@ng-click=\"browserCtrl.openManual('firefox', browserCtrl.selectedVersion.firefox)\"]");
    private static final By FIREFOX_CARD = By.xpath("//*[@class=\"browser-card _md\"][2]");


    public FirefoxCard(RemoteWebDriver driver) {
        super(driver);
        setBrowserName("Firefox ");
    }

    @Override
    public WebElement getVersionField() {
        return driver.findElement(VERSION_FIELD);
    }

    @Override
    public WebElement getOSField() {
        return driver.findElement(OS_FIELD);
    }

    @Override
    public WebElement getButton() {
        return driver.findElement(OPEN_BUTTON);
    }

    @Override
    public List<WebElement> getVersionSelector() {
        if (!isCardVisible())
            return null;
        hideSelector();
        getVersionField().click();
        return getSelector();
    }

    @Override
    public List<WebElement> getOSSelector() {
        if (!isCardVisible()) return null;
        hideSelector();
        getOSField().click();
        return getSelector();
    }

    @Override
    public boolean isCardVisible() {
        return driver.findElements(By.xpath("//*[@class=\"browsers-image firefox\"]")).size() > 0;
    }
}

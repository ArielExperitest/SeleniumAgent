package Test.manual.POM.Cards;

import Test.manual.POM.BrowsersPage;
import Test.manual.POM.Cards.BrowserCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class SafariCard extends BrowsersPage implements BrowserCard {


    private static final By OS_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.safari.os\"]");
    private static final By VERSION_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.safari.version\"]");
    private static final By OPEN_BUTTON = By.xpath("//*[@ng-click=\"browserCtrl.openManual('safari', browserCtrl.selectedVersion.safari)\"]");
    private static final By SAFARI_CARD = By.xpath("//*[@class=\"browser-card _md\"][4]");

    public SafariCard(RemoteWebDriver driver) {
        super(driver);
        setBrowserName("Safari ");
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
        if (isCardVisible()) {
            hideSelector();
            getVersionField().click();
            return getSelector();
        } else {
            return null;
        }
    }

    @Override
    public List<WebElement> getOSSelector() {
        if (isCardVisible()) {
            hideSelector();
            getOSField().click();
            return getSelector();
        } else {
            return null;
        }
    }

    @Override
    public boolean isCardVisible() {
        return driver.findElements(By.xpath("//*[@class=\"browsers-image safari\"]")).size() > 0;
    }
}

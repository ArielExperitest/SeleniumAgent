package Test.manual.POM.Cards;

import Test.manual.POM.BrowsersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class SafariCard extends BrowsersPage implements BrowserCard {


    private static final By OS_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.safari.os\"]");
    private static final By VERSION_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.safari.version\"]");
    private static final By OPEN_BUTTON = By.xpath("//*[@ng-click=\"browserCtrl.openManual('safari', browserCtrl.selectedVersion.safari)\"]");
    private static final By SAFARI_CARD = By.xpath("//*[@class=\"browser-card _md\"][4]");
    private WebElement cardWebElement ;

    public SafariCard(RemoteWebDriver driver) {
        super(driver);
        cardWebElement = driver.findElement(SAFARI_CARD);
    }

    @Override
    public WebElement getChosenVersionField() {
        return cardWebElement.findElement(VERSION_FIELD);
    }

    @Override
    public WebElement getChosenOSField() {
        return cardWebElement.findElement(OS_FIELD);
    }

    @Override
    public WebElement getButton() {
        return cardWebElement.findElement(OPEN_BUTTON);
    }

    @Override
    public List<WebElement> getVersionSelector() {
        if (isCardVisible()) {
            if (isSelectorVisible()) refresh();
            getChosenVersionField().click();
            return getSelector();
        } else {
            return null;
        }
    }

    @Override
    public List<WebElement> getOSSelector() {
        if (isCardVisible()) {
            if (isSelectorVisible()) refresh();
            getChosenOSField().click();
            return getSelector();
        } else {
            return null;
        }
    }

    @Override
    public boolean isCardVisible() {
        return cardWebElement.findElements(By.xpath("//*[@class=\"browsers-image safari\"]")).size() > 0;
    }
}

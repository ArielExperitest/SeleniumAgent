package Test.manual.POM.Cards;

import Test.manual.POM.BrowsersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class FirefoxCard extends BrowsersPage implements BrowserCard {

    private static final By OS_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.firefox.os\"]");
    private static final By VERSION_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.firefox.version\"]");
    private static final By OPEN_BUTTON = By.xpath("//*[@ng-click=\"browserCtrl.openManual('firefox', browserCtrl.selectedVersion.firefox)\"]");
    private static final By FIREFOX_CARD = By.xpath("//*[@class=\"browser-card _md\"][2]");
    private WebElement cardWebElement ;


    public FirefoxCard(RemoteWebDriver driver) {
        super(driver);
        cardWebElement = driver.findElement(FIREFOX_CARD);
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
            System.out.println("ssssssssssssss");
            return null;
        }
    }

    @Override
    public boolean isCardVisible() {
        return cardWebElement.findElements(By.xpath("//*[@class=\"browsers-image firefox\"]")).size() > 0;
    }

}
package Test.manual.POM.Cards;

import Test.manual.POM.BrowsersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class ChromeCard extends BrowsersPage implements BrowserCard {


    private static final By OS_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.chrome.os\"]");
    private static final By VERSION_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.chrome.version\"]");
    private static final By OPEN_BUTTON = By.xpath("//*[@ng-click=\"browserCtrl.openManual('chrome', browserCtrl.selectedVersion.chrome)\"]");
    private static final By CHROME_CARD = By.xpath("//*[@class=\"browser-card _md\" and not(contains(@id,\"ios-card\"))][1]");
    private WebElement cardWebElement;

    public ChromeCard(RemoteWebDriver driver) {
        super(driver);
        cardWebElement = driver.findElement(CHROME_CARD);
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
            if (isSelectorVisible()) escapeButton();
            getChosenVersionField().click();
            return getSelector();
        } else {
            return null;
        }
    }

    @Override
    public List<WebElement> getOSSelector() {
        if (isCardVisible()) {
            if (isSelectorVisible()) escapeButton();
            getChosenOSField().click();
            return getSelector();
        } else {
            return null;
        }
    }

    @Override
    public boolean isCardVisible() {
        return cardWebElement.findElements(By.xpath("//*[@class=\"browsers-image\"]")).size() > 0;
    }

    @Override
    public String getCurrentOS() {
        return getChosenOSField().getText();
    }

    @Override
    public String getCurrentVersion() {
        return getChosenVersionField().getText();
    }

}

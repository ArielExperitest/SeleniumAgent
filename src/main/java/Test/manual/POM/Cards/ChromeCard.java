package Test.manual.POM.Cards;

import Test.manual.POM.BrowsersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class ChromeCard extends BrowsersPage implements BrowserCard {


    //    private static final By OS_FIELD = By.xpath("//*[@layout=\"row\"]/md-input-container[2]");
    private static final By VERSION_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.chrome.version\"]");
    private static final By OS_FIELD = By.xpath("//*[@ng-model=\"browserCtrl.selectedVersion.chrome.os\"]");
    private static final By OPEN_BUTTON = By.xpath("//*[@ng-click=\"browserCtrl.openManual('chrome', browserCtrl.selectedVersion.chrome)\"]");

    public ChromeCard(RemoteWebDriver driver) {
        super(driver);
        setBrowserName("Chrome ");
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
        getVersionField().click();
        return getSelector();
    }

    @Override
    public List<WebElement> getOSSelector() {
        getOSField().click();
        return getSelector();
    }

    @Override
    public boolean isCardVisible() {
        return driver.findElements(By.xpath("//*[@class=\"browsers-image \"]")).size() > 0;
    }
}

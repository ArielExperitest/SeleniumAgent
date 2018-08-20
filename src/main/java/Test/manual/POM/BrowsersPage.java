package Test.manual.POM;

import Test.manual.POM.Cards.ChromeCard;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BrowsersPage {
    private static final By SELECTOR_XPATH = By.xpath("//*[@class=\"md-select-menu-container md-active md-clickable\"]/md-select-menu/md-content/md-option");
    private static final By XPATH_OK_DIALOG = By.xpath("//*[@ng-click=\"deviceOpenFailureCtrl.cancel()\"]");
    private static final By XPATH_ERROR_WINDOW = By.xpath("//*[@class=\"modal-dialog \"]");
    private static final String HTTPS_QACLOUD_EXPERITEST_COM = "https://qacloud.experitest.com";
    private static final String USERNAME_XPATH = "//*[@name=\"username\"]";
    private static final String PASSWORD_XPATH = "//*[@name=\"password\"]";
    private static final String LOGIN_XPATH = "//*[@name=\"login\"]";
    protected RemoteWebDriver driver;
    protected String browserName;

    public BrowsersPage(RemoteWebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contains("browsers"))
            loginToCloudGoTOBrowsers();
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    private void loginToCloudGoTOBrowsers() {
        driver.manage().window().maximize();
        sleep(500);
        driver.get(HTTPS_QACLOUD_EXPERITEST_COM);
        sleep(500);
        driver.findElement(By.xpath(USERNAME_XPATH)).sendKeys("ariel");
        sleep(500);
        driver.findElement(By.xpath(PASSWORD_XPATH)).sendKeys("Experitest2012");
        sleep(500);
        driver.findElement(By.xpath(LOGIN_XPATH)).click();
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();

        System.out.println("--Finish constructor-----");
    }

    public ChromeCard getChromeCard() {
        return new ChromeCard(driver);
    }

    protected List<WebElement> getSelector() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(SELECTOR_XPATH));
        try {
            return driver.findElements(SELECTOR_XPATH);
        } catch (Exception e) {
            System.out.println("[Ariel Log] Fail to find selector!!!!!!!!!!!");
            return driver.findElements(SELECTOR_XPATH);
        }
    }

    protected boolean isSelectorVisible() {
        return driver.findElements(SELECTOR_XPATH).size() > 0;
    }

    public void hideSelector() {
        if (isSelectorVisible()) {
            driver.getKeyboard().pressKey(Keys.ENTER);
            sleep(100);
            driver.getKeyboard().pressKey(Keys.ESCAPE);

            while (driver.findElements(By.xpath("//*[@class=\"md-select-menu-container md-active md-clickable\"]")).size() > 0) {
                driver.getKeyboard().pressKey(Keys.ENTER);
                sleep(100);
                driver.getKeyboard().pressKey(Keys.ESCAPE);
                System.out.println("&&&&&&&&WTH!!!!!!! it's still open");
            }

        }
//        waitForElement(By.xpath(properties.getProperty("RefreshPage.Icon"));
//        WebElement element = driver.findElement(By.xpath(properties.getProperty("RefreshPage.Icon"));
//        getRecordsCountFromTable(element).getText());
    }

    public WebElement getErrorDialog() {
        return driver.findElement(XPATH_OK_DIALOG);
    }

    public boolean isErrorWindowViable() {
        return driver.findElements(XPATH_ERROR_WINDOW).size() > 0;
    }


    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

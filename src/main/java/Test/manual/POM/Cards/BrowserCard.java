package Test.manual.POM.Cards;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface BrowserCard {


    WebElement getVersionField();

    WebElement getOSField();

    WebElement getButton();

    List<WebElement> getVersionSelector();

    List<WebElement> getOSSelector();

    boolean isCardVisible();

    void hideSelector();

    String getBrowserName();
}

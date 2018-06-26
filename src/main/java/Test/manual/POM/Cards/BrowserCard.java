package Test.manual.POM.Cards;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface BrowserCard {


    WebElement getChosenVersionField();

    WebElement getChosenOSField();

    WebElement getButton();

    List<WebElement> getVersionSelector();

    List<WebElement> getOSSelector();

    boolean isCardVisible();
}

package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SeleniumScreenshot extends TestBase {

    public SeleniumScreenshot(String browserType) {
        this.browserName = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        dc.setCapability("seleniumScreenshot", false);
        dc.setCapability("takeScreenshots", false);
        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, false);//takesScreenshot
        dc.setCapability("generateReport", false);
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://en.wikipedia.org");
        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        driver.get("https://www.google.com");
        try {
            BufferedImage bufferedImage = ImageIO.read(scrFile);
            ImageIO.write(bufferedImage, "png", new File("reports/images/screenshot_" + browserName + "_" + System.currentTimeMillis() + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

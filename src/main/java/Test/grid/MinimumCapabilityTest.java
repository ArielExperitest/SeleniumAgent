package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static FrameWork.Credentials.PASS;
import static FrameWork.Credentials.PROJECT;
import static FrameWork.Credentials.USER;

/**
 * Created by ariel.hazan on 10-Dec-17
 */
public class MinimumCapabilityTest extends TestBase {

    private DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    public MinimumCapabilityTest() {
        desiredCapabilities.setCapability("username", USER);
        desiredCapabilities.setCapability("password", PASS);
        desiredCapabilities.setCapability("projectName", PROJECT);
    }

    @Override
    public void test() {
        driver = new RemoteWebDriver(url, desiredCapabilities);
        driver.get("https://www.ynet.co.il");
    }
}

package aRunners;

import FrameWork.Credentials;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static FrameWork.Credentials.updateServerCredentials;

public class TestInitializer {
    protected boolean USE_WAIT_UNTIL = true;//help to ignore from browsers issues
    protected boolean USE_ACCESS_KEY = false;

    public TestInitializer() {
//        updateServerCredentials(Credentials.CloudServerName.RND_VM_CLOUD);
        updateServerCredentials(Credentials.CloudServerName.QA_ADMIN);
//        updateServerCredentials(Credentials.CloudServerName.ARIEL_MAC_ADMIN);
//        updateServerCredentials(Credentials.CloudServerName.MASTER_CLOUD);

//        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, false);//takesScreenshot - not supporting
//        dc.setCapability("seleniumScreenshot", false);
        dc.setCapability("takeScreenshots", true);
        dc.setCapability("generateReport", true);
        dc.setCapability("newCommandTimeout", 1000);//default is 300
        dc.setCapability("newSessionWaitTimeout", 1000);//default is 300
        dc.setCapability("useWaitUntil", USE_WAIT_UNTIL);//default is 300
//        dc.setCapability(CapabilityType.BROWSER_VERSION, "64.0");
//        dc.setCapability(CapabilityType.PLATFORM, Platform.WIN10);
//        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        dc.setCapability("agentName", "Selenium Agent - 2.182");
    }

    protected DesiredCapabilities dc = new DesiredCapabilities();
}

package aRunners;

import FrameWork.Credentials;
import org.openqa.selenium.remote.DesiredCapabilities;

import static FrameWork.Credentials.updateServerCredentials;

public class TestInitializer {
    protected boolean USE_ACCESS_KEY = false;

    public TestInitializer() {
//        updateServerCredentials(Credentials.CloudServerName.RND_VM_CLOUD);
        updateServerCredentials(Credentials.CloudServerName.QA_SECURE_ADMIN);
//        updateServerCredentials(Credentials.CloudServerName.MASTER_CLOUD);
//        updateServerCredentials(Credentials.CloudServerName.DEEP_TESTING_CLOUD_PROJECT_ADMIN);
//        updateServerCredentials(Credentials.CloudServerName.ARIEL_MAC_ADMIN);
//        updateServerCredentials(Credentials.CloudServerName.ARIEL_MAC_PRO_ADMIN);
//        updateServerCredentials(Credentials.CloudServerName.ARIEL_MAC_USER);

//        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, false);//takesScreenshot - not supporting
//        dc.setCapability("seleniumScreenshot", false);
        dc.setCapability("takeScreenshots", true);
        dc.setCapability("generateReport", true);
        dc.setCapability("newCommandTimeout", 1000);//default is 300
        dc.setCapability("newSessionWaitTimeout", 1000);//default is 300
//        dc.setCapability(CapabilityType.BROWSER_VERSION, "63.0.1");
//        dc.setCapability(CapabilityType.PLATFORM, Platform.WIN10);
//        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        dc.setCapability("seetest:location.service.gps", "true");
    }

    protected DesiredCapabilities dc = new DesiredCapabilities();
}

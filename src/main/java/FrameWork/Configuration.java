package FrameWork;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static FrameWork.Credentials.*;

public class Configuration {

    private DesiredCapabilities desiredCapabilities = null;

    public Configuration(DesiredCapabilities desiredCapabilities) {
        this.desiredCapabilities = desiredCapabilities;
    }

    protected boolean USE_AK_Flag = false;
    long START_TEST_TIME = System.currentTimeMillis();

    protected Configuration() {
//        updateServerCredentials(CloudServerName.RND_VM_CLOUD);
        updateServerCredentials(CloudServerName.QA_SECURE_ADMIN);
//        updateServerCredentials(CloudServerName.ARIEL_MAC_ADMIN);
//        updateServerCredentials(CloudServerName.ARIEL_MAC_PRO_ADMIN);
//        updateServerCredentials(CloudServerName.ARIEL_MAC_USER);
    }

    void setDC() {
        setURL();

//        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, false);//takesScreenshot - not supporting
        dc.setCapability("seleniumScreenshot", false);
        dc.setCapability("takeScreenshots", true);
        dc.setCapability("generateReport", true);
        dc.setCapability("newCommandTimeout", "300");//default is 300
        dc.setCapability("newSessionWaitTimeout", "300");//default is 300
//        dc.setCapability(CapabilityType.BROWSER_VERSION, "63.0.1");
//        dc.setCapability(CapabilityType.PLATFORM, Platform.WIN10);
//        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
    }

    private void setURL() {
        try {
            if (USE_AK_Flag) {
                url = new URL("http://:" + AK + "@" + HOST + ":" + PORT + "/wd/hub");
                if (SECURE)
                    url = new URL("https://:" + AK + "@" + HOST + ":" + PORT + "/wd/hub");
            } else {

                url = new URL("http://" + HOST + ":" + PORT + "/wd/hub");
                if (SECURE)
                    url = new URL("https://" + HOST + ":" + PORT + "/wd/hub");
                if (desiredCapabilities == null) {
                    dc.setCapability("username", USER);
                    dc.setCapability("password", PASS);
                    dc.setCapability("projectName", PROJECT); //only required if your user has several projects assigned to it. Otherwise, exclude this capability.
                } else
                    dc.merge(desiredCapabilities);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected URL url;
    protected RemoteWebDriver driver;
    protected DesiredCapabilities dc = new DesiredCapabilities();


    static {
        //Ariel Windows keystore
//        System.getProperties().setProperty("javax.net.ssl.trustStore", "C:\\Users\\ariel.hazan\\Desktop\\Key\\truststore.jks");
//        System.getProperties().setProperty("javax.net.ssl.trustStorePassword", "123456");

        //Ariel Mac keystore
//        System.getProperties().setProperty("javax.net.ssl.trustStore", "C:\\Users\\ariel.hazan\\Desktop\\Key\\Ariel_Mac_Keystore.jks");
//        System.getProperties().setProperty("javax.net.ssl.trustStorePassword", "");

        //Dikla keystore
//        System.getProperties().setProperty("javax.net.ssl.trustStore", "C:\\Users\\ariel.hazan\\Downloads\\trust_store.jks");
//        System.getProperties().setProperty("javax.net.ssl.trustStorePassword", "");
    }
}

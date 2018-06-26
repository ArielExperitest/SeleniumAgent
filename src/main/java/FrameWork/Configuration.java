package FrameWork;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static FrameWork.Credentials.*;

public class Configuration {
    Configuration() {
//        CloudServerName cloudName = CloudServerName.ARIEL_MAC_USER;
//        CloudServerName cloudName = CloudServerName.ARIEL_MAC_PRO_ADMIN;
//        CloudServerName cloudName = CloudServerName.ARIEL_MAC_ADMIN;
//        CloudServerName cloudName = CloudServerName.ARIEL_MAC_SECURE_ADMIN;
//        CloudServerName cloudName = CloudServerName.MASTER_CLOUD;
//        CloudServerName cloudName = CloudServerName.YORAM;

        CloudServerName cloudName = CloudServerName.ARIEL_WIN_ADMIN;
//        CloudServerName cloudName = CloudServerName.ARIEL_WIN_SECURE_ADMIN;
//     CloudServerName cloudName = CloudServerName.QA_SECURE_USER

        updateServerCredentials(cloudName);
    }

    static {
        //Ariel Windows keystore
//        System.getProperties().setProperty("javax.net.ssl.trustStore", "C:\\Users\\ariel.hazan\\Desktop\\Key\\truststore.jks");
//        System.getProperties().setProperty("javax.net.ssl.trustStorePassword", "123456");

        //Ariel Mac keystore
        System.getProperties().setProperty("javax.net.ssl.trustStore", "C:\\Users\\ariel.hazan\\Desktop\\Key\\Ariel_Mac_Keystore.jks");
        System.getProperties().setProperty("javax.net.ssl.trustStorePassword", "");
    }

    public static String logFileName = "overallReport.txt";
//    public static String logFileName = "overallReport2.txt";

    protected boolean USE_AK_Flag = false;


    protected void setDC() {
        setURL();

        dc.setCapability("seleniumScreenshot", true);
        dc.setCapability("takeScreenshots", true);
        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, true);//takesScreenshot
        dc.setCapability("generateReport", true);
        dc.setCapability("newCommandTimeout", 500);//default is 300
        dc.setCapability("newSessionWaitTimeout", 500);//default is 300
//        dc.setCapability(CapabilityType.BROWSER_VERSION, "61.0b9");
//        dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.MAC);
//        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
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
                dc.setCapability("username", USER);
                dc.setCapability("password", PASS);
                dc.setCapability("projectName", PROJECT); //only required if your user has several projects assigned to it. Otherwise, exclude this capability.
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected void setMinimumDC() {
        setURL();
    }

    protected URL url;
    protected RemoteWebDriver driver;
    protected DesiredCapabilities dc = new DesiredCapabilities();
    public static String reportsPath = "reports/";
    public static String collectSupportDataPath = "reports/CSD/";
    public static String attachmentPath = "reports/attachment/";
}

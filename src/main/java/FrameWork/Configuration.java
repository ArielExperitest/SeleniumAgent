package FrameWork;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static FrameWork.Credentials.*;

public class Configuration {
    protected boolean USE_AK_Flag = false;
    long START_TEST_TIME = System.currentTimeMillis();

    //        CloudServerName cloudName = CloudServerName.ARIEL_MAC_PRO_ADMIN;
//    CloudServerName cloudName = CloudServerName.ARIEL_MAC_ADMIN;
//            CloudServerName cloudName = CloudServerName.DIKLA_WIN_USER;
//     CloudServerName cloudName = CloudServerName.ARIEL_WIN_ADMIN;
    //        CloudServerName cloudName = CloudServerName.YORAM;
//        CloudServerName cloudName = CloudServerName.MASTER_CLOUD;
     CloudServerName cloudName = CloudServerName.QA_SECURE_USER;

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

    static ExecutorService executorReport = Executors.newFixedThreadPool(3);


    protected Configuration() {
        updateServerCredentials(cloudName);
    }

    protected void setDC() {
        setURL();
//        dc.setCapability("seleniumScreenshot", false);
//        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, false);//takesScreenshot
//        dc.setCapability("takeScreenshots", false);
//        dc.setCapability("generateReport", false);
        dc.setCapability("newCommandTimeout", "300");//default is 300
        dc.setCapability("newSessionWaitTimeout", "300");//default is 300
//        dc.setCapability(CapabilityType.BROWSER_VERSION, "53.0.3");
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
    public static String collectSupportDataPath = "reports/CSD/";
    public static String attachmentPath = "reports/attachment/";
}

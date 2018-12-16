package FrameWork;

import Utils.CollectSupportDataAPI;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static FrameWork.Credentials.*;
import static FrameWork.Credentials.PASS;
import static FrameWork.Credentials.PROJECT;


public abstract class TestBase implements Runnable {
    protected abstract void test() throws Exception;

    protected boolean USE_ACCESS_KEY = false;

    protected TestBase() {
        updateServerCredentials(CloudServerName.RND_VM_CLOUD);
//        updateServerCredentials(CloudServerName.QA_SECURE_ADMIN);
//        updateServerCredentials(CloudServerName.MASTER_CLOUD);
//        updateServerCredentials(CloudServerName.DEEP_TESTING_CLOUD_PROJECT_ADMIN);
//        updateServerCredentials(CloudServerName.ARIEL_MAC_ADMIN);
//        updateServerCredentials(CloudServerName.ARIEL_MAC_PRO_ADMIN);
//        updateServerCredentials(CloudServerName.ARIEL_MAC_USER);
    }

    @Override
    public void run() {
        setDC();
        try {
            log.info("Start test - " + dc);
            initDriver();
            test();
            log.info("Result - #" + (++testIndex) + " @PASS " + getSessionDetails());
        } catch (Exception e) {
            writeToLog(driver.getCapabilities(), e);
        } finally {
            try {
                if (driver != null) {
                    driver.quit();
                }
            } catch (Exception e) {
                log.info("Fail to preform quit: " + getSessionDetails() + " exceptionMsg: " + e.getMessage());
            }
        }
    }

    //Test failed
    private void writeToLog(Capabilities capabilities, Exception exception) {
        log.error("Result - #" + (++testIndex) + " @FAIL " + getSessionDetails() + " \n" + capabilities, exception);

        countExc(platform + " " + browserType + " " + browserVersion + " - " + exception.getMessage().split("\n")[0]);
        if (failCount % 15 == 0) {
            String CSDPath = String.valueOf(testIndex) + "_" + testName + "_" + System.currentTimeMillis() + ".zip";
            new Thread(new CollectSupportDataAPI(CSDPath), CSDPath).start();
            log.info("Start downloading Collect Support Data =" + CSDPath);
            //Download report from reporter
//            String reportPath = testIndex + "_" + testName + "_" + START_TEST_TIME + ".zip";
//            log.info("Start downloading report =" + reportPath);
//            new Thread(new ReporterAttachment(reportPath, reportUrl)).start();
        }
    }

    private synchronized void countExc(String message) {
        boolean find = false;
        for (Node node :
                excList) {
            if (node.message.equals(message)) {
                node.count++;
                find = true;
                break;
            }
        }
        if (!find) excList.add(new Node(1, message));
        if (excList.size() % 5 == 0) excList.sort(Comparator.comparingInt(left -> left.count));

        log.info("########## Number of fail tests: " + (++failCount) + " of " + testIndex + "##############");
        for (Node anExcList : excList) log.info(anExcList.count + " " + anExcList.message);
        log.info("###################################");
    }

    private void initDriver() {
        driver = new RemoteWebDriver(url, dc);
        this.browserVersion = this.driver.getCapabilities().getVersion();
        this.platform = String.valueOf(this.driver.getCapabilities().getPlatform());
        this.reportUrl = (String) this.driver.getCapabilities().getCapability("reportUrl");
        this.sessionId = (String) this.driver.getCapabilities().getCapability("sessionId");
        this.browserType = this.driver.getCapabilities().getBrowserName();

        if (!browserType.equals(BrowserType.IE)) {//https://github.com/theintern/leadfoot/issues/134
            driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);
        }
        String msg = "Done init driver..";
        if (this.reportUrl == null)
            msg += " - Report is null ";
        log.info(msg);
    }

    private String getSessionDetails() {
        return platform + " " + browserVersion + " " + sessionId + " reportUrl: " + reportUrl;
    }

    void setDC() {
        try {
            if (USE_ACCESS_KEY) {
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
//        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, false);//takesScreenshot - not supporting
//        dc.setCapability("seleniumScreenshot", false);
        dc.setCapability("takeScreenshots", true);
        dc.setCapability("generateReport", true);
        dc.setCapability("newCommandTimeout", 1000);//default is 300
        dc.setCapability("newSessionWaitTimeout", 1000);//default is 300
//        dc.setCapability(CapabilityType.BROWSER_VERSION, "63.0.1");
//        dc.setCapability(CapabilityType.PLATFORM, Platform.WIN10);
//        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
    }

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private static List<Node> excList = new ArrayList<>();
    private static int failCount = 0, testIndex = 0;
    protected String sessionId = "", browserVersion = "", testName = "", browserType = "", platform = "", reportUrl = "";
    protected URL url;
    protected RemoteWebDriver driver;
    protected DesiredCapabilities dc = new DesiredCapabilities();

}

class Node {
    int count;
    String message;

    Node(int count, String message) {
        this.count = count;
        this.message = message;
    }
}
package FrameWork;

import Utils.CollectSupportDataAPI;
import aRunners.TestInitializer;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static FrameWork.Credentials.*;
import static FrameWork.Credentials.PASS;
import static FrameWork.Credentials.PROJECT;


public abstract class TestBase extends TestInitializer implements Runnable {
    protected abstract void test() throws Exception;

    protected WebDriverWait wait;

    @Override
    public void run() {
        try {
            setDC();
            log.info("Start test - " + dc);
            initDriver();
            log.info((this.reportUrl != null ? "Done init driver.. " : "Done init driver - Report is null ") + sessionId + " viewUrl: " + viewUrl);
            test();
            log.info("Result - #" + (++testIndex) + " @PASS " + getSessionDetails());
        } catch (Exception e) {
            writeToLog(driver.getCapabilities(), e);
        } finally {
            if (driver != null) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    log.info("Fail to preform quit: " + getSessionDetails() + " exceptionMsg: " + e.getMessage());
                }
            }
        }
    }

    //Test failed
    private void writeToLog(Capabilities capabilities, Exception exception) {
        log.error("Result - #" + (++testIndex) + " @FAIL " + getSessionDetails() + " \n" + capabilities, exception);

        countExc(platform + " " + browserName + " " + browserVersion + " - " + exception.getMessage().split("\n")[0]);
        if (failCount % 10 == 0) {
            String CSDPath = testIndex + "_" + testName + "_" + sessionId + ".zip";
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

        log.info("########## Number of fail tests: " + (++failCount) + " of " + testIndex + " ##############");
        for (Node anExcList : excList) log.info(anExcList.count + " " + anExcList.message);
        log.info("###################################");
    }

    private String getSessionDetails() {
        return agentName + " " + platform + " " + browserVersion + " " + sessionId + " reportUrl: " + reportUrl;
    }

    private void initDriver() {
        driver = new RemoteWebDriver(url, dc);
        Capabilities capabilities = this.driver.getCapabilities();

        this.browserVersion = capabilities.getVersion();
        this.platform = String.valueOf(capabilities.getPlatform());
        this.reportUrl = (String) capabilities.getCapability("reportUrl");
        this.sessionId = (String) capabilities.getCapability("sessionId");
        this.agentName = (String) capabilities.getCapability("agentName");
        this.viewUrl = (String) capabilities.getCapability("viewUrl");
        this.browserName = capabilities.getBrowserName();

        wait = new WebDriverWait(driver, 90);
        if (!browserName.equals(BrowserType.IE) && !browserVersion.equals("64.0") && !browserVersion.equals("65.0")) {//https://github.com/theintern/leadfoot/issues/134
            driver.manage().timeouts()
                    .implicitlyWait(90, TimeUnit.SECONDS)
                    .pageLoadTimeout(90, TimeUnit.SECONDS)
                    .setScriptTimeout(90, TimeUnit.SECONDS);
        }
    }

    protected WebElement myFindElement(By xPath) {
        return USE_WAIT_UNTIL ? wait.until(ExpectedConditions.visibilityOfElementLocated(xPath)) : driver.findElement(xPath);
    }

    private void setDC() throws MalformedURLException {
        url = new URL((SECURE ? "https://" : "http://") + ":" + AK + "@" + HOST + ":" + PORT + "/wd/hub");
        if (!USE_ACCESS_KEY) {
            url = new URL((SECURE ? "https://" : "http://") + HOST + ":" + PORT + "/wd/hub");

            dc.setCapability("username", USER);
            dc.setCapability("password", PASS);
            dc.setCapability("projectName", PROJECT); //only required if your user has several projects assigned to it. Otherwise, exclude this capability.
        }
    }

    protected String sessionId = "", browserVersion = "", testName = "", browserName = "", platform = "", reportUrl = "", agentName = "", viewUrl = null;

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private static List<Node> excList = new ArrayList<>();
    private static int failCount = 0, testIndex = 0;
    protected URL url;
    protected RemoteWebDriver driver;
}

class Node {
    int count;
    String message;

    Node(int count, String message) {
        this.count = count;
        this.message = message;
    }
}
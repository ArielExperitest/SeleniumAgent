package FrameWork;

import Utils.CollectSupportDataAPI;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class TestBase extends Configuration implements Runnable {

    protected abstract void test() throws Exception;

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private static List<Node> excList = new ArrayList<>();
    private static int failCount = 0, testIndex = 0;
    private String sessionId = null, browserVersion = "", testName = "testName", browserType = "", platform = null, reportUrl = "Can't get report URL";

    @Override
    public void run() {
        setDC();
        try {
            log.info("Start test - " + dc);
            initDriver();
            test();
            log.info("Result - #" + (++testIndex) + " @PASS " + getSessionDetails());
        } catch (Exception e) {
            if (driver != null) {
                writeToLog(driver.getCapabilities(), e);
            } else {
                writeToLog(dc, e);
            }
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

    //Failed
    private void writeToLog(Capabilities capabilities, Exception exception) {
        log.error("Result - #" + (++testIndex) + " @FAIL " + getSessionDetails() + " " + capabilities, exception);

        countExc(platform + " " + browserType + " " + browserVersion + " - " + exception.getMessage().split("\n")[0]);
        if (testIndex % 5 == 0) {
            String CSDPath = testIndex + "_" + testName + "_" + START_TEST_TIME + ".zip";
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
            }
            if (find) break;
        }
        if (!find)
            excList.add(new Node(1, message));
        if (excList.size() % 5 == 0)
            excList.sort(Comparator.comparingInt(left -> left.count));
        log.info("############## Exception summary #####################");
        log.info("Number of fail tests: " + (++failCount) + " of " + testIndex);

        for (Node anExcList : excList)
            log.info(anExcList.count + " " + anExcList.message);
        log.info("###################################");
    }

    protected void sleep(int time) {
        try {
            if (browserType.equals(BrowserType.SAFARI))
                Thread.sleep(3 * time);
            else
                Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initDriver() {
        driver = new RemoteWebDriver(url, dc);
        this.browserVersion = this.driver.getCapabilities().getVersion();
        this.platform = String.valueOf(this.driver.getCapabilities().getPlatform());
        this.reportUrl = (String) this.driver.getCapabilities().getCapability("reportUrl");
        this.sessionId = (String) this.driver.getCapabilities().getCapability("sessionId");
        this.browserType = this.driver.getCapabilities().getBrowserName();
        if (!browserType.equals(BrowserType.IE)) {
            driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);
        }
        log.info("Done init driver - " + getSessionDetails());
    }

    private String getSessionDetails() {
        return platform + " " + browserVersion + " " + sessionId + " reportUrl: " + reportUrl;
    }
}

class Node {
    int count;
    String message;

    Node(int count, String message) {
        this.count = count;
        this.message = message;
    }
}
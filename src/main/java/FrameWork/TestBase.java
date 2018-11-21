package FrameWork;

import Utils.CollectSupportDataAPI;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class TestBase extends Configuration implements Runnable {
    protected TestBase() {
    }

    protected abstract void test() throws Exception;

    protected final Logger log = Logger.getLogger(this.getClass().getName());
    private static int testIndex = 0;
    private static List<Node> excList = new ArrayList<>();
    private static int failCount = 0;
    private boolean isTestPass = true;

    protected String testName = "testName";
    protected String browserType = "";
    protected String browserVersion = "";
    protected String platform = null;
    protected String reportUrl = "Can't get report URL";
    protected String sessionId = null;

    @Override
    public void run() {
        setDC();

        try {
            log.info("Start test - " + dc);
            initDriver();
            test();
            testIndex++;
            log.info("Result - #" + testIndex + " @PASS " + sessionId + " " + platform + " " + browserVersion + " reportUrl: " + reportUrl);
        } catch (Exception e) {
            if (!e.getMessage().contains("Go To Fail Test!!!")) {

                if (driver != null) {
                    writeToLog(driver.getCapabilities(), e);
                } else {
                    writeToLog(null, e);
                }
            }
        } finally {
            try {
                driver.quit();
            } catch (Exception e) {
                log.info("Fail to preform quit: " + sessionId + " " + platform + " " + browserVersion + " reportUrl: " + reportUrl + " exceptionMsg= " + e.getMessage());
            }
        }
    }

    //Failed
    private void writeToLog(Capabilities capabilities, Exception exception) {
        testIndex++;
        log.error("Result - #" + testIndex + " @FAIL " + sessionId + " " + platform + " reportUrl=" + reportUrl);
        log.error("--------- capabilities: " + capabilities, exception);

        countExc(platform + " " + browserType + " " + browserVersion + " - " + exception.getMessage().split("\n")[0]);

        if (testIndex % 5 == 0) {
            String CSDPath = testIndex + "_" + testName + "_" + START_TEST_TIME + ".zip";
            String reportPath = testIndex + "_" + testName + "_" + START_TEST_TIME + ".zip";
            new Thread(new CollectSupportDataAPI(CSDPath), testIndex + "_" + testName + "_" + START_TEST_TIME).start();
            log.info("Start downloading Collect Support Data =" + CSDPath);
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
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void sleepSafari(int time) {
        if (browserType.equals(BrowserType.SAFARI))
            sleep(time);
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
        log.info("Finish init driver - " + sessionId + " " + platform + " " + browserVersion + " reportUrl: " + reportUrl);
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
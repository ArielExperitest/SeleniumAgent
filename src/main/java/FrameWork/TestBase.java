package FrameWork;

import Utils.CollectSupportDataAPI;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.*;


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

    public TestBase(DesiredCapabilities desiredCapabilities) {
        super(desiredCapabilities);
    }

    @Override
    public void run() {
        setDC();

        try {
            log.info("Start test - " + dc);
            test();
            writeToLog();
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
                log.info("Fail to preform quit: " + platform + " " + sessionId + " " + testName + " " + reportUrl + " exceptionMsg= " + e.getMessage());
            }
        }
    }

    //Passed
    private void writeToLog() {
        testIndex++;
        log.info("Result - #" + testIndex + ". " + " @PASS " + sessionId + " " + platform + " " + testName + " reportPath=" + reportUrl);
    }

    //Failed
    private void writeToLog(Capabilities capabilities, Exception exception) {
        testIndex++;
        log.error("Result - #" + testIndex + " @FAIL " + sessionId + " " + platform + " " + testName + " reportUrl=" + reportUrl);

        if (capabilities != null)
            log.error("--------- capabilities " + capabilities + "", exception);
        else
            log.error("--------- capabilities are null", exception);

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

    protected void initProperty() {
        this.browserVersion = this.driver.getCapabilities().getVersion();
        this.platform = String.valueOf(this.driver.getCapabilities().getPlatform());
        this.reportUrl = (String) this.driver.getCapabilities().getCapability("reportUrl");
        this.sessionId = (String) this.driver.getCapabilities().getCapability("sessionId");
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
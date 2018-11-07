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
    private Exception exception = null;

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
            log.info("End test - " + sessionId + " " + dc);

        } catch (Exception e) {
            exception = e;
            if (!e.getMessage().contains("Go To Fail Test!!!")) {
                isTestPass = false;
            }
        } finally {
            if (Objects.nonNull(driver)) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    log.info("Fail to preform quit: " + platform + " " + testName + " " + reportUrl + " exceptionMsg= " + e.getMessage());
                }
                if (isTestPass) {
                    writeToLog();
                } else {
                    writeToLog(driver.getCapabilities());
                }
            } else {
                writeToLog(null);
            }
        }
    }

    //Passed
    private void writeToLog() {
        testIndex++;
        log.info("Result - #" + testIndex + ". " + " @PASS " + platform + " " + testName + " reportPath=" + reportUrl);
    }

    //Failed
    private void writeToLog(Capabilities capabilities) {
        testIndex++;
        String CSDPath = testIndex + "_" + testName + "_" + START_TEST_TIME + ".zip";
        String reportPath = testIndex + "_" + testName + "_" + START_TEST_TIME + ".zip";


        if (testIndex % 5 == 0) {
            new Thread(new CollectSupportDataAPI(CSDPath), testIndex + "_" + testName + "_" + START_TEST_TIME).start();
//            new Thread(new ReporterAttachment(reportPath, reportUrl)).start();
        }

        log.error("Result - #" + testIndex + " @FAIL " + sessionId + " " + platform + " " + testName + " reportUrl=" + reportUrl + " CSDZip=" + CSDPath + " reportZip=" + reportPath);
        if (Objects.nonNull(capabilities))
            log.error("capabilities - " + "-------- " + capabilities + "");
        else
            log.error("Result - " + "capabilities are null");
        log.error("Result - " + "----------Exception ", exception);

        countExc(browserType + " " + browserVersion + " - " + exception.getMessage().split("\n")[0]);
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
        log.info("Number of fail tests: " + (++failCount));

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
}

class Node {
    int count;
    String message;

    Node(int count, String message) {
        this.count = count;
        this.message = message;
    }
}
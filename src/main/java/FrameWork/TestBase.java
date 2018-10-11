package FrameWork;

import Utils.CollectSupportDataAPI;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.BrowserType;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public abstract class TestBase extends Configuration implements Runnable {
    protected abstract void test() throws Exception;

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private static int testIndex = 0;
    static ArrayList<Node> excList = new ArrayList<>();
    private boolean isTestPass = true;
    private Exception exception = null;

    protected String testName = "testName";
    protected String browserType = "";
    protected String platform = null;
    protected String reportUrl = "Can't get report URL";
    protected String sessionId = null;

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

        new Thread(new CollectSupportDataAPI(CSDPath), testIndex + "_" + testName + "_" + START_TEST_TIME).start();
//        executorReport.submit(new ReporterAttachment(reportPath, reportUrl));


        log.error("Result - #" + testIndex + " @FAIL " + sessionId + " " + platform + " " + testName + " reportUrl=" + reportUrl + " CSDZip=" + CSDPath + " reportZip=" + reportPath);
        if (Objects.nonNull(capabilities))
            log.error("capabilities - " + "-------- " + capabilities + "");
        else
            log.error("Result - " + "capabilities are null");
        log.error("Result - " + "----------Exception ", exception);

        countExc(exception.getMessage().split("\n")[0]);
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

        log.info("############## Exception summary #####################");
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
        try {
            if (browserType.equals(BrowserType.SAFARI))
                Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
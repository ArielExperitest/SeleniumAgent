package FrameWork;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;

import java.util.Objects;


public abstract class TestBase extends Configuration implements Runnable {
    protected abstract void test() throws Exception;

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private static int testIndex = 0;

    private int fail = 0;
    private int pass = 0;
    protected boolean isTestPass = true;
    protected String testName = "testName", browserType = "", platformName = null, reportUrl = "Can't get report URL";
    protected Exception exception = null;

    @Override
    public void run() {

        setDC();
        try {
            log.info("Start test");
            test();
            log.info("End test");

        } catch (Exception e) {
            exception = e;
            if (!e.getMessage().contains("Go To Fail Test!!!")) {
                isTestPass = false;
                log.error("Failed test with error " + e.getMessage());
                e.printStackTrace();
            }
        } finally {
            if (Objects.nonNull(driver)) {
                try {
                    driver.quit();
                } catch (Exception e) {
                    log.info("Fail to preform quit: " + platformName + " " + testName + " " + reportUrl + " exceptionMsg= " + e.getMessage());
                }
                if (isTestPass) {
                    writeToLog();
                } else {
                    writeToLog(driver.getCapabilities());
                }
            } else {
                log.info(exception.getMessage().split("\n")[0]);
                writeToLog(null);
            }
        }
    }

    //Passed
    private void writeToLog() {
        pass++;
        testIndex++;
        log.info("Result - " + testIndex + ". " + " PASS " + platformName + " " + testName + " reportPath=" + reportUrl);
    }

    //Failed
    private void writeToLog(Capabilities capabilities) {
        fail++;

//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.submit(new CollectSupportDataAPI(testIndex, testName, startTime));
//        executor.submit(new ReporterAttachment(testIndex, reportUrl, testName, startTime));
//        executor.shutdown();

        String CSDPath = testIndex + "_" + testName + "_" + START_TEST_TIME + ".zip";
        String reportPath = testIndex + "_" + testName + "_" + START_TEST_TIME + ".zip";
        testIndex++;
        log.info("Result - " + testIndex + ". " + " FAIL " + platformName + " " + testName + " reportUrl=" + reportUrl + " CSDZip=" + CSDPath + " reportZip=" + reportPath);
        if (capabilities != null)
            log.info("Result - " + "-------- " + capabilities.toString() + "");
        else
            log.info("Result - " + "capabilities are null");
        log.info("Result - " + "----------Exception ", exception);
//            log.info("----------Exception------------- "+e.get);
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

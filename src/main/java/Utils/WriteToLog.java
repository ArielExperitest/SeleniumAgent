package Utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static FrameWork.Configuration.*;

public class WriteToLog {
    private static final Logger log = Logger.getLogger("TEST REPORT");
    private  final String PROJECT_PATH = "C:\\SeleniumAgent\\SeleniumAgent\\";
    private static int testIndex = 0;
    private static int fail = 0;
    private static int pass = 0;


    //Passed
    public  void writeToOverall(String startTime, String endTime, String testName, String platformName, String reportUrl) {
        String testStatus = "PASS";
        pass++;
        testIndex++;
        log.info(String.format("%s %5s - %5s %5s %5s %30s %50s", testIndex + ". ", startTime, endTime, testStatus, platformName, testName, reportUrl));
    }

    //Failed
    public  void writeToOverall(String startTime, String endTime, String testName, String platformName, Exception e, Capabilities capabilities, String reportUrl) {
        String testStatus = "FAIL";
        fail++;

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new CollectSupportDataAPI(testIndex, testName, startTime));
        executor.submit(new ReporterAttachment(testIndex, reportUrl, testName, startTime));
        executor.shutdown();

        String CSDPath = PROJECT_PATH + "reports/CSD/" + testIndex + "_" + testName + "_" + startTime.replace(":", "-") + ".zip";
        String reportPath = PROJECT_PATH + testIndex + "_" + testName + "_" + startTime.replace(":", "-") + ".zip";
        testIndex++;
        log.info(String.format("%s %5s - %5s %5s %5s %30s %50s %70s %90s", testIndex + ". ", startTime, endTime, testStatus, platformName, testName, reportUrl, CSDPath, reportPath));
        if (capabilities != null)
            log.info("-------- " + capabilities.toString() + "");
        else
            log.info("capabilities are null");
        log.info("----------Exception ", e);
//            log.info("----------Exception------------- "+e.get);
    }
}

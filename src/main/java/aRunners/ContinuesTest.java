package aRunners;

import Test.grid.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContinuesTest {

    public static void main(String[] args) {
//        tester(15, 5000);
//        singlePerformanceTest(7, 25, BrowserType.SAFARI);
//        singleGoogleTest(6, 300, BrowserType.FIREFOX);
//        singleGoogleTest(6, 300, BrowserType.CHROME);
//        singlePerformanceTest(5, 10, BrowserType.FIREFOX);
//        singleLongLiteTest(11, 50, BrowserType.SAFARI);
//        singleBingTest(80, 140, BrowserType.CHROME);
//        after();
    }


    private static void tester(int numOfThreads, int numOfTest) {
        before(numOfThreads);

        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new BasicWikiTest(BrowserType.CHROME));
//            executorService.submit(new LongWikiTest(BrowserType.SAFARI));
            executorService.submit(new BasicWikiTest(BrowserType.FIREFOX));

            if (i % 10 == 0) {
                executorService.submit(new BasicWikiTest(BrowserType.EDGE));
            }
            if (i % 10 == 0) {
//                executorService.submit(new BasicWikiTest(BrowserType.SAFARI));
            }
            if (i % 10 == 0) {
                executorService.submit(new BasicWikiTest(BrowserType.IE));
            }
        }
    }

    private static void singleGoogleTest(int numOfThreads, int numOfTest, String browserName) {
        before(numOfThreads);
        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new BasicGoogleTest(browserName));
        }
        log.info("=========Finish upload tests");
    }

    private static void singleLogTest(int numOfThreads, int numOfTest, String browserName) {
        before(numOfThreads);
        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new LongTest(browserName));
        }
        log.info("=========Finish upload tests");
    }

    private static void singlePerformanceTest(int numOfThreads, int numOfTest, String browserName) {
        before(numOfThreads);
        for (int i = 0; i < numOfTest; i++) {

            executorService.submit(new PerformanceTest(browserName));
        }
        log.info("=========Finish upload tests");
    }

    private static void singleLongLiteTest(int nubOfWorkers, int numOfTasks, String browserName) {
        before(nubOfWorkers);
        for (int i = 0; i < numOfTasks; i++)
            executorService.submit(new LongLiteTest(browserName));

        log.info("=========Finish upload tests");
    }

    private static void singleBingTest(int nubOfWorkers, int numOfTasks, String browserName) {
        before(nubOfWorkers);
        for (int i = 0; i < numOfTasks; i++)
            executorService.submit(new BingTest(browserName));

        log.info("=========Finish upload tests");
    }


    private static void singleBasicTest(int numOfThreads, int numOfTest, String browserName) {
        before(numOfThreads);

        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new BasicGoogleTest(browserName));
        }

        log.info("=========Finish upload tests");
    }

    private static void before(int numOfThreads) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        log.info("=========Finish Create Executor Service");
        startTime = System.nanoTime();

    }

    private static void after() {
        executorService.shutdown();
        long currentTime = System.currentTimeMillis();
        int i = 0;
        while (true) {
            if (executorService.isTerminated()) break;
            else if (currentTime + 60_000 * i < System.currentTimeMillis()) {
                log.info("Not all threads are finished");
                i++;
            }

        }
        log.info("-------Finished all threads-------");
        double seconds = (System.nanoTime() - startTime) / 1_000_000_000.0;
        log.info("The test has run: " + (int) (seconds / 60) + " minuets");
    }

    private static final Logger log = Logger.getLogger("ContinuesTest");
    private static ExecutorService executorService;
    private static long startTime;
}

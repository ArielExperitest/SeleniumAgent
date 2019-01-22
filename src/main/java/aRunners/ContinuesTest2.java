package aRunners;

import Test.grid.BasicGoogleTest;
import Test.grid.LongTest;
import Test.grid.PerformanceTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContinuesTest2 {

    public static void main(String[] args) {
        tester(10, 2500);
//        singlePerformanceTest(7, 25, BrowserType.SAFARI);
//        singleGoogleTest(10, 3000, BrowserType.FIREFOX);
//        singleGoogleTest(10, 3000, BrowserType.CHROME);
//        singlePerformanceTest(10, 50, BrowserType.CHROME);
        after();
    }


    private static void tester(int numOfThreads, int numOfTest) {
        before(numOfThreads);

        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new PerformanceTest(BrowserType.CHROME));
            executorService.submit(new PerformanceTest(BrowserType.FIREFOX));

            if (i % 10 == 0) {
//                executorService.submit(new PerformanceTest(BrowserType.EDGE));
            }
            if (i % 100 == 0) {
                executorService.submit(new PerformanceTest(BrowserType.SAFARI));
            }
            if (i % 10 == 0) {
//                executorService.submit(new PerformanceTest(BrowserType.IE));
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
        log.info("=========Finish upload tests");
        executorService.shutdown();
        while (true) if (executorService.isTerminated()) break;
        log.info("-------Finished all threads-------");
        double seconds = (System.nanoTime() - startTime) / 1_000_000_000.0;
        log.info("The test has run: " + (int) (seconds / 60) + " minuets");

    }

    private static final Logger log = Logger.getLogger("ContinuesTest");
    private static ExecutorService executorService;
    private static long startTime;
}

package aRunners;

import Test.grid.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContinuesTest {

    //16, 405 seconds >>>>true
    //  9, 9.4 minutes >>>>>false

    public static void main(String[] args) {
        before(10);
//        tester(20, 50);
        singleTest(3, BrowserType.FIREFOX, new LongTest());
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
//                executorService.submit(new PerformanceTest(BrowserType.SAFARI));
            }
            if (i % 10 == 0) {
//                executorService.submit(new PerformanceTest(BrowserType.IE));
            }
        }
    }

    private static void singleTest(int numOfTest, String browserName, Object object) {
        for (int i = 0; i < numOfTest; i++) {
            if (object instanceof PerformanceTest) {
                executorService.submit(new PerformanceTest(browserName));
            } else if (object instanceof LongTest) {
                executorService.submit(new LongTest(browserName));
            } else if (object instanceof BasicWikiTest) {
                executorService.submit(new BasicWikiTest(browserName));
            } else if (object instanceof BasicGoogleTest) {
                executorService.submit(new BasicGoogleTest(browserName));
            }
        }
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


//    private static void singleLogTest(int numOfThreads, int numOfTest, String browserName) {
//        before(numOfThreads);
//        for (int i = 0; i < numOfTest; i++) {
//            executorService.submit(new LongTest(browserName));
//        }
//        log.info("=========Finish upload tests");
//    }
//    private static void singlePerformanceTest(int numOfThreads, int numOfTest, String browserName) {
//        before(numOfThreads);
//        for (int i = 0; i < numOfTest; i++) {
//
//            executorService.submit(new PerformanceTest(browserName));
//        }
//        log.info("=========Finish upload tests");
//    }
//    private static void singleBasicTest(int numOfThreads, int numOfTest, String browserName) {
//        before(numOfThreads);
//
//        for (int i = 0; i < numOfTest; i++) {
//            executorService.submit(new BasicGoogleTest(browserName));
//        }
//
//        log.info("=========Finish upload tests");
//    }

}

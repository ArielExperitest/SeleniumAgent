package FrameWork;

import Test.grid.*;
import Test.manual.OpenManualBrowserViaCloud;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Suits {
    private static final Logger log = Logger.getLogger("Suits");

    public static void main(String[] args) {
//        checkAllBrowsers(1, 5);

//        testAgent(3, 10);
//        test(12, 10, 15, BrowserType.SAFARI);
//        testManual(4, 5, 3, BrowserType.CHROME);
//        testSuit(10);
//        testSuit(3, 2, BrowserType.CHROME);
        testAgent(10, 4);
    }

    private static void checkAllBrowsers(int numOfSetReturns, int numOfSet) {

        for (int i = 0; i < numOfSetReturns; i++) {
            for (int j = 0; j < numOfSet; j++) {
                new VersionCheckOneByOne(3);
            }
            log.info("=========Finish Agent Suits #" + i + "=========");
        }
    }

    private static void testManual(int numOfThreads, int numOfSetReturns, int numOfSet, String browserType) {
        ExecutorService executor;
        int i;
        for (int j = 0; j < numOfSetReturns; j++) {
            executor = Executors.newFixedThreadPool(numOfThreads);
            for (i = 0; i < numOfSet; i++) {
                executor.execute(new OpenManualBrowserViaCloud(browserType));
            }
            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            log.info("=========Finish Safari Suits #" + i + "=========");
        }
        log.info("Finished all threads");

    }


    public static void test(int numOfThreads, int numOfSetReturns, int numOfSet, String browserType) {
        ExecutorService executor;
        int i;
        for (int j = 0; j < numOfSetReturns; j++) {
            executor = Executors.newFixedThreadPool(numOfThreads);
            for (i = 0; i < numOfSet; i++) {
                executor.execute(new PerformanceTest(browserType));
            }
            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            log.info("=========Finish Safari Suits #" + i + "=========");
        }
        log.info("Finished all threads");
    }

    private static void testAgent(int numOfSetReturns, int numOfSet) {
        testAgent(numOfSet * 3 + 1, numOfSetReturns, numOfSet);
    }

    private static void testAgent(int numOfThreads, int numOfSetReturns, int numOfSet) {
        ExecutorService executor;

        for (int i = 0; i < numOfSetReturns; i++) {
            executor = Executors.newFixedThreadPool(numOfThreads);

            for (int j = 0; j < numOfSet; j++) {
                executor.execute(new PerformanceTest(BrowserType.CHROME));
                executor.execute(new PassTest(BrowserType.SAFARI));
                executor.execute(new PerformanceTest(BrowserType.FIREFOX));
//                executor.execute(new PerformanceTest(BrowserType.EDGE));
            }
//            executor.execute(new PerformanceTest(BrowserType.IE));
//            executor.execute(new PerformanceTest(BrowserType.IE));

            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            log.info("=========Finish Agent Suits #" + i + "=========");
        }
        log.info("-------Finished all threads-------");
    }

    public static void testSuit(int numOfThreads, int numOfSetReturns, String browserType) {
        ExecutorService executor;
        int i;
        for (int j = 0; j < numOfSetReturns; j++) {
            executor = Executors.newFixedThreadPool(numOfThreads);

//            executor.execute(new PerformanceTest("", browserType));
            executor.execute(new AccessKeyTest(browserType));
            executor.execute(new PassTest(browserType));
            executor.execute(new FailTest(browserType));
            executor.execute(new MinimumCapabilityTest(browserType));
            new VersionCheckOneByOne(1);

            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            log.info("=========Finish Safari Suits #" + j + "=========");
        }
        log.info("Finished all threads");
    }

}

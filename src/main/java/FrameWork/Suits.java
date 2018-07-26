package FrameWork;

import Test.grid.*;
import Test.manual.OpenManualBrowserViaCloud;
import Utils.WriteToLog;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Suits {
    private static final Logger log = Logger.getLogger("Suits");

    public static void main(String[] args) {

//        testAgent(3, 3);
//        test(12, 10, 15, BrowserType.SAFARI);
//        testManual(2, 100, 2, BrowserType.CHROME);
        testSuit(10);
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
                executor.execute(new PerformanceTest(BrowserType.FIREFOX));
//                executor.execute(new PerformanceTest(BrowserType.SAFARI));
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

            executor.execute(new PerformanceTest(browserType));
            executor.execute(new AccessKeyTest(browserType));
            executor.execute(new PassTest(browserType));
            executor.execute(new FailTest(browserType));
            executor.execute(new MinimumCapabilityTest(browserType));
            executor.execute(new VersionCheckOneByOne());

            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            log.info("=========Finish Safari Suits #" + j + "=========");
        }
        log.info("Finished all threads");

    }

    private static void testSuit(int numOfSetReturns) {
        ExecutorService executor;
        int i;
        for (int j = 0; j < numOfSetReturns; j++) {
            executor = Executors.newFixedThreadPool(8);

            executor.execute(new VersionCheckOneByOne());

//            executor.execute(new PerformanceTest(BrowserType.CHROME));
            executor.execute(new AccessKeyTest(BrowserType.CHROME));
//            executor.execute(new PassTest(BrowserType.CHROME));
//            executor.execute(new FailTest(BrowserType.CHROME));
            executor.execute(new MinimumCapabilityTest(BrowserType.CHROME));

//            executor.execute(new PerformanceTest(BrowserType.FIREFOX));
            executor.execute(new AccessKeyTest(BrowserType.FIREFOX));
//            executor.execute(new PassTest(BrowserType.FIREFOX));
//            executor.execute(new FailTest(BrowserType.FIREFOX));
            executor.execute(new MinimumCapabilityTest(BrowserType.FIREFOX));

            executor.execute(new PerformanceTest(BrowserType.SAFARI));
            executor.execute(new AccessKeyTest(BrowserType.SAFARI));
//            executor.execute(new PassTest(BrowserType.SAFARI));
            executor.execute(new FailTest(BrowserType.SAFARI));
            executor.execute(new MinimumCapabilityTest(BrowserType.SAFARI));
            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            log.info("=========Finish Safari Suits #" + j + "=========");
        }
        log.info("Finished all threads");

    }
}

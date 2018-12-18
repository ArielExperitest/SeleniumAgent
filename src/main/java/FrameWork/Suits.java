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
        testAgent(3, 10);
//        testEdge(1, 1, 1);
    }

    private static void checkAllBrowsers(int numOfReturns, int numOfSet) {

        for (int i = 0; i < numOfReturns; i++) {
            for (int j = 0; j < numOfSet; j++) {
                new VersionCheckOneByOne(3);
            }
            log.info("=========Finish Agent Suits #" + i + "=========");
        }
    }

    private static void testManual(int numOfThreads, int numOfReturns, int numOfSet, String browserType) {
        ExecutorService executor;
        int i;
        for (int j = 0; j < numOfReturns; j++) {
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


    public static void test(int numOfThreads, int numOfReturns, int numOfSet, String browserType) {
        ExecutorService executor;
        int i;
        for (int j = 0; j < numOfReturns; j++) {
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

    private static void testAgent(int numOfReturns, int numOfSet) {
        testAgent(numOfSet * 3 + 1, numOfReturns, numOfSet);
    }

    private static void testEdge(int numOfThreads, int numOfReturns, int numOfSet) {
        ExecutorService executor;

        for (int i = 0; i < numOfReturns; i++) {
            executor = Executors.newFixedThreadPool(numOfThreads);

            for (int j = 0; j < numOfSet; j++) {
                new Thread(new PerformanceTest(BrowserType.EDGE)).start();
            }
            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            log.info("=========Finish Agent Suits #" + i + "=========");
        }
        log.info("-------Finished all threads-------");
    }


    private static void testAgent(int numOfThreads, int numOfReturns, int numOfSet) {
        ExecutorService executor;

        for (int i = 0; i < numOfReturns; i++) {
            executor = Executors.newFixedThreadPool(numOfThreads);

            for (int j = 0; j < numOfSet; j++) {
                executor.execute(new PerformanceTest(BrowserType.CHROME));
                executor.execute(new PerformanceTest(BrowserType.SAFARI));
                executor.execute(new PerformanceTest(BrowserType.FIREFOX));
            }
//            executor.execute(new PerformanceTest(BrowserType.EDGE));
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

    public static void testSuit(int numOfThreads, int numOfReturns, String browserType) throws InterruptedException {
        ExecutorService executor;
        int i;
        for (int j = 0; j < numOfReturns; j++) {
            executor = Executors.newFixedThreadPool(numOfThreads);

//            executor.execute(new PerformanceTest("", browserName));
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

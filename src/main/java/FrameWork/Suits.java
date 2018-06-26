package FrameWork;

import Test.grid.PerformanceTest;
import Test.grid.VersionCheckOneByOne;
import Utils.WriteToLog;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Suits {

    public static void main(String[] args) {

        WriteToLog.writeFirstTime();

        testAgent(25, 10, 10);

//        fastTests(30, 1);
//        testIE(10, 1);
//        testSafari(10, 6, 8);
//        minimumTests(1, 1);
//        longRun(3, 5);
//        checkVersion(15, 10);
//        testIE(10, 11);
    }


    private static void testAgent(int numOfThreads, int numOfSetReturns, int numOfSet) {
        ExecutorService executor;

        for (int i = 0; i < numOfSetReturns; i++) {
            executor = Executors.newFixedThreadPool(numOfThreads);

            for (int j = 0; j < numOfSet; j++) {
//                executor.execute(new PerformanceTest(BrowserType.CHROME));
                executor.execute(new PerformanceTest(BrowserType.FIREFOX));
            }
//            executor.execute(new PerformanceTest(BrowserType.SAFARI));
//            executor.execute(new PerformanceTest(BrowserType.IE));
//            executor.execute(new PerformanceTest(BrowserType.IE));

            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            WriteToLog.writeStringToLog("=========Finish Agent Suits #" + i + "=========");

        }


        System.out.println("Finished all threads");
    }

    private static void testIE(int numOfThreads, int numOfReturns) {
        ExecutorService executor;
        for (int i = 1; i < numOfReturns + 1; i++) {
            executor = Executors.newFixedThreadPool(numOfThreads);
            executor.execute(new PerformanceTest(BrowserType.IE));
//            executor.execute(new FailTest.IE());
//            executor.execute(new PassTest(s));
//            executor.execute(new AccessKeyTest(s));
//            executor.execute(new PerformanceTest(s));
//            executor.execute(new SpeedTestGenerateReport.IE());
//            executor.execute(new SpeedTestScreenshots.IE());


            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            WriteToLog.writeStringToLog("=========Finish IE Suits #" + i + "=========");
        }
    }

    public static void testSafari(int numOfThreads, int numOfSetReturns, int numOfSet) {
        ExecutorService executor;
        for (int j = 0; j < numOfSetReturns; j++) {
            for (int i = 0; i < numOfSet; i++) {
                executor = Executors.newFixedThreadPool(numOfThreads);
                executor.execute(new PerformanceTest(BrowserType.SAFARI));

//            executor.execute(new FailTest.Safari());
//            executor.execute(new PassTest(BrowserType.SAFARI));
//            executor.execute(new AccessKeyTest(BrowserType.SAFARI));


                executor.shutdown();
                while (true) {
                    if (executor.isTerminated()) break;
                }
                WriteToLog.writeStringToLog("=========Finish Safari Suits #" + i + "=========");
            }
        }
    }

    public static void checkVersion(int numOfThreads, int numOfReturns) {
        ExecutorService executor;

        for (int i = 0; i < numOfReturns; i++) {
            executor = Executors.newFixedThreadPool(numOfThreads);
            executor.execute(new VersionCheckOneByOne());


            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            WriteToLog.writeStringToLog("=========Finish Safari Suits #" + i + "=========");
        }

    }

    public static void longRun(int numOfThreads, int numOfReturns) {
        ExecutorService executor;

        for (int i = 0; i < numOfReturns; i++) {
            executor = Executors.newFixedThreadPool(numOfThreads);
            executor.execute(new PerformanceTest(BrowserType.FIREFOX));
            executor.execute(new PerformanceTest(BrowserType.CHROME));
//            executor.execute(new PerformanceTest(BrowserType.IE));
            executor.execute(new PerformanceTest(BrowserType.SAFARI));

            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }

            WriteToLog.writeStringToLog("=========Finish  Suits #" + i + "=========");
        }

    }
}

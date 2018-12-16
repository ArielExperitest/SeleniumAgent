package FrameWork;

import Test.grid.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContinuesTest {
    private static final Logger log = Logger.getLogger("ContinuesTest");
    private static ExecutorService executorService;


    public static void main(String[] args) {
        tester(45, 300);
//        singleTest(15, 300, BrowserType.EDGE);
//        singleTest(4, 50 , BrowserType.IE);
//        System.out.println(strings.toString());
    }


    private static void tester(int numOfThreads, int numOfTest) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        log.info("=========Finish Create Executor Service");

        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new PerformanceTest(BrowserType.CHROME));
            executorService.submit(new PerformanceTest(BrowserType.FIREFOX));

            if (i % 100 == 0) {
//                executorService.submit(new PerformanceTest(BrowserType.EDGE));
            }
            if (i % 100 == 0) {
                executorService.submit(new PerformanceTest(BrowserType.SAFARI));
            }
            if (i % 100 == 0) {
                executorService.submit(new PerformanceTest(BrowserType.IE));
            }
        }
//        executorService.submit(new PerformanceTest(BrowserType.IE));
        log.info("=========Finish upload tests");
        long startTime = System.currentTimeMillis();


        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) break;
        }
        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) / 1000 + " seconds");
        log.info("-------Finished all threads-------");
    }

    private static void singleTest(int numOfThreads, int numOfTest, String browserType) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        log.info("=========Finish Create Executor Service");

        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new NativePopupsTest(browserType));
        }
        log.info("=========Finish upload tests");
        long startTime = System.currentTimeMillis();
        executorService.shutdown();

        while (true) if (executorService.isTerminated()) break;
        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) / 1000 + " seconds");
        log.info("-------Finished all threads-------");
    }
}

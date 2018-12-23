package aRunners;

import Test.grid.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContinuesTest {
    private static final Logger log = Logger.getLogger("ContinuesTest");
    private static ExecutorService executorService;


    public static void main(String[] args) {
        tester(5, 50);
//        singlePerformanceTest(5, 40, BrowserType.FIREFOX);
//        singleBasicTest(15, 10000, BrowserType.FIREFOX);
//        System.out.println(strings.toString());
    }

    private static void tester(int numOfThreads, int numOfTest) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        log.info("=========Finish Create Executor Service");

        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new PerformanceTest(BrowserType.CHROME));
            executorService.submit(new PerformanceTest(BrowserType.FIREFOX));

            if (i % 10 == 0) {
                executorService.submit(new PerformanceTest(BrowserType.EDGE));
            }
            if (i % 100 == 0) {
//                executorService.submit(new PerformanceTest(BrowserType.SAFARI));
            }
            if (i % 10 == 0) {
                executorService.submit(new PerformanceTest(BrowserType.IE));
            }
        }
        log.info("=========Finish upload tests");

        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) break;
        }
        log.info("-------Finished all threads-------");
    }

    private static void singlePerformanceTest(int numOfThreads, int numOfTest, String browserName) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        log.info("=========Finish Create Executor Service");

        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new PerformanceTest(browserName));
        }
        log.info("=========Finish upload tests");
        executorService.shutdown();

        while (true) if (executorService.isTerminated()) break;
        log.info("-------Finished all threads-------");
    }

    private static void singleBasicTest(int numOfThreads, int numOfTest, String browserName) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        log.info("=========Finish Create Executor Service");

        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new BasicTest(browserName));
        }
        log.info("=========Finish upload tests");
        executorService.shutdown();

        while (true) if (executorService.isTerminated()) break;
        log.info("-------Finished all threads-------");
    }
}

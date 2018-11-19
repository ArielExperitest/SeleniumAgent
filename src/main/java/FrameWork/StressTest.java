package FrameWork;

import Test.grid.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StressTest {
    private static final Logger log = Logger.getLogger("StressTest");
    private static ExecutorService executorService;
    private static ArrayList<Long> strings;


    public static void main(String[] args) {
        strings = new ArrayList<>();

        tester(92, 300);

        System.out.println(strings.toString());
    }

    private static void tester(int numOfThreads, int numOfTest) {
        executorService = Executors.newFixedThreadPool(numOfThreads);
        log.info("=========Finish Create Executor Service");

        for (int i = 0; i < numOfTest; i++) {
            executorService.submit(new PerformanceTest(BrowserType.CHROME));
            executorService.submit(new PerformanceTest(BrowserType.FIREFOX));

            if (i % 5 == 0) {
                executorService.submit(new PerformanceTest(BrowserType.EDGE));
            }
            if (i % 10 == 0)
                executorService.submit(new PerformanceTest(BrowserType.SAFARI));
        }
//        executorService.submit(new PerformanceTest(BrowserType.IE));
//        executorService.submit(new PerformanceTest(BrowserType.EDGE));
//        executorService.submit(new PerformanceTest(BrowserType.IE));
        log.info("=========Finish upload tests");
        long startTime = System.currentTimeMillis();


        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) break;
        }
        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) / 1000 + " seconds");
        strings.add((endTime - startTime) / 1000);
        log.info("-------Finished all threads-------");
    }
}

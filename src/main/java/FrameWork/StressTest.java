package FrameWork;

import Test.grid.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StressTest {
    private static final Logger log = Logger.getLogger("StressTest");
    private static ExecutorService executorService;

    public static void main(String[] args) {
        tester(30, 1500);
    }


    private static void tester(int numOfParallelTest, int numOfTest) {
        executorService = Executors.newFixedThreadPool(numOfParallelTest);
        log.info("=========Finish Create Executor Service");

        for (int i = 0; i < numOfTest; i++) {
            executorService.execute(new PerformanceTest(BrowserType.CHROME));
            executorService.execute(new PerformanceTest(BrowserType.FIREFOX));

            if (i % 3 == 0)
                executorService.execute(new PerformanceTest(BrowserType.SAFARI));
//            executorService .execute(new PerformanceTest(BrowserType.EDGE));
            if (i % 500 == 0)
                executorService.execute(new PerformanceTest(BrowserType.IE));

        }
        log.info("=========Finish upload tests");

        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) break;
        }
        log.info("-------Finished all threads-------");
    }
}

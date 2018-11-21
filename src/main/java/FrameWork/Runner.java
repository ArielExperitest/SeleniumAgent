package FrameWork;

import Test.grid.Basic;
import org.openqa.selenium.remote.BrowserType;


public class Runner {


    public static void main(String[] args) {

        int j = 0;
        while (j < 100) {
//            new Thread(new WatchCloudTest(BrowserType.CHROME, "admin", "Experitest2012")).start();
//            new Thread(new WatchCloudTest(BrowserType.FIREFOX, "https://qa-win2016.experitest.com", "diklaLDAP", "Experitest2012")).start();

//            CollectSupportDataAPI collectSupportDataAPI = new CollectSupportDataAPI();
//            collectSupportDataAPI.downloadCSD(1,"aaa","166");
//            new Thread(new OpenManualBrowserViaCloud(BrowserType.CHROME)).start();
//            new Thread(new SeleniumScreenshot(BrowserType.FIREFOX)).start();
//            new Thread(new PerformanceTest(BrowserType.IE)).start();
//            new Thread(new PerformanceTest(BrowserType.FIREFOX)).start();
//            new Thread(new PerformanceTest(BrowserType.CHROME)).start();
//            new Thread(new PassTest(BrowserType.EDGE)).start();
//            new Thread(new FaileTest2(BrowserType.CHROME)).start();
            new Thread(new Basic(BrowserType.IE)).start();
//            new Thread(new LongTest(BrowserType.EDGE)).start();
//            new VersionCheckOneByOne(1);


//            new VersionCheckOneByOne(3);
//            new Thread(new PassTest("MicrosoftEdge")).start();

//            new Thread(new VersionCheckOneByOne()).start();
//            new Thread(new CheckAllBrowserVersion()).start();

//            new Thread(new VersionCheck(BrowserType.CHROME,"67")).start();
//            new Thread(new PassTest(BrowserType.SAFARI)).start();

//            new Thread(new VersionCheck.Firefox()).start();

//            new Thread(new VersionCheck(BrowserType.FIREFOX, "60.0.2")).start();
//            new Thread(new VersionCheck(BrowserType.FIREFOX, "58.0.1")).start();
//
//            new Thread(new VersionCheck.IE("11")).start();
//            new Thread(new TimeTestYoram()).start();

//            new Thread(new Basic(BrowserType.FIREFOX)).start();

//            new Thread(new PerformanceTest(BrowserType.CHROME)).start();
//
//            new Thread(new AccessKeyTest(BrowserType.FIREFOX)).start();
//
//            new Thread(new PassTest(BrowserType.CHROME)).start();

//            new Thread(new PerformanceTest(BrowserType.CHROME)).start();

            System.out.println(">>>>>>>>>>>>>>>>> Test #" + j + " <<<<<<<<<<<<<<<<<<<<<<<");
            j++;


//            if (j % 3 == 0)
//                Thread.sleep(5 * 60 * 1000);
        }
    }
}
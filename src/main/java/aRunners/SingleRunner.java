package aRunners;

import Test.grid.*;
import org.openqa.selenium.remote.BrowserType;


public class SingleRunner {


    public static void main(String[] args) {

        int j = 0;
        while (j < 1) {
//            new Thread(new PassTest(BrowserType.CHROME)).start();
//            CollectSupportDataAPI collectSupportDataAPI = new CollectSupportDataAPI();
//            collectSupportDataAPI.downloadCSD(1,"aaa","166");
//            new Thread(new OpenManualBrowserViaCloud(BrowserType.CHROME)).start();
//            new Thread(new SeleniumScreenshot(BrowserType.FIREFOX)).start();
//            new Thread(new PerformanceTest(BrowserType.IE)).start();
//            new Thread(new BasicWikiTest(BrowserType.FIREFOX)).start();
            new Thread(new BasicWikiTest(BrowserType.CHROME)).start();
//            new Thread(new MinimumCapabilityTest()).start();
//            new VersionCheckOneByOne(10);
//            new Thread(new AccessKeyTest(BrowserType.CHROME)).start();
//            new Thread(new UploadFile(BrowserType.SAFARI)).start();
//            new Thread(new NativePopupsTest(BrowserType.IE)).start();
//            new Thread(new BasicGoogleTest(BrowserType.CHROME)).start();
//            new Thread(new LongTest(BrowserType.EDGE)).start();
//            new VersionCheckOneByOne(1);

//            new VersionCheckOneByOne(3);
//            new Thread(new PassTest("MicrosoftEdge")).start();

//            new Thread(new VersionCheckOneByOne()).start();
//            new Thread(new CheckAllBrowserVersion()).start();

//            new Thread(new VersionCheck(BrowserType.CHROME,"67")).start();

//            new Thread(new VersionCheck.Firefox()).start();

//            new Thread(new VersionCheck(BrowserType.FIREFOX, "60.0.2")).start();
//            new Thread(new VersionCheck(BrowserType.FIREFOX, "58.0.1")).start();
//
//            new Thread(new VersionCheck.IE("11")).start();
//            new Thread(new TimeTestYoram()).start();

            System.out.println(">>>>>>>>>>>>>>>>> Loop #" + j + " <<<<<<<<<<<<<<<<<<<<<<<");
            j++;
        }
    }
}
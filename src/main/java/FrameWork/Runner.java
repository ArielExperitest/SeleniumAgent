package FrameWork;

import Test.PerformanceTest;
import Test.PassTest;
import Utils.WriteToLog;
import org.openqa.selenium.remote.BrowserType;


public class Runner {


    public static void main(String[] args) {
        WriteToLog.writeFirstTime();

        int j = 0;
        while (j < 1) {

//            new Thread(new SaveScreenshot(BrowserType.FIREFOX)).start();
//            new Thread(new SaveScreenshot(BrowserType.CHROME)).start();
//            new Thread(new SaveScreenshot(BrowserType.IE)).start();

//            new Thread(new VersionCheck(BrowserType.CHROME,"67")).start();
//            new Thread(new VersionCheck(BrowserType.CHROME,"66")).start();
//            new Thread(new PassTest(BrowserType.FIREFOX)).start();
//            new Thread(new PassTest(BrowserType.CHROME)).start();
//            new Thread(new PassTest(BrowserType.IE)).start();
//            new Thread(new PassTest(BrowserType.SAFARI)).start();

//            new Thread(new VersionCheck.Firefox()).start();
//            VersionCheckChrome();
//            new Thread(new VersionCheck(BrowserType.SAFARI, "11.1")).start();
//            new Thread(new VersionCheck.IE("11")).start();
//            new Thread(new VersionCheckOneByOne()).start();
//            new Thread(new TimeTestYoram()).start();

//            new Thread(new Basic(BrowserType.CHROME)).start();
//            new Thread(new Basic(BrowserType.FIREFOX)).start();
//            new Thread(new Basic(BrowserType.SAFARI)).start();
//            new Thread(new Basic(BrowserType.IE)).start();
//
//            new Thread(new PerformanceTest(BrowserType.CHROME)).start();
//            new Thread(new PerformanceTest(BrowserType.FIREFOX)).start();
//            new Thread(new PerformanceTest(BrowserType.SAFARI)).start();
            new Thread(new PerformanceTest(BrowserType.IE)).start();
//
//            new Thread(new AccessKeyTest(BrowserType.IE)).start();
//            new Thread(new AccessKeyTest(BrowserType.FIREFOX)).start();
//            new Thread(new AccessKeyTest(BrowserType.SAFARI)).start();
//            new Thread(new AccessKeyTest(BrowserType.IE)).start();
//
//            new Thread(new PassTest(BrowserType.CHROME)).start();
//            new Thread(new PassTest(BrowserType.FIREFOX)).start();
//            new Thread(new PassTest(BrowserType.SAFARI)).start();
//            new Thread(new PassTest(BrowserType.IE)).start();
//
//
//            new Thread(new PerformanceTest(BrowserType.CHROME)).start();
//            new Thread(new PerformanceTest(BrowserType.FIREFOX)).start();
//            new Thread(new PerformanceTest(BrowserType.SAFARI)).start();
//            new Thread(new PerformanceTest(BrowserType.IE)).start();

            System.out.println(">>>>>>>>>>>>>>>>> Test #" + j + " <<<<<<<<<<<<<<<<<<<<<<<");
            j++;


//            if (j % 3 == 0)
//                Thread.sleep(5 * 60 * 1000);
        }
    }

    public void passTest(int numberOfChrome, int numberOfFirefox, int numberOfIE) {
        int i = 0;
        while (++i < numberOfChrome)
            new Thread(new PassTest(BrowserType.CHROME)).start();
        i = 0;
        while (++i < numberOfFirefox)
            new Thread(new PassTest(BrowserType.FIREFOX)).start();
        i = 0;
        while (++i < numberOfIE)
            new Thread(new PassTest(BrowserType.IE)).start();
    }


}
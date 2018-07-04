package FrameWork;

import Utils.WriteToLog;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class TestBase extends Configuration implements Runnable {

    protected abstract void test() throws Exception;

    protected String startTime = "", endTime = "";
    protected boolean isTestPass = true;
    protected String reportUrl = "Can't get report URL";
    protected String testName = "testName";
    protected String platformName = null;
    protected String browserType = "";
    protected Exception exception = null;

    @Override
    public void run() {

        setDC();
        try {
            startTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(System.currentTimeMillis()));
            test();
            endTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));

        } catch (Exception e) {
            exception = e;
            if (!e.getMessage().contains("Go To Fail Test!!!")) {
                isTestPass = false;
//                e.printStackTrace();
            }
        } finally {
            if (driver != null) {

                reportUrl = (String) driver.getCapabilities().getCapability("reportUrl");
                platformName = String.valueOf(driver.getCapabilities().getPlatform());


                if (isTestPass) {
                    WriteToLog.writeToOverall(startTime, endTime, testName, platformName, reportUrl);
                } else {
                    WriteToLog.writeToOverall(startTime, endTime, testName, platformName, exception, driver.getCapabilities(), reportUrl);
                }
                try {
                    driver.quit();
                } catch (Exception e) {
                    System.out.println("Fail to preform quit: " + platformName + reportUrl + testName);
                }
            } else {
                System.out.println(exception.getMessage().split("\n")[0]);
                WriteToLog.writeToOverall(startTime, endTime, testName, platformName, exception, null, "Test failed, driver is null because " + exception.getMessage().split("\n")[0]);
            }
        }
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

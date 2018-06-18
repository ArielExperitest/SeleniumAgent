package Test;

import FrameWork.TestBase;
import Utils.WriteToLog;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ariel.hazan on 10-Dec-17
 */
public class MinimumCapabilityTest extends TestBase {

    public MinimumCapabilityTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void run() {

        setMinimumDC();
        try {
            startTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(System.currentTimeMillis()));
            test();
            endTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));

        } catch (Exception e) {
            exception = e;
            isTestPass = false;
        } finally {
            if (driver != null) {

                reportUrl = (String) driver.getCapabilities().getCapability("reportUrl");
                platformName = String.valueOf(driver.getCapabilities().getPlatform());


                if (isTestPass) {
                    WriteToLog.writeToOverall(startTime, endTime, testName, platformName, reportUrl);
                } else {
                    WriteToLog.writeToOverall(startTime, endTime, testName, platformName, exception, driver.getCapabilities(), reportUrl);
                }
                driver.quit();
            } else {
                System.out.println(exception.getMessage().split("\n")[0]);
                WriteToLog.writeToOverall(startTime, endTime, testName, platformName, "Test failed, driver is null because " + exception.getMessage().split("\n")[0]);
            }

        }
    }

    @Override
    public void test() {
        String startTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
        boolean isTestPass = true;
        String testName = (String) dc.getCapability("testName");
        String reportUrl = "Can't get report URL";
        Capabilities capabilities;
        String platformName=null;
        try {
            driver = new RemoteWebDriver(url, dc);
            driver.get("https://www.ynet.co.il");
            driver.get("https://www.google.co.il");
            driver.get("https://www.cnn.com");

            platformName = (String) dc.getCapability("platformName");
            endTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            if (driver != null) {
                capabilities = driver.getCapabilities();
                reportUrl = (String) capabilities.getCapability("reportUrl");
                WriteToLog.writeToOverall(startTime,endTime, testName, platformName, e, capabilities, reportUrl);
            } else {
                WriteToLog.writeToOverall(startTime,endTime, testName, platformName, e, null, reportUrl);
            }
            isTestPass = false;
        } finally {
            if (driver != null) {
                reportUrl = (String) driver.getCapabilities().getCapability("reportUrl");

                if (isTestPass)
                    WriteToLog.writeToOverall(startTime,endTime, testName, platformName, reportUrl);

                driver.quit();
            } else {
                WriteToLog.writeToOverall(startTime,endTime, testName, platformName, "driver is null");
            }
        }
    }
}

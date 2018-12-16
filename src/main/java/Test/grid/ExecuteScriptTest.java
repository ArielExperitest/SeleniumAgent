package Test.grid;

import FrameWork.TestBase;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExecuteScriptTest extends TestBase {

    public ExecuteScriptTest(String browserType) {
        this.browserType = browserType;
        testName = this.getClass().getSimpleName() + " Test " + browserType;
        dc.setCapability("testName", testName);
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);
    }

    @Override
    public void test() {
        driver = new RemoteWebDriver(url, dc);
        System.out.println((String) driver.getCapabilities().getCapability("reportUrl"));
        driver.get("https://www.google.com");


        try {
            int i = 1 / 0;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String sStackTrace = sw.toString(); // stack trace as a stringsStackTrace
            driver.executeScript("seetest:client.setReportStatus(\"true\",\"this my massage\",\"" + sStackTrace + "\")");
        }
        driver.get("https://www.google.com");


    }
}

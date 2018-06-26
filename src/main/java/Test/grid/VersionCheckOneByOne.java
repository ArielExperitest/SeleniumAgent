package Test.grid;

import FrameWork.Credentials;
import FrameWork.TestBase;
import Utils.WriteToLog;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static FrameWork.Credentials.*;


/**
 * Created by ariel.hazan on 04-Dec-17
 */
public class VersionCheckOneByOne extends TestBase {

    private String versionToCheck;
    private ArrayList<String> arrayList = new ArrayList<>();


    @Override
    public void run() {
        setDC();

        getAllBuilds();
        while (arrayList.size() > 0) {
            isTestPass = true;
            versionToCheck = arrayList.remove(0);

            whatBrowser();
            try {
                testName = "Check Version " + versionToCheck + " on " + browserType;
                dc.setCapability("testName", testName);

                startTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(System.currentTimeMillis()));
                test();
                endTime = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));

                Thread.sleep(1000);
            } catch (Exception e) {
                exception = e;
                if (!e.getMessage().contains("Go To Fail Test!!!") ||
                        (Arrays.toString(e.getStackTrace()).contains("IncompleteTest") && e.getMessage().contains("time out"))) {
                    isTestPass = false;
                    e.printStackTrace();
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
                    driver.quit();
                } else {
                    System.out.println(exception.getMessage().split("\n")[0]);
                    WriteToLog.writeToOverall(startTime, endTime, testName, platformName, exception, null, "Test failed, driver is null because " + exception.getMessage().split("\n")[0]);
                }
            }
        }
    }

    @Override
    public void test() {
        switch (browserType) {
            case BrowserType.FIREFOX: {
                dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                driver = new RemoteWebDriver(url, dc);
                driver.get("about:support");
                WebElement ver = driver.findElement(By.xpath("//*[@id=\"version-box\"]"));
                if (!ver.getText().equals(versionToCheck)) {
                    System.out.println("Version to check= " + versionToCheck);
                    System.out.println("Version= " + ver.getText());
                }
                break;

            }
            case BrowserType.IE: {
                dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);
                driver = new RemoteWebDriver(url, dc);
                driver.get("http://www.whatversion.net/internet-explorer/");
                WebElement ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]/h2")); //Your IE version is 11.0
                System.out.println("Version to check= " + versionToCheck);
                System.out.println(ver.getText());
                break;

            }

            case BrowserType.SAFARI: {
                dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
                driver = new RemoteWebDriver(url, dc);
                driver.get("http://www.whatversion.net/safari/");
                WebElement ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]/h2")); //Your IE version is 11.0
                System.out.println("Version to check= " + versionToCheck);
                System.out.println("Version= " + ver.getText());
                break;

            }
            case BrowserType.CHROME: {
                dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                driver = new RemoteWebDriver(url, dc);
                driver.get("chrome://version");
                WebElement ver = driver.findElement(By.xpath("//*[@id=\"version\"]/span[1]"));
                if (!(ver.getText().split("\\.")[0]).equals(versionToCheck)) {
                    System.out.println("Version to check= " + versionToCheck);
                    System.out.println("Version= " + ver.getText());
                }
                break;

            }
        }

    }

    private void getAllBuilds() {
        HttpResponse<String> response = null;
        String strAPI = "/api/v2/selenium-agents";
        String baseURL = "http://" + HOST;
        if (Credentials.SECURE) {
            baseURL = "https://" + HOST;
        }
        try {
            response = Unirest.get(baseURL + strAPI)
                    .basicAuth(USER, PASS)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
//        System.out.println(response.getBody());

        String[] agents;
        if (response != null) {
            agents = response.getBody().split("\"id\" :");

            for (int i = 1; i < agents.length; i++) {
                if (agents[i].contains("\"connected\" : true,")) {
                    String[] eachAgent = agents[i].split("\"browserVersion\" : \"");
                    for (int j = 1; j < eachAgent.length; j++) {
                        arrayList.add(eachAgent[j].split("\"")[0]);
                    }
                }
            }
            System.out.println(arrayList.toString());
        } else {
            System.out.println("Agent is down!!!!!");
        }
    }

    private void whatBrowser() {
        dc.setCapability(CapabilityType.BROWSER_VERSION, versionToCheck);

        if (versionToCheck.contains(".")) {
            String num = versionToCheck.split("\\.")[0];
            if (Integer.parseInt(num) < 15) {

                browserType = BrowserType.SAFARI;
            } else {
                //Firefox
                browserType = BrowserType.FIREFOX;
            }
        } else if (Integer.parseInt(versionToCheck) < 15) {
            //IE
            browserType = BrowserType.IE;
        } else {
            //Chrome
            browserType = BrowserType.CHROME;
        }
    }
}

package Test.grid;

import FrameWork.Credentials;
import FrameWork.TestBase;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.Objects;

import static FrameWork.Credentials.HOST;
import static FrameWork.Credentials.PASS;
import static FrameWork.Credentials.USER;

public class CheckAllBrowserVersion extends TestBase {
    private final Logger log = Logger.getLogger(this.getClass().getName());
    private ArrayList<JSONObject> jsonNodes;
    private String browserName, browserVersion;

    public CheckAllBrowserVersion() {
        jsonNodes = getAvailableBrowser();
        log.info("Number of browsers: " + jsonNodes.size());
    }

    @Override
    public void run() {

        setDC();
        for (JSONObject jsonObject : jsonNodes) {

            try {
                browserVersion = jsonObject.getString("browserVersion");
                browserName = jsonObject.getString("browserName");
                if (browserName.equals(BrowserType.CHROME)) browserVersion = browserVersion.split("\\.")[0];


                dc.setCapability(CapabilityType.BROWSER_NAME, browserName);
                dc.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
                dc.setCapability(CapabilityType.PLATFORM, jsonObject.getString("platform"));
                dc.setCapability("testName", browserName + " " + browserVersion + " on " + jsonObject.getString("platform"));

                driver = new RemoteWebDriver(url, dc);
                reportUrl = (String) driver.getCapabilities().getCapability("reportUrl");
                platform = String.valueOf(driver.getCapabilities().getPlatform());

                log.info("Start test");
                test();
                log.info("End test");

            } catch (Exception e) {
                exception = e;
                isTestPass = false;
                log.error("Failed test with error " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (Objects.nonNull(driver)) {
                    try {
                        driver.quit();
                    } catch (Exception e) {
                        log.info("Fail to preform quit: " + platform + " " + testName + " " + reportUrl + " exceptionMsg= " + e.getMessage());
                    }
                    if (isTestPass) {
                        writeToLog();
                    } else {
                        writeToLog(driver.getCapabilities());
                    }
                } else {
                    log.info(exception.getMessage().split("\n")[0]);
                    writeToLog(null);
                }

            }
        }
    }


    @Override
    protected void test() throws Exception {
        String ver = "null";
        switch (browserName) {
            case BrowserType.FIREFOX: {
                driver.get("about:support");
                ver = driver.findElement(By.xpath("//*[@id=\"version-box\"]")).getText();
                break;
            }
            case BrowserType.IE: {
                driver.get("http://www.whatversion.net/internet-explorer/");
                ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]/h2")).getText(); //Your IE version is 11.0
                break;
            }
            case BrowserType.SAFARI: {
                driver.get("http://www.whatversion.net/safari/");
//                https://www.whatismybrowser.com/detect/what-version-of-safari-do-i-have
                ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]/h2")).getText(); //Your IE version is 11.0
                break;
            }
            case BrowserType.CHROME: {
                driver.get("chrome://version");
                ver = driver.findElement(By.xpath("//*[@id=\"version\"]/span[1]")).getText();
                break;
            }
        }
        if (Objects.nonNull(ver))
            log.info("Found browser: " + browserName + " " + ver + " On " + driver.getCapabilities().getPlatform());
        log.info("Ask for " + browserVersion + " get " + ver);
        Assert.assertTrue("Ask for " + browserVersion + " get " + ver, ver.contains(browserVersion) || ver.contains("b"));
    }

    private ArrayList<JSONObject> getAvailableBrowser() {
        ArrayList<JSONObject> jsonNodes = new ArrayList<>();
        JsonNode jsonNode = seleniumAgentApi();
        for (int i = 0; i < jsonNode.getArray().length(); i++) {
            if (jsonNode.getArray().getJSONObject(i).getBoolean("connected")) {

                JSONArray jsonArray = jsonNode.getArray().getJSONObject(i).getJSONArray("supportedBrowsers");

                for (int j = 0; j < jsonArray.length(); j++) {
                    jsonNodes.add((JSONObject) jsonArray.get(j));
                }
            }
        }
        return jsonNodes;
    }

    private JsonNode seleniumAgentApi() {
        JsonNode response = null;
        String strAPI = "/api/v2/selenium-agents";
        String baseURL = "http://" + HOST;
        if (Credentials.SECURE) {
            baseURL = "https://" + HOST;
        }
        try {
            response = Unirest.get(baseURL + strAPI)
                    .basicAuth(USER, PASS)
                    .asJson().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response;
    }
}

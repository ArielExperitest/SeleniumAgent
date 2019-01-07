package Test.grid;

import FrameWork.Credentials;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.remote.BrowserType;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static FrameWork.Credentials.*;


/**
 * Created by ariel.hazan on 04-Dec-17
 */
public class VersionCheckOneByOne {

    public VersionCheckOneByOne(int numOfThreads) {
        updateServerCredentials(Credentials.CloudServerName.QA_ADMIN);

        ArrayList<JSONObject> jsonNodes = getAvailableBrowser();
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);

        for (JSONObject jsonObject : jsonNodes) {
            String browserVersion = jsonObject.getString("browserVersion");
            String browserName = jsonObject.getString("browserName");
            if (browserName.equals(BrowserType.CHROME)) browserVersion = browserVersion.split("\\.")[0];
            executor.execute(new VersionCheck(browserName, browserVersion));
        }

        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) break;
        }
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
        String baseURL = (SECURE ? "https://" : "http://") + HOST + ":" + PORT + "/api/v2/selenium-agents";

        try {
            response = Unirest.get(baseURL)
                    .basicAuth(USER, PASS)
                    .asJson().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response;
    }
}

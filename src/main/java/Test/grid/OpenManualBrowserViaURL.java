package Test.grid;

import FrameWork.TestBase;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static FrameWork.Credentials.*;
import static FrameWork.Credentials.PASS;


public class OpenManualBrowserViaURL extends TestBase {


    private String baseUrl;
    private ArrayList<String> arrayList = new ArrayList<>();
    private String baseURL;

    public OpenManualBrowserViaURL() {
        baseURL = "http://" + HOST + ":" + PORT + "/api/v2/selenium-agents";
        if (SECURE)
            baseURL = "https://" + HOST + ":" + PORT + "/api/v2/selenium-agents";
    }


    @Override
    protected void test() {
//        driver = new RemoteWebDriver(url, dc);

JSONObject jsonObject =new JSONObject("{\"browserVersion\":\"43\",\"browserName\":\"chrome\",\"platform\":\"WIN10\"}");
        System.out.println(getBrowserToken(jsonObject));
//        ArrayList<JSONObject> a = getSupportedBrowsers();



    }


    private void openAllBrowserOfSelenium() {
        ArrayList<JSONObject> supportedBrowsers = getSupportedBrowsers();

//        for (int i = 0; i < supportedBrowsers.size(); i++) {
//        }

    }


    private ArrayList<JSONObject> getSupportedBrowsers() {
        JSONArray seleniumAgents = new JSONArray(Objects.requireNonNull(getAllSeleniumAPI()));
        ArrayList<JSONObject> supportedBrowsers = new ArrayList<>();

        for (int i = 0; i < seleniumAgents.length(); i++) {
            JSONObject seleniumAgent = seleniumAgents.getJSONObject(i);

            //Check to see if the Selenium online
            if (seleniumAgent.get("connected").toString().equals("true")) {
                JSONArray seleniumBrowsers = (JSONArray) seleniumAgent.get("supportedBrowsers");

                for (int j = 0; j < seleniumBrowsers.length(); j++) {
                    supportedBrowsers.add((JSONObject) seleniumBrowsers.get(j));//Add {"browserVersion":"43","browserName":"chrome","platform":"WIN10"}

                }
            }
        }
        return supportedBrowsers;
    }

    private String getBrowserToken(JSONObject jsonObject) {
        HttpResponse<String> response = null;

        try {
            response = Unirest.put(baseURL + "/open-manual")
                    .basicAuth(USER, PASS)
                    .header("accept", "application/json")
                    .body(jsonObject)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return Objects.nonNull(response) ? response.getBody() : null;
    }


    private String getAllSeleniumAPI() {
        HttpResponse<String> response = null;

        try {
            response = Unirest.get(baseURL)
                    .basicAuth(USER, PASS)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return Objects.nonNull(response) ? response.getBody() : null;
    }


}

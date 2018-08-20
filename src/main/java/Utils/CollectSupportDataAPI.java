package Utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static FrameWork.Credentials.*;

public class CollectSupportDataAPI implements Runnable {

    private static final String API_V2_CONFIGURATION_COLLECT_SUPPORT_DATA = "/api/v2/configuration/collect-support-data/false/false/-1/-1";
    private static final String API_V2_SELENIUM_AGENTS = "/api/v2/selenium-agents";
    private String baseURL, fileName;

    public CollectSupportDataAPI() {

    }

    @Override
    public void run() {
        try {
            HttpResponse<InputStream> response = Unirest.get(baseURL)
                    .basicAuth(USER, PASS).asBinary();
            InputStream is = response.getBody();

            FileOutputStream fos = new FileOutputStream(new File(fileName));
            int inByte;
            while ((inByte = is.read()) != -1)
                fos.write(inByte);

            is.close();
            fos.close();
        } catch (IOException | UnirestException e) {
            e.printStackTrace();
        }
    }

    public CollectSupportDataAPI(int testIndex, long START_TEST_TIME) {
        fileName = "reports/CSD/" + testIndex + "_" + START_TEST_TIME + ".zip";
        baseURL = "http://" + HOST + ":" + PORT;
        if (SECURE) {
            baseURL = "https://" + HOST + ":" + PORT;
        }
        baseURL += API_V2_CONFIGURATION_COLLECT_SUPPORT_DATA + getSupportedIDs();
    }


    private String getAllSeleniumAPI() {
        String baseURL = "http://" + HOST + ":" + PORT;
        if (SECURE) {
            baseURL = "https://" + HOST + ":" + PORT;
        }
        String ApiUrl = baseURL + API_V2_SELENIUM_AGENTS;
        if (SECURE)
            ApiUrl = baseURL + API_V2_SELENIUM_AGENTS;

        HttpResponse<String> response = null;
        try {
            response = Unirest.get(ApiUrl)
                    .basicAuth(USER, PASS)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return Objects.nonNull(response) ? response.getBody() : null;
    }


    private String getSupportedIDs() {
        JSONArray seleniumAgents = new JSONArray(Objects.requireNonNull(getAllSeleniumAPI()));

        StringBuilder seleniumIDs = new StringBuilder("/");
        for (int i = 0; i < seleniumAgents.length(); i++) {
            JSONObject seleniumAgent = seleniumAgents.getJSONObject(i);

            //Check to see if the Selenium online
            if (seleniumAgent.get("connected").toString().equals("true")) {
                seleniumIDs.append(seleniumAgent.get("id")).append(",");
            }
        }
        return seleniumIDs.substring(0, seleniumIDs.length() - 1);
    }
}
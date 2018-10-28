package Utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.security.timestamp.Timestamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static FrameWork.Credentials.*;

public class CollectSupportDataAPI implements Runnable {
    private final Logger log = Logger.getLogger(this.getClass().getName());

    private static final String API_V2_CONFIGURATION_COLLECT_SUPPORT_DATA = "/api/v2/configuration/collect-support-data/false/false/-1/-1";
    private static final String API_V2_SELENIUM_AGENTS = "/api/v2/selenium-agents";
    private String baseURL, fileName;


    public CollectSupportDataAPI(String fileName) {
        log.info("Starting download Collect Support Data" + fileName);

        this.fileName = fileName;
        baseURL = "http://";
        if (SECURE)
            baseURL = "https://";

        baseURL += HOST + ":" + PORT + API_V2_CONFIGURATION_COLLECT_SUPPORT_DATA + getSupportedIDs();
    }

    public CollectSupportDataAPI() {
        new CollectSupportDataAPI(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
    }

    @Override
    public void run() {
        try {
            Unirest.setTimeouts(50 * 60 * 1000, 50 * 60 * 1000);
            HttpResponse<InputStream> response = Unirest.get(baseURL)
                    .basicAuth(USER, PASS).asBinary();
            InputStream is = response.getBody();
            FileOutputStream fos = new FileOutputStream(new File("reports/CSD/" + fileName));
            int inByte;
            while ((inByte = is.read()) != -1)
                fos.write(inByte);

            is.close();
            fos.close();
            log.info("Finish download " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("IOException - Can't download " + fileName);
        } catch (UnirestException e) {
            e.printStackTrace();
            log.info("UnirestException - Can't download " + fileName);
        }
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
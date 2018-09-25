package Utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static FrameWork.Credentials.*;

public class ReporterAttachment implements Runnable {

    private boolean isReportNull = false;
    private String baseURL, fileName;

    public ReporterAttachment(String fileName, String reportUrl) {
        if (Objects.isNull(reportUrl) || reportUrl.contains("Can't get")) {
            isReportNull = true;
        } else {
            String testID = reportUrl.split("/")[5];

            baseURL = "http://" + REPORTER_HOST + ":" + REPORTER_PORT + "/api/" + PROJECT + "/" + testID + "/attachments-name";
            if (REPORTER_SECURE)
                baseURL = "https://" + REPORTER_HOST + ":" + REPORTER_PORT + "/api/" + PROJECT + "/" + testID + "/attachments-name";
            this.fileName = "reports/attachment/" + fileName;
        }
    }

    @Override
    public void run() {
        if (!isReportNull) {
            try {
                Unirest.setTimeouts(5 * 60 * 1000, 5 * 60 * 1000);
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
        System.out.println("Finish download " + baseURL);
    }
}

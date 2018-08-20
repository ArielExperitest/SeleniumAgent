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
    private String url, fileName;

    @Override
    public void run() {
        if (!isReportNull) {
            try {
                HttpResponse<InputStream> response = Unirest.get(url)
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
    }

    public ReporterAttachment(int testIndex, String reportUrl, long START_TEST_TIME) {
        if (Objects.isNull(reportUrl) || reportUrl.contains("Can't get")) {
            isReportNull = true;
        } else {
            String testID = reportUrl.split("/")[5];

            url = "http://" + REPORTER_HOST + ":" + REPORTER_PORT + "/api/" + PROJECT + "/" + testID + "/attachments-name";
            if (REPORTER_SECURE)
                url = "https://" + REPORTER_HOST + ":" + REPORTER_PORT + "/api/" + PROJECT + "/" + testID + "/attachments-name";
            fileName = "reports/attachment/" + (testIndex + 1) + "_" + START_TEST_TIME + ".zip";
        }
    }
}

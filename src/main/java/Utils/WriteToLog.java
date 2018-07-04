package Utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.openqa.selenium.Capabilities;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

import static FrameWork.Configuration.*;
import static FrameWork.Credentials.*;

public class WriteToLog {
    private static int testIndex = 0;

    private static int fail = 0;
    private static int pass = 0;
    private static CollectSupportDataAPI collectSupportDataAPI;

    public static synchronized void writeFirstTime() {
        PrintWriter writer;
        collectSupportDataAPI = new CollectSupportDataAPI();
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(reportsPath + logFileName, false)));
            writer.write(String.format("%s %10s - %5s %10s %10s %20s %70s %30s\n", "#", "Start", "End", "Status", "Platform Name", "Test Name", "Report Url", "Collect support data"));

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized void writeStringToLog(String s) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(reportsPath + logFileName, true)));
            writer.write("\n" + s + "\n");

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    //Passed
    public static synchronized void writeToOverall(String startTime, String endTime, String testName, String platformName, String reportUrl) {
        PrintWriter writer;
        String testStatus = "PASS";
        pass++;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(reportsPath + logFileName, true)));
            testIndex++;
            writer.write(String.format("%s %5s - %5s %5s %5s %30s %50s\n", testIndex + ". ", startTime, endTime, testStatus, platformName, testName, reportUrl));

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Failed
    public static synchronized void writeToOverall(String startTime, String endTime, String testName, String platformName, Exception e, Capabilities capabilities, String reportUrl) {
        PrintWriter writer;
        String testStatus = "FAIL";
        fail++;
        String CSDPath = collectSupportDataAPI.downloadCSD(testIndex, testName, startTime), reportPath = reporterAttachment(reportUrl, testName, startTime);


        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(reportsPath + logFileName, true)));


            testIndex++;
            writer.write(String.format("%s %5s - %5s %5s %5s %30s %50s %70s %90s\n", testIndex + ". ", startTime, endTime, testStatus, platformName, testName, reportUrl, CSDPath, reportPath));
            writer.write("================================\n");
            if (capabilities != null)
                writer.write("--------Capabilities--------\n" + capabilities.toString() + "\n");
            else
                writer.write("Driver is null\n");
            writer.write("----------Exception-------------\n" + e.toString() + "\n");
            if (Arrays.toString(e.getStackTrace()).contains("com.experitest"))
                writer.write("----------Exception-------------\n" + Arrays.toString(e.getStackTrace()) + "\n");
            writer.write("================================\n");


            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String reporterAttachment(String reportUrl, String testName, String startTime) {
        if (Objects.isNull(reportUrl) || reportUrl.contains("Test failed,")) {
            return null;
        }
        String testID = reportUrl.split("/")[5];

        String url = "http://" + REPORTER_HOST + ":" + REPORTER_PORT + "/api/" + PROJECT + "/" + testID + "/attachments-name";
        if (REPORTER_SECURE)
            url = "https://" + REPORTER_HOST + ":" + REPORTER_PORT + "/api/" + PROJECT + "/" + testID + "/attachments-name";
        String fileName = attachmentPath + testIndex + "_" + testName + "_" + startTime.replace(":", "-") + ".zip";
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

        return "C:\\SeleniumAgent\\SeleniumAgent\\" + fileName;
    }

}

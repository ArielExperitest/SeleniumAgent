package Test.API;

import FrameWork.Credentials;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;

public class LimitSessionsApi extends Credentials {

    private int projectId = 3;

    @Before
    public void setUp() throws Exception {
        updateServerCredentials(CloudServerName.ARIEL_MAC_ADMIN);
    }

    /**
     * Sets the maximum number of concurrent Selenium Sessions for this project .
     * Selenium Sessions include both SeleniumGrid & Selenium Manual Browser Sessions
     *
     * @throws UnirestException
     */
    @Test
    public void SetMaxSeleniumSessions() throws UnirestException {
        String url = "http://" + HOST;
        if (SECURE)
            url = "https://" + HOST;

        HttpResponse<String> response = Unirest.post(url + "/api/v1/projects/" + projectId + "/max-concurrent-browser")
                .basicAuth(USER, PASS)
                .queryString("maxSeleniumSessions", "-1")
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
    }


    /**
     * Gets the maximum number of concurrent Selenium Sessions for this project .
     * Selenium Sessions include both SeleniumGrid & Selenium Manual Browser Sessions
     *
     * @throws UnirestException
     */
    @Test
    public void getMaxSeleniumSessions() throws UnirestException {
        String url = "http://" + HOST;
        if (SECURE)
            url = "https://" + HOST;
        HttpResponse<String> response = Unirest.get(url + "/api/v1/projects/" + projectId + "/max-concurrent-browser")
                .basicAuth(USER, PASS)
                .asString();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
    }

}

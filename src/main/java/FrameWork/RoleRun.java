package FrameWork;

import Test.grid.Basic;
import Test.grid.PerformanceTest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static FrameWork.Credentials.*;

public class RoleRun {
    private int numOfReturns = 3;
    private int numOfUsers = 5;
    private int numOfProjectAdmin = 5;
    private int numOfAdmin = 5;

    @Before
    public void setUp() throws Exception {
        updateServerCredentials(CloudServerName.RND_VM_CLOUD);
        urlBase = "http://" + HOST + ":" + PORT;
        if (SECURE) {
            urlBase = "https://" + HOST + ":" + PORT;
        }
        createProject();
        for (int i = 0; i < numOfUsers; i++) {
            userCredentials.add(createUser(i));
            Thread.sleep(500);
        }
        for (int i = 0; i < numOfAdmin; i++) {
            userCredentials.add(createAdmin(i));
            Thread.sleep(500);
        }
        for (int i = 0; i < numOfProjectAdmin; i++) {
            userCredentials.add(createProjectAdmin(i));
            Thread.sleep(500);
        }

    }

    @Test
    public void test() {
        ExecutorService executor;

        for (int i = 0; i < numOfReturns; i++) {
            int loopNum = numOfUsers + numOfProjectAdmin + numOfAdmin;
            executor = Executors.newFixedThreadPool(loopNum);

            for (int j = 0; j < numOfReturns; j++) {
                for (UserCredentials userCredential : userCredentials) {

                    DesiredCapabilities dc = new DesiredCapabilities();
                    dc.setCapability("username", userCredential.userName);
                    dc.setCapability("password", EXPERITEST_2012);
                    if (userCredential.userName.contains("ariel_Admin"))
                        dc.setCapability("projectName", "Default");
                    else
                        dc.setCapability("projectName", PROJECT_NAME);

                    executor.submit(new PerformanceTest(BrowserType.CHROME, dc));
                    executor.submit(new PerformanceTest(BrowserType.FIREFOX, dc));
                    executor.submit(new PerformanceTest(BrowserType.SAFARI, dc));
                    executor.submit(new Basic(BrowserType.CHROME, dc));
                    executor.submit(new Basic(BrowserType.FIREFOX, dc));
                    executor.submit(new Basic(BrowserType.SAFARI, dc));
                }
            }
            executor.shutdown();
            while (true) {
                if (executor.isTerminated()) break;
            }
            System.out.println("=========Finish Agent Suits #" + i + "=========");
        }
        System.out.println("-------Finished all threads-------");
    }

    @After
    public void tearDown() throws Exception {
        deleteProject();
    }

    private UserCredentials createUserAPI(String username, String role) throws UnirestException {

        HttpResponse<JsonNode> response = Unirest.post(urlBase + "/api/v1/users/new")
                .basicAuth(USER, PASS)
                .queryString("username", username)
                .queryString("firstName", "useri")
                .queryString("lastName", "useri")
                .queryString("email", "user@gmail.com")
                .queryString("project", projectId)
                .queryString("role", role)
                .queryString("password", EXPERITEST_2012)
                .asJson();

        System.out.println(response.getBody());
        response.getBody().getObject();
        return new UserCredentials(username, (long) -1);
    }

    private UserCredentials createAdminAPI(String username) throws UnirestException {

        HttpResponse<JsonNode> response = Unirest.post(urlBase + "/api/v1/users/new")
                .basicAuth(USER, PASS)
                .queryString("username", username)
                .queryString("firstName", "useri")
                .queryString("lastName", "useri")
                .queryString("email", "user@gmail.com")
                .queryString("role", "Admin")
                .queryString("password", EXPERITEST_2012)
                .asJson();

        Long userId = response.getBody().getObject().getJSONObject("data").getLong("id");
        System.out.println(response.getBody());
        response.getBody().getObject();
        return new UserCredentials(username, userId);
    }


    private UserCredentials createUser(int index) throws UnirestException {
        return createUserAPI(ARIEL_USER + index, "User");
    }

    private UserCredentials createAdmin(int index) throws UnirestException {
        return createAdminAPI(ARIEL_ADMIN + index);
    }

    private UserCredentials createProjectAdmin(int index) throws UnirestException {
        return createUserAPI(ARIEL_P + index, "ProjectAdmin");
    }

    private void createProject() throws UnirestException, InterruptedException {
        HttpResponse<JsonNode> response = Unirest.post(urlBase + "/api/v1/projects/new")
                .basicAuth(USER, PASS)
                .queryString("name", PROJECT_NAME)
                .asJson();
        Thread.sleep(5000);
        System.out.println(response.getBody());
        System.out.println(response.getBody().getObject().getJSONObject("data").getLong("id"));
        projectId = response.getBody().getObject().getJSONObject("data").getLong("id");
    }

    private void deleteProject() throws UnirestException {

        HttpResponse<String> response = Unirest.post(urlBase + "/api/v1/projects/" + projectId + "/delete")
                .basicAuth(USER, PASS)
                .asString();

        System.out.println(response.getBody());
        for (UserCredentials anUserCredentials :
                userCredentials) {
            if (anUserCredentials.userId != -1)
                deleteUser(anUserCredentials.userId);
        }
    }

    private void deleteUser(Long userId) throws UnirestException {
        HttpResponse<String> response = Unirest.post(urlBase + "/api/v1/users/" + userId + "/delete")
                .basicAuth(USER, PASS)
                .asString();
        System.out.println(response.getBody());
    }

    private class UserCredentials {
        private String userName;
        private Long userId;

        private UserCredentials(String userName, Long userId) {
            this.userName = userName;
            this.userId = userId;
        }
    }

    private final String PROJECT_NAME = "Ariel123456";
    private final String EXPERITEST_2012 = "Experitest2012";
    private final String ARIEL_USER = "ariel_User";
    private final String ARIEL_ADMIN = "ariel_Admin";
    private final String ARIEL_P = "ariel_P";
    private Long projectId;

    private String urlBase;
    private ArrayList<UserCredentials> userCredentials = new ArrayList<>();
}

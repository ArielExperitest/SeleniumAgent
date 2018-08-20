package SingleTest;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Created by ariel.hazan on 04-Dec-17
 */
public class ParallelBrowserTests3 {


    protected DesiredCapabilities dc = new DesiredCapabilities();
    protected URL url;
    ExecutorService executor;


    String USER = "admin";
    String PASS = "Experitest2012";
    String projectName = "default";
    String HOST = "192.168.2.91";
    String PORT = "80";
    boolean isSecure = false;

    int numOfFirefox = 0;
    int numOfChrome = 0;
    int numOfSafari = 18;
    int numOfIE = 0;

    @Before
    public void run() {
        dc.setCapability("username", USER);
        dc.setCapability("password", PASS);
        dc.setCapability("projectName", projectName);

        dc.setCapability("testName", "Ariel Test");

        try {
            url = new URL("http://" + HOST + ":" + PORT + "/wd/hub");
            if (isSecure)
                url = new URL("https://" + HOST + ":" + PORT + "/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        executor = Executors.newFixedThreadPool(numOfFirefox + numOfChrome + numOfIE + numOfSafari);

    }

    @Test
    public void test() {

        addTestToExecutors(numOfSafari, BrowserType.SAFARI);
        addTestToExecutors(numOfChrome, BrowserType.CHROME);
        addTestToExecutors(numOfIE, BrowserType.IE);
        addTestToExecutors(numOfFirefox, BrowserType.FIREFOX);

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("=========Finish Agent Suits=========");
    }


    public static class ArielTest implements Runnable {

        protected RemoteWebDriver driver;

        ArielTest(DesiredCapabilities dc, URL url) {
            driver = new RemoteWebDriver(url, dc);
        }

        @Override
        public void run() {
            try {
                driver.get("https://www.google.com/");
                sleep(3 * 1000);
                driver.get("https://www.amazon.com/");
                sleep(3 * 1000);
                driver.get("https://edition.cnn.com/");
                sleep(3 * 1000);
                driver.get("https://www.msn.com/he-il/");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (driver != null) {
                    driver.quit();
                }
            }
        }

        public void sleep(int time) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTestToExecutors(int numOfReturn, String browserType) {
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);

        int i = 0;
        while (i < numOfReturn) {
            i++;
            executor.execute(new ArielTest(dc, url));
        }
    }
}

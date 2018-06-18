package SingleTest;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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


/**
 * Created by ariel.hazan on 04-Dec-17
 */
public class ParallelBrowserTests {


    protected DesiredCapabilities dc = new DesiredCapabilities();
    protected URL url;
    ExecutorService executor;


    String USER = "";//TODO
    String PASS = "";//TODO
    String projectName = "";//TODO
    String HOST = "";//TODO
    String PORT = "";//TODO
    boolean isSecure = false;//TODO

    int numOfFirefox = 0;
    int numOfChrome = 0;
    int numOfSafari = 0;
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

        addTest(numOfSafari, BrowserType.SAFARI);
        addTest(numOfChrome, BrowserType.CHROME);
        addTest(numOfIE, BrowserType.IE);
        addTest(numOfFirefox, BrowserType.FIREFOX);

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("=========Finish Agent Suits=========");
    }

    private void addTest(int numOfReturn, String browserType) {
        dc.setCapability(CapabilityType.BROWSER_NAME, browserType);

        int i = 0;
        while (i < numOfReturn) {
            i++;
            executor.execute(new ArielTest(dc, url));
        }
    }


    public static class ArielTest implements Runnable {
        protected RemoteWebDriver driver;

        ArielTest(DesiredCapabilities dc, URL url) {
            driver = new RemoteWebDriver(url, dc);
        }

        @Override
        public void run() {
            try {

                int j = 0;
                driver.get("http://the-internet.herokuapp.com");
                driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[5]/a")).click();
                while (j < 3) {

                    List<WebElement> isChecked = driver.findElements(By.xpath("//*[@checked]"));
                    if (isChecked.size() > 0) {
                        driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click();
                    } else {
                        driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
                    }
                    j++;
                }

                driver.navigate().back();
                driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[9]/a")).click();
                driver.findElement(By.xpath("//*[@id=\"dropdown\"]")).click();
                driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[2]")).click();
                driver.navigate().back();
                driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[16]/a")).click();
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("scroll(0, 500);"); //Down
                jse.executeScript("scroll(0, 250);"); //Down
                jse.executeScript("scroll(0, -250);");//Up
                jse.executeScript("scroll(0, -600);");//Up
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (driver != null)
                    driver.quit();
            }
        }
    }
}

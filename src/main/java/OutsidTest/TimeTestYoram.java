package OutsidTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TimeTestYoram implements Runnable {


    protected URL url;
    protected WebDriver driver;
    protected DesiredCapabilities dc = new DesiredCapabilities();
    ExecutionTimer executionTimer;

    @Override
    public void run() {

        executionTimer = new ExecutionTimer();

        try {
//            url = new URL("http://192.168.2.167:4444/wd/hub");
//            url = new URL("http://192.168.2.91:80/wd/hub");
//            url = new URL("http://192.168.2.167:9100/wd/hub");
//            url = new URL("http://192.168.2.167:80/wd/hub");
            url = new URL("http://192.168.4.34:8080/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        dc.setCapability("username", "ariel-user");
//        dc.setCapability("projectName", "Test@ariel.com");

//        dc.setCapability("password", "Experitest2012");
        dc.setCapability("password", "Admin123");
        dc.setCapability("username", "admin");
        dc.setCapability("projectName", "Default");

        dc.setCapability("generateReport", false);
        dc.setCapability(CapabilityType.TAKES_SCREENSHOT, false);
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
//        dc.setCapability(CapabilityType.BROWSER_VERSION, "65");

        test();
//        LoginToCloud();
    }

    public void test() {
        long s = System.currentTimeMillis();
        int j = 0;
        executionTimer.start();
        driver = new RemoteWebDriver(url, dc);
        executionTimer.end();
        System.out.println("new RemoteWebDriver , " + executionTimer.duration());
        while (j < 30) {

            int i = 0;
            executionTimer.start();
            driver.get("http://the-internet.herokuapp.com");
            executionTimer.end();
            System.out.println("get , " + executionTimer.duration());
            executionTimer.start();
            driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[5]/a")).click();
            executionTimer.end();
            System.out.println("findElement(By.xpath( + click , " + executionTimer.duration());
            while (i < 3) {
                executionTimer.start();
                List<WebElement> isChecked = driver.findElements(By.xpath("//*[@checked]"));
                executionTimer.end();
                System.out.println("findElement(By.xpath( , " + executionTimer.duration());
                if (isChecked.size() > 0) {
                    executionTimer.start();
                    driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click();
                    executionTimer.end();
                    System.out.println("findElement(By.xpath( + click , " + executionTimer.duration());
                } else {
                    executionTimer.start();
                    driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
                    executionTimer.end();
                    System.out.println("findElement(By.xpath( + click , " + executionTimer.duration());
                }
                i++;
            }
            executionTimer.start();
            driver.navigate().back();
            executionTimer.end();
            System.out.println("navigate().back() , " + executionTimer.duration());
            executionTimer.start();
            driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[9]/a")).click();
            executionTimer.end();
            System.out.println("findElement(By.xpath( + click , " + executionTimer.duration());

            executionTimer.start();
            driver.findElement(By.xpath("//*[@id=\"dropdown\"]")).click();
            executionTimer.end();
            System.out.println("findElement(By.xpath( + click , " + executionTimer.duration());

            executionTimer.start();
            driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[2]")).click();
            executionTimer.end();
            System.out.println("findElement(By.xpath( + click , " + executionTimer.duration());

            executionTimer.start();
            driver.navigate().back();
            executionTimer.end();
            System.out.println("navigate().back() , " + executionTimer.duration());

            executionTimer.start();
            driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[16]/a")).click();
            executionTimer.end();
            System.out.println("findElement(By.xpath( + click , " + executionTimer.duration());

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            //Down
            executionTimer.start();
            jse.executeScript("scroll(0, 500);");
            executionTimer.end();
            System.out.println("executeScript , " + executionTimer.duration());

            executionTimer.start();
            jse.executeScript("scroll(0, 250);");
            executionTimer.end();
            System.out.println("executeScript , " + executionTimer.duration());
            //Up
            executionTimer.start();
            jse.executeScript("scroll(0, -250);");
            executionTimer.end();
            System.out.println("executeScript , " + executionTimer.duration());
            executionTimer.start();
            jse.executeScript("scroll(0, -600);");
            executionTimer.end();
            System.out.println("executeScript , " + executionTimer.duration());
            executionTimer.start();
            driver.get("https://www.google.com");
            executionTimer.end();
            System.out.println("get , " + executionTimer.duration());
            executionTimer.start();
            WebElement searchBar = driver.findElement(By.id("lst-ib"));
            executionTimer.end();
            System.out.println("findElement(By.id , " + executionTimer.duration());
            executionTimer.start();
            searchBar.click();
            executionTimer.end();
            System.out.println("click , " + executionTimer.duration());
            executionTimer.start();
            searchBar.sendKeys("Jerusalem wiki");
            executionTimer.end();
            System.out.println("sendKeys , " + executionTimer.duration());
            j++;
        }
        executionTimer.start();
        driver.quit();
        executionTimer.end();
        System.out.println("quit , " + executionTimer.duration());
//        System.out.println((System.currentTimeMillis() - s) / 1000 + " Sec");

    }


}

class ExecutionTimer {
    private double start;
    private double end;

    public ExecutionTimer() {
        start = 0;
        end = 0;
    }

    public void end() {
        end = System.currentTimeMillis();
    }

    public void start() {
        start = System.currentTimeMillis();
    }

    public double duration() {
        return (end - start) / 1000;
    }

}
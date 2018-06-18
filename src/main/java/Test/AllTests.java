package Test;

import FrameWork.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AllTests extends TestBase {

    private String versionToCheck;

    public void accessKeyTest() {
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://www.ynet.co.il");
        driver.get("https://www.google.co.il");
        driver.get("https://www.cnn.com");
    }

    public void basicTest() {
    }

    public void CheckFirefoxTest() {
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        driver = new RemoteWebDriver(url, dc);
        driver.get("about:support");
        WebElement ver = driver.findElement(By.xpath("//*[@id=\"version-box\"]"));
        if (!ver.getText().equals(versionToCheck)) {
            System.out.println("Version to check= " + versionToCheck);
            System.out.println("Version= " + ver.getText());
        }
    }

    public void CheckChromeTest() {
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        driver = new RemoteWebDriver(url, dc);
        driver.get("chrome://version");
        WebElement ver = driver.findElement(By.xpath("//*[@id=\"version\"]/span[1]"));
        if (!(ver.getText().split("\\.")[0]).equals(versionToCheck)) {
            System.out.println("Version to check= " + versionToCheck);
            System.out.println("Version= " + ver.getText());
        }
    }

    public void CheckIETest() {
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);
        driver = new RemoteWebDriver(url, dc);
        driver.get("http://www.whatversion.net/internet-explorer/");
        WebElement ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]/h2")); //Your IE version is 11.0
        System.out.println("Version to check= " + versionToCheck);
        System.out.println(ver.getText());
    }

    public void CheckSafariTest() {
        dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
        driver = new RemoteWebDriver(url, dc);
        driver.get("http://www.whatversion.net/safari/");
        WebElement ver = driver.findElement(By.xpath("//*[@id=\"browser-info\"]/h2")); //Your IE version is 11.0
        System.out.println("Version to check= " + versionToCheck);
        System.out.println("Version= " + ver.getText());
    }

    public void CheckAllVersionTest() {
    }

    public void failTest() {
    }

    public void incompleteTest() {
    }

    public void longTest() {
        //Login to cloud Test
        driver = new RemoteWebDriver(url, dc);
        driver.get("http://192.168.1.64");

        driver.findElement(By.xpath("//*[@name='username']")).sendKeys("ariel");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Experitest2012");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='login']")).click();
        sleep(3000);
        while (driver.getCurrentUrl().contains("/index.html#/login")) {
            driver.findElement(By.xpath("//*[@name='login']")).click();
            sleep(3000);
        }
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]")).click();
        sleep(2000);
        int i = 0;
        while (i < 5) {
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]")).click();
            sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();
            sleep(2000);
            i++;
        }

        //Wikipedia
        driver.get("https://en.wikipedia.org/wiki/Special:Random");
        driver.findElement(By.xpath("//*[@id=\"searchInput\"]")).sendKeys("Experitest");
        driver.findElement(By.id("searchInput")).sendKeys(Keys.ENTER);
        String s = driver.getCurrentUrl();

    }

    public void minimumCapabilityTest() {
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://www.ynet.co.il");
        driver.get("https://www.google.co.il");
        driver.get("https://www.cnn.com");
    }

    public void passTest() {
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://www.ynet.co.il");
    }

    public void sheliTest() {
        driver = new RemoteWebDriver(url, dc);
        driver.get("https://www.google.com");
        sleep(8000);
        WebElement searchBar = driver.findElement(By.id("lst-ib"));
        searchBar.click();
        sleep(5000);
        searchBar.sendKeys("Jerusalem wiki");
        searchBar.sendKeys(Keys.RETURN);
//            driver.findElement(By.xpath("//*[@id=\"hplogo\"]")).click();
        sleep(5000);
        driver.findElement(By.xpath("//*[@href=\"https://en.wikipedia.org/wiki/Jerusalem\"]")).click();
        sleep(5000);

        driver.findElement(By.xpath("//*[contains(text(), 'History of Jerusalem')]")).click();
        sleep(5000);
        driver.getCurrentUrl();
        driver.getTitle();
    }

    public void versionCheckTest() {
    }

    public void SpeedTestGenerateReportTest() {
        driver.get("http://192.168.1.64");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys("ariel");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Experitest2012");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='login']")).click();
        sleep(3000);
        while (driver.getCurrentUrl().contains("/index.html#/login")) {
            driver.findElement(By.xpath("//*[@name='login']")).click();
            sleep(3000);
        }
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]")).click();
        sleep(1500);
        int i = 0;
        while (i < 10) {
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]")).click();
            sleep(1500);
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();
            sleep(1500);
            i++;
        }
    }

    public void SpeedTestScreenshotsTest() {
        driver.get("http://192.168.1.64");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys("ariel");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("Experitest2012");
        sleep(1000);
        driver.findElement(By.xpath("//*[@name='login']")).click();
        sleep(3000);
        while (driver.getCurrentUrl().contains("/index.html#/login")) {
            driver.findElement(By.xpath("//*[@name='login']")).click();
            sleep(3000);
        }
        driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[5]")).click();
        sleep(1500);
        int i = 0;
        while (i < 10) {
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]")).click();
            sleep(1500);
            driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[4]")).click();
            sleep(1500);
            i++;
        }
    }


    @Override
    protected void test() {

    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

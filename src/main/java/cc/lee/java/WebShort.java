package cc.lee.java;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by bjlizhitao on 2017/4/26.
 */
public class WebShort {
    public static void snapshot(TakesScreenshot drivername, String filename) {
        // this method will take screen shot ,require two parameters ,one is driver name, another is file name
        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            System.out.println("save snapshot path is:E:/" + filename);
            FileUtils.copyFile(scrFile, new File("E:\\" + filename));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        } finally {
            System.out.println("screen shot finished");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        String URL = "http://www.baidu.com";
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        //max size the browser
        driver.manage().window().maximize();
        /*
        Navigation navigation = driver.navigate();
         navigation.to(URL);*/
        Thread.sleep(2000);
        snapshot((TakesScreenshot) driver, "open_baidu.png");
        //WebElement reg=driver.findElement(By.name("tj_reg"));
        //reg.click();
        //    WebElement keyWord = driver.findElement(By.id("kw1"));
        //find the element
        WebElement keyWord = driver.findElement(By.xpath("//input[@id='kw1']"));
        keyWord.clear();
        //send key words
        keyWord.sendKeys("Selenium");
        Thread.sleep(3000);
        snapshot((TakesScreenshot) driver, "input_keyWord.png");
        WebElement submit = driver.findElement(By.id("su1"));
        System.out.println(submit.getLocation());
        submit.click();
        //System.out.println(driver.getWindowHandle());
        Thread.sleep(5000);
        // System.out.println(driver.getPageSource());
        String pageSource = driver.getPageSource();
        //  System.out.println(pageSource);
        //WebElement link =driver.findElement(By.xpath(SELENIUM_LINK));
        WebElement link = driver.findElement(By.xpath("//*[@id=\"1\"]/h3/a"));     //*[@id="1"]/h3/a
        link.click();
        Thread.sleep(5000);
        driver.switchTo().window(driver.getWindowHandles().toArray(new String[0])[1]);
        //get page title
        System.out.println(driver.getTitle());
        Thread.sleep(5000);
        //     navigation.back();
        snapshot((TakesScreenshot) driver, "open_bake.png");
        System.out.println(driver.getTitle() + "\n" + driver.getCurrentUrl());
        driver.quit();
    }
}

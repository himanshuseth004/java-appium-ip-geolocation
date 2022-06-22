import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class vanilla_ios {

    public static String userName = System.getenv("LT_USERNAME") == null ? "LT_USERNAME"  //Add username here
            : System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY" //Add accessKey here
            : System.getenv("LT_ACCESS_KEY");

    public static IOSDriver driver = null;

    public static void main(String[] args) throws Exception {

       try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformVersion", "15");
            caps.setCapability("deviceName", "iPhone 12");
            caps.setCapability("isRealMobile", true);
            caps.setCapability("app", "lt://"); //Enter your app url
            caps.setCapability("platformName", "iOS");
            caps.setCapability("build", "Java Vanilla - iOS");
            caps.setCapability("name", "Sample Test Java");
            caps.setCapability("devicelog", true);
            caps.setCapability("network", true);

            //ADD GEOLOCATION BASED ON COUNTRY CODE
            caps.setCapability("geoLocation", "fr");


           driver = new IOSDriver(new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"), caps);

            Thread.sleep(2000);

            driver.findElement(MobileBy.id("color")).click();
            Thread.sleep(1000);

            driver.findElement(MobileBy.id("Text")).click();
            Thread.sleep(1000);

            driver.findElement(MobileBy.id("toast")).click();
            Thread.sleep(1000);

            driver.findElement(MobileBy.id("notification")).click();
            Thread.sleep(2000);

            driver.findElement(MobileBy.id("geoLocation")).click();
            Thread.sleep(4000);
            driver.navigate().back();
            Thread.sleep(1000);

            driver.findElement(MobileBy.id("speedTest")).click();
            Thread.sleep(5000);
            driver.navigate().back();

            MobileElement browser = (MobileElement) driver.findElementByAccessibilityId("Browser");
            browser.click();
            Thread.sleep(3000);

            WebDriverWait el7 =  new WebDriverWait(driver, 30);
            el7.until(ExpectedConditions.elementToBeClickable(MobileBy.id("url")));
            driver.findElementById("url").sendKeys("https://www.lambdatest.com/");

            WebDriverWait el = new WebDriverWait(driver,90);
            MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("find");
            el.until(ExpectedConditions.elementToBeClickable(el4));
            el4.click();
            el4.sendKeys("Lambdatest");

            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
            driver.quit();

        } catch (Exception t) {
           ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
           t.printStackTrace();
           driver.quit();
       }
    }
}


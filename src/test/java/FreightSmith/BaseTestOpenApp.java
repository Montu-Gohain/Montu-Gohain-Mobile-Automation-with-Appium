package FreightSmith;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class BaseTestOpenApp {
    public AndroidDriver driver;
    public boolean recurse_till_download(WebElement downloadingElement) throws StaleElementReferenceException ,InterruptedException{
        try {

            if (downloadingElement.isDisplayed()) {
                System.out.println("Downloading window is still displayed. Waiting...");
                Thread.sleep(15 * 1000); // Convert seconds to milliseconds
                return recurse_till_download(downloadingElement);
            }
            return false;
        }
        catch (StaleElementReferenceException E){
            System.out.println("Final wait till the Acceptance button is clickable.");
            System.out.println("Terms and conditions accept button is clickable now.");
            return true;
        }
    }

    @BeforeClass
    public void OpenApplication()  throws MalformedURLException,InterruptedException {
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability("platformName","Android");
        dc.setCapability("platformVersion","14");
        dc.setCapability("automationName" , "uiautomator2");
        dc.setCapability("deviceName","realme RMX3710");
//        dc.setCapability("deviceName","Realme virtual device");

        // Todo: To install the application for the first in the device (Whether it is Emulator or real device)
//        dc.setCapability("app","E:\\Exuber_work\\Testable_apks\\FreightSmith.apk");

        dc.setCapability("appPackage", "net.freightsmith");
        dc.setCapability("appActivity","net.freightsmith.MainActivity");

        URL url = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
        driver = new AndroidDriver(url, dc);
        Thread.sleep(20000);
        System.out.println("Application Opened Successfully in the Device.");

        // Todo : Step 1 : Accept the terms and conditions
//        Thread.sleep(25000);


        WebElement download_in_progress = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Download is in progress\"]"));

        recurse_till_download(download_in_progress);

        WebElement accept_button = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]"));

        accept_button.click();
        System.out.println("Terms and Conditions are Accepted.");


        // Todo : Step 2 : Language selection page, Pressing Continue with English

        Thread.sleep(3000);
        WebElement  continue_button = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]"));
        continue_button.click();
        System.out.println("Clicked in Continue Button in the Language selection page.");
        Thread.sleep(5000);

    }


//    @AfterSuite
//    public void closeApplication() {
//        // Quit the Appium driver
//        System.out.println("All tests completed, Let's close the application");
//
//        driver.quit();
//    }
}

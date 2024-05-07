package FreightSmith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

 public class LogOut extends captchaFilling{
    @Test(dependsOnMethods = "captcha_Filling")
    public void log_out() throws  InterruptedException{
        // Todo : Click on Account profile
        System.out.println("Click On Account Profile Section.");
        WebElement account_profile = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup"));
        account_profile.click();
        Thread.sleep(2000);
        // Todo : Click on Log out button
        System.out.println("Click on Log Out Button.");
        WebElement log_out_button = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[9]"));
        log_out_button.click();
        System.out.println("Click on Yes in the confirmation button.");
        WebElement logout_confirmation_button = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup"));
        logout_confirmation_button.click();
    }

}

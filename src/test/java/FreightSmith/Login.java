package FreightSmith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Login extends BaseTestOpenApp{
    @Test
    public void login_with_mobile_no () throws InterruptedException {

        // Todo: Step 3 : Enter mobile number and Sign In

        Thread.sleep(3000);
        driver.findElement(By.xpath("//android.widget.EditText[@text=\'Mobile Number *\']")).click();
        driver.hideKeyboard();
//            Todo : Please ensure that in case of Sign In use a unused mobile number and email.
        String mobile_no = "7086221142";
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(mobile_no);
        System.out.println("Entered mobile number : " + mobile_no);
        Thread.sleep(4000);
        WebElement login_button = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
        login_button.click();
        login_button.click();

        Thread.sleep(2000);
        System.out.println("Login button clicked");
    }
}

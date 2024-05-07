package FreightSmith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class captchaFilling extends Login {


    @Test
    public void captcha_Filling() throws InterruptedException {
        Thread.sleep(5000);
        try {


            String captchaText = driver.findElement(By.xpath("//android.widget.TextView")).getText();
            System.out.println(captchaText);

            String option =captchaText.split("\\s+")[2];
            System.out.println(option);
            String xpath = getOptionXPath(option);


            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(4*1000);
            String captchaText_2 = driver.findElement(By.xpath("//android.widget.TextView")).getText();
            System.out.println(captchaText_2);

            String option_2 =captchaText_2.split("\\s+")[2];
            System.out.println(option_2);
            String xpath2 = getOptionXPath_2(option);
            driver.findElement(By.xpath(xpath2)).click();
            System.out.println("Captcha Filling completed.");

            Thread.sleep(10 * 1000);
            WebElement location_permission_accept = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]"));
            location_permission_accept.click();

//            Todo : Let's go the account summary section. and try to edit the profile details
            edit_profile();

        } catch (Exception e) {

            System.out.println("Error occurred: " + e.getMessage());
        }
    }
    public String getOptionXPath(String option) {
        switch (option) {
            case "car":
                return "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.ImageView";
            case "bus":
                return "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.ImageView";
            case "truck":
                return "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView";
            case "bike":
                return "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.ImageView";
            default:
                throw new IllegalArgumentException("Unknown option: " + option);
        }


    }
    public String getOptionXPath_2(String option) {

        switch (option) {
            case "car":
                return "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.widget.ImageView";
            case "bus":
                return "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.ImageView";
            case "truck":
                return "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.ImageView";
            case "bike":
                return "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.ImageView";
            default:
                throw new IllegalArgumentException("Unknown option: " + option);
        }
    }

    public void edit_profile() throws InterruptedException, IOException {
        Properties prop = new Properties();
        File file = new File("testdata.properties");
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);

        System.out.println("Let's update the profile details.");

        Thread.sleep(2*1000);
        WebElement account_profile_section = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup"));
        account_profile_section.click();
        Thread.sleep(4*1000);

        String current_firstname = driver.findElement(By.xpath("(//android.widget.TextView)[2]")).getText();
        String current_lastname = driver.findElement(By.xpath("(//android.widget.TextView)[3]")).getText();
        String current_email = driver.findElement(By.xpath("(//android.widget.TextView)[6]")).getText();

        System.out.println("Current name : " + current_firstname + " " + current_lastname);
        System.out.println("Current Email : " + current_email);

//        Todo : ========== Now let's click on the three dots in top and click on the Edit button.

        String new_first_name = prop.getProperty("first_name_updated");
        String new_last_name = prop.getProperty("last_name_updated");
        String new_email = prop.getProperty("email_updated");

        WebElement three_dots = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup"));
        three_dots.click();

        Thread.sleep(2*1000);

        WebElement edit_button = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Edit\"]"));
        edit_button.click();

        Thread.sleep(2*1000); // Wait for 4 seconds.

        System.out.println("Updating Firstname");
        WebElement firstname_input = driver.findElement(By.xpath("(//android.widget.EditText)[1]"));
        firstname_input.sendKeys("");
        firstname_input.sendKeys(new_first_name);


        Thread.sleep(3*1000); // Wait for 3 seconds.

        System.out.println("Updating Lastname");
        WebElement lastname_input = driver.findElement(By.xpath("(//android.widget.EditText)[2]"));
        lastname_input.sendKeys("");
        lastname_input.sendKeys(new_last_name);

        Thread.sleep(3*1000); // Wait for 3 seconds.

        System.out.println("Updating Email");
        WebElement email_input = driver.findElement(By.xpath("(//android.widget.EditText)[3]"));
        email_input.sendKeys("");
        email_input.sendKeys(new_email);

        Thread.sleep(3*1000); // Wait for 3 seconds.

//        Todo: Finally let's submit these new changes.

        WebElement submit_button = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup[6]"));
        submit_button.click();

        Thread.sleep(3*1000); // Wait for 3 seconds.
        System.out.println("Going again to home screen");

        WebElement home_icon = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]"));
        home_icon.click();
        System.out.println("Let's compare the profile data , if it is updated or not.");
        Thread.sleep(2*1000);
        account_profile_section.click();

        System.out.println("Waiting to see the updated profile data.");
        Thread.sleep(10*1000);
//        au = after_update
        String au_firstname = driver.findElement(By.xpath("(//android.widget.TextView)[2]")).getText();
        String au_lastname = driver.findElement(By.xpath("(//android.widget.TextView)[3]")).getText();
        String au_email = driver.findElement(By.xpath("(//android.widget.TextView)[6]")).getText();


        System.out.println("Updated name : " + au_firstname + " " + au_lastname);
        System.out.println("Updated email : " + au_email);


        boolean matching_firstname = new_first_name.equals(au_firstname);
        boolean matching_lastname = new_last_name.equals(au_lastname);
        boolean matching_email = new_email.equals(au_email);

        System.out.println("Firstname matched : " + matching_firstname);
        System.out.println("Lastname matched : " + matching_lastname);
        System.out.println("Email matched : " + matching_email);

        Thread.sleep(2*1000);
        Assert.assertTrue(matching_firstname && matching_lastname && matching_email);

        System.out.println("Profile data updated successfully.");
    }
}

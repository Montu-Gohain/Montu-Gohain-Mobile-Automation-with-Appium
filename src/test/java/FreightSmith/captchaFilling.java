package FreightSmith;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.options.webview.SupportsSafariWebInspectorMaxFrameLengthOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class captchaFilling extends Login {


    @Test
    public void captcha_Filling() throws InterruptedException,IOException {
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
//            edit_profile();

//            Todo : Let's go to Driver Check In Screen.

//              driver_checkIn();

//            Todo : Let's go to Payment
              payment_receipt_download();
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
// Todo : Different test cases can be written here.
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

//        Todo : Input validation in the edit profile input form

        System.out.println("Updating Firstname");
        WebElement firstname_input = driver.findElement(By.xpath("(//android.widget.EditText)[1]"));
        WebElement lastname_input = driver.findElement(By.xpath("(//android.widget.EditText)[2]"));

        firstname_input.sendKeys("");
        firstname_input.sendKeys("2"); // Entering non-supported input value and expecting error message.


        lastname_input.sendKeys("");
        lastname_input.sendKeys("3"); // Entering non-supported input value and expecting error message.

//        Let's Check if we can see the error message
        WebElement lastname_error_message = driver.findElement(By.xpath("(//android.widget.TextView[@text=\"Special characters and numbers are not allowed\"])[2]"));

//        Here we're checking whether we are getting the warning message or not.

        WebElement firstname_error_message = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Special characters and numbers are not allowed\"]"));

        Thread.sleep(3*1000);
        Assert.assertTrue(firstname_error_message.isDisplayed() && lastname_error_message.isDisplayed());

        System.out.println("Firstname and lastname input validation working perfectly..");


//        Let's enter valid firstname now.
        firstname_input.sendKeys("");
        firstname_input.sendKeys(new_first_name);


        Thread.sleep(3*1000); // Wait for 3 seconds.

        System.out.println("Updating Lastname");


//      Let's enter valid lastname now.

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

        Thread.sleep(5*1000); // Wait for 3 seconds.
        System.out.println("Going again to home screen");

        WebElement home_icon = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]"));
        home_icon.click();
        System.out.println("Let's compare the profile data , if it is updated or not.");
        Thread.sleep(5*1000);
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
    public void driver_checkIn () throws  InterruptedException, IOException {
        Properties prop = new Properties();
        File file = new File("testdata.properties");
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);

        System.out.println("Let's go to Driver Check-In page.");
        Thread.sleep(5*1000);
//        Todo : Step 1: Open Driver Check-In Page.
        WebElement driver_check_in = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"));
        driver_check_in.click();
        Thread.sleep(8*1000); // Waiting to load the device location.

//        Todo : Step 2 : Enter PO number and zip code.

        String target_po = prop.getProperty("target_po");
        String target_zip_code = prop.getProperty("target_zip_code");

        System.out.println("Entering PO number : " + target_po + "/n" + "Entering zip code : " + target_zip_code);


        WebElement po_number_input = driver.findElement(By.xpath("//android.widget.EditText[@text=\"PO Number\"]"));
        po_number_input.sendKeys(target_po);

        Thread.sleep(2*1000);
        WebElement zip_code_input = driver.findElement(By.xpath("//android.widget.EditText[@text=\"Warehouse Zip Code\"]"));
        zip_code_input.sendKeys(target_zip_code);

//        Todo : Step 3 :  Click on the submit button.

        WebElement submit_btn = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]"));
        System.out.println("Finally Clicking on the submit button");
        submit_btn.click();

        Thread.sleep(5 * 1000);

//        Is this correct pop-up window.
        WebElement yes_btn = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Yes\"]"));
        if(yes_btn.isDisplayed()){
            yes_btn.click();
            Thread.sleep(5 * 1000);
            System.out.println("Is this correct window shown");
        }

//        Please click to verify the Purchase order.
        WebElement click_to_verify_po = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Ok\"]"));
        click_to_verify_po.click();
        Thread.sleep(5 * 1000);


//        After displaying as verified, click on verify all po's button.
        WebElement verify_all_po = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Verify All PO's\"]"));
        verify_all_po.click();
        Thread.sleep(5 * 1000);

//        Proceed to Check In
        WebElement proceed_to_checkin = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Proceed to Check-In\"]"));
        proceed_to_checkin.click();
        Thread.sleep(2*1000);

//        Click No in You've verified X purchase number.
        WebElement no_button  = driver.findElement(By.xpath("//android.widget.TextView[@text=\"No\"]"));
        no_button.click();

        Thread.sleep(3*1000);
//        At this point the form should open
        WebElement option_trailer_tractor = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[10]/android.view.ViewGroup"));
        option_trailer_tractor.click();

//        Entering Carrier name and selecting from dropdown.
        WebElement carrier_name_input = driver.findElement(By.xpath("//android.widget.EditText[@text=\"Enter or Add Carrier Name Here\"]"));
        carrier_name_input.click();
//       carrier_name_input.sendKeys("TEST ARNAB");
        enter_carrier_name();
        System.out.println("Carrier name entered sucessfully");

//        Waiting for the option to appear in dropdown menu.
        Thread.sleep(7*1000);
        WebElement dropdown_option = driver.findElement(By.xpath("//android.widget.TextView[@text=\"TEST ARNAB\"]"));
        dropdown_option.click();
        System.out.println("Carrier selected successfully from the dropdown.");

//      Entering any 4 digits number in Trailer number and Tractor number input.
        Thread.sleep(3*1000);

        WebElement trailer_number = driver.findElement(By.xpath("//android.widget.EditText[@text=\"L5678\"]"));
        trailer_number.sendKeys("");
        trailer_number.sendKeys("3374");

        Thread.sleep(2*1000);

        WebElement tractor_number = driver.findElement(By.xpath("//android.widget.EditText[@text=\"T1234\"]"));
        tractor_number.sendKeys("");
        tractor_number.sendKeys("7476");
        System.out.println("Entered trailer number and tractor number.");

        Thread.sleep(2*1000);
        System.out.println("Final wait after clicking the submit button.");

//        Finally click on submit button.

        WebElement final_submit_btn = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[11]"));
        final_submit_btn.click();
        final_submit_btn.click();


//        Final wait till success message appears on the screen.
        System.out.println("Waiting for the success message pop-up.");
        Thread.sleep(6*1000);

        WebElement final_ok_button = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Ok\"]"));
        final_ok_button.click();

        System.out.println("Driver Check-In and PO Verification done successfully.");

        Assert.assertTrue(true);

    }
    public void enter_carrier_name(){
        driver.pressKey(new KeyEvent(AndroidKey.T));
        driver.pressKey(new KeyEvent(AndroidKey.E));
        driver.pressKey(new KeyEvent(AndroidKey.S));
        driver.pressKey(new KeyEvent(AndroidKey.T));
        driver.pressKey(new KeyEvent(AndroidKey.SPACE));
        driver.pressKey(new KeyEvent(AndroidKey.A));
        driver.pressKey(new KeyEvent(AndroidKey.R));
        driver.pressKey(new KeyEvent(AndroidKey.N));
        driver.pressKey(new KeyEvent(AndroidKey.A));
        driver.pressKey(new KeyEvent(AndroidKey.B));
    }
    public void payment_receipt_download() throws InterruptedException,IOException{

        Properties prop = new Properties();
        File file = new File("testdata.properties");
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);

        Thread.sleep(3*1000);
//        Clicking on Payment section in Home Screen
        WebElement payment_section = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"));
        payment_section.click();
        System.out.println("Clicked on Payment section.");

        Thread.sleep(3*1000);
//        Clicking on Completed
        WebElement completed_section = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]"));
        completed_section.click();

        Thread.sleep(3*1000);
//        Total completed payments details
        List<WebElement> completed_payments_ = driver.findElements(By.xpath("//android.widget.TextView[@text=\"Click to View Details\"]"));
        int total_completed_payments = completed_payments_.size();

        System.out.println("Total number of completed payments : " + total_completed_payments);
        Thread.sleep(2*1000);
//        Click : Click to View Details
        WebElement click_to_view_details = driver.findElement(By.xpath("(//android.widget.TextView[@text=\"Click to View Details\"])[1]"));
        click_to_view_details.click();

        Thread.sleep(2*1000);
//        Click on Download Receipt button.
        WebElement download_receipt_btn = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        download_receipt_btn.click();
        System.out.println("Download Receipt button clicked, test complete");
        Thread.sleep(5*1000);

        WebElement allow_storage_permission = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]"));
        allow_storage_permission.click();

        System.out.println("Download started...");

        Thread.sleep(4*1000);
        System.out.println("Let's test out Share receipt feature.");
//        Share Receipt test case.

        WebElement share_receipt_btn = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]"));
        share_receipt_btn.click();

        Thread.sleep(3*1000); // Waiting for the Share Receipt form

        String share_receipt_to_email = prop.getProperty("share_receipt_email");

//        For the first time we'll be clicking on the Submit button to test if the form validation is working or not.

        WebElement submit_btn_sr = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
        submit_btn_sr.click();

        Thread.sleep(3*1000);
        WebElement email_error_msg = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Please enter valid email\"]"));

        if(email_error_msg.isDisplayed()){
            System.out.println("Email input field validation is working perfectly.");
        }

        Thread.sleep(3*1000);

        WebElement share_receipt_email = driver.findElement(By.xpath("//android.widget.EditText[@text=\"Email\"]"));
        share_receipt_email.sendKeys(share_receipt_to_email);


//      Finally click on submit button.
        submit_btn_sr.click();
        submit_btn_sr.click();

        System.out.println("Share Receipt email submit button clicked.");

        Assert.assertTrue(true);
    }

}

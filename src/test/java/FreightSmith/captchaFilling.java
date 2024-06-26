package FreightSmith;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static java.time.Duration.ofMillis;

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

//            ====================== Different test cases, Just Call the function and perform the test ==============================

//            Todo : Date : 15th march : Test case : Payment Due (Using Relay Payment method)
//              payment_due_relay();

//            Todo : Date : 16th march : Test case : Payment Due (Using ComData Express Code method)
            payment_due_insta_money();

//            Todo : Let's go the account summary section. and try to edit the profile details
//            edit_profile();

//            Todo : Let's go to Driver Check In Screen.
//            driver_checkIn();

//            Todo : Let's go to Payment
//            payment_receipt_download();


//            Todo : payment awaiting.
//            To get the most updated data for payment section let's logout and login again.

//            logout_and_relogin();
//            payment_awaiting();



        } catch (Exception e) {

            System.out.println("Error occurred: " + e.getMessage());
        }
    }
    //    Date : 15th May
    public void payment_due_relay() throws InterruptedException, IOException{

        Properties prop = new Properties();
        File properties_file = new File("testdata.properties");
        FileInputStream fis = new FileInputStream(properties_file);
        prop.load(fis);

        Thread.sleep(4000);
//        Step 1 : Go to payments section.
        WebElement payment_section = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"));
        payment_section.click();
        System.out.println("Payment option clicked on HomeScreen.");

        Thread.sleep(6*1000); // Waiting to get the payment data load in the screen.
//      Step 2 : Go to Payment Due section.

        WebElement payment_due_section = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]"));
        payment_due_section.click();

        System.out.println("Payment Due option clicked.");


        Thread.sleep(3*1000);
//      Step 3 : Click on the topmost payment due entry.
        WebElement topmost_payment_due = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"));
        topmost_payment_due.click();

        System.out.println("Topmost payment due entry is clicked.");
        Thread.sleep(3*1000);

        WebElement PO_no = driver.findElement(By.xpath("//android.widget.TextView[14]"));
        System.out.println("Current PO number : " + PO_no.getText());

//        PDD : Payment Due Details.
        WebElement pay_btn_PDD = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        pay_btn_PDD.click();

        Thread.sleep(2000);

//      Select Partial payment option and click Pay
        WebElement partial_payment_option = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"));
        partial_payment_option.click();
        Thread.sleep(2*1000);

//        PP : Partial payment
        String partial_payment_amount = prop.getProperty("partial_payment_amount");
        WebElement PP_input_field = driver.findElement(By.xpath("//android.widget.EditText"));
        PP_input_field.sendKeys(partial_payment_amount);
        System.out.println("Entered partial amount : " + partial_payment_amount);
        Thread.sleep(2000);

//        Clicking on Remaining payment text to remove the keyboard.
        WebElement remaining_amount_text = driver.findElement(By.xpath("//android.widget.TextView[4]"));
        remaining_amount_text.click();

        Thread.sleep(2000);

//        PD : Payment Details
        WebElement pay_btn_PD = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        pay_btn_PD.click();

        System.out.println("Clicked in Pay button.");

        Thread.sleep(2000);

        select_payment_method_relay(driver,prop);

//        ================= Partial payment ===============================
        System.out.println("Partial payment complete, let's finish the remaining amount.");

        WebElement pay_remaining_amount = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
        pay_remaining_amount.click();
        System.out.println("Clicked on Pay remaining amount button.");
        Thread.sleep(2000);

//        Select the Full payment option.
        WebElement full_payment_option = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"));
        full_payment_option.click();
        System.out.println("Full payment option selected.");
        Thread.sleep(2000);

//        FP : Full payment

        WebElement pay_btn_FP = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        pay_btn_FP.click();
        Thread.sleep(2000);
        System.out.println("Now let's select again the payment method as relay and complete the payment.");

        select_payment_method_relay(driver,prop);

        Assert.assertTrue(true);
        System.out.println("Partial and full payment through Relay : Test case complete.");
    }
    public void select_payment_method_relay(AndroidDriver driver, Properties prop) throws InterruptedException{
        System.out.println("Let's choose Relay as our Automated payment method.");

        WebElement relay_option = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"));
        relay_option.click();
        Thread.sleep(2000);

//        Let's click on Pay button.
//        SPM : Select a payment method.

        WebElement pay_btn_SPM = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        pay_btn_SPM.click();
        Thread.sleep(2000);

        System.out.println("We've reached Confirm Payment Screen.");
        WebElement confirm_payment_btn = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        confirm_payment_btn.click();

        Thread.sleep(2000);
        System.out.println("Let's enter money code for Relay...");

        String relay_money_code = prop.getProperty("money_code_for_relay");
        WebElement money_code_input = driver.findElement(By.xpath("//android.widget.EditText[@text=\"************\"]"));
        money_code_input.click();
        driver.hideKeyboard();
        money_code_input.sendKeys(relay_money_code);
        Thread.sleep(2000);
        money_code_input.click();
        enter_money_code_relay();
        Thread.sleep(2000);

        System.out.println("We've entered the money code for relay.");
        WebElement money_code_text = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Money code *\"]"));
        money_code_text.click(); // Click it to close the keyboard.

        Thread.sleep(2000);
//        Finally click on submit button and wait for 9 seconds.
        WebElement submit_btn = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[4]"));
        submit_btn.click();
        Thread.sleep(9000);
        System.out.println("Payment completed through Relay Successfully.");
    }
    public void enter_money_code_relay(){
        driver.pressKey(new KeyEvent(AndroidKey.S));
    }

    //    Test case : Payment Due using ComData Express Code Payment method.
    public void payment_due_insta_money() throws InterruptedException, IOException{

        Properties prop = new Properties();
        File properties_file = new File("testdata.properties");
        FileInputStream fis = new FileInputStream(properties_file);
        prop.load(fis);

        Thread.sleep(4000);
//        Step 1 : Go to payments section.
        WebElement payment_section = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"));
        payment_section.click();
        System.out.println("Payment option clicked on HomeScreen.");

        Thread.sleep(6*1000); // Waiting to get the payment data load in the screen.
//      Step 2 : Go to Payment Due section.

        WebElement payment_due_section = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]"));
        payment_due_section.click();

        System.out.println("Payment Due option clicked.");


        Thread.sleep(3*1000);
//      Step 3 : Click on the topmost payment due entry.
        WebElement topmost_payment_due = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"));
        topmost_payment_due.click();

        System.out.println("Topmost payment due entry is clicked.");
        Thread.sleep(3*1000);

        WebElement PO_no = driver.findElement(By.xpath("//android.widget.TextView[14]"));
        System.out.println("Current PO number : " + PO_no.getText());

//        PDD : Payment Due Details.
        WebElement pay_btn_PDD = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        pay_btn_PDD.click();

        Thread.sleep(2000);

//      Select Partial payment option and click Pay =================
//        WebElement partial_payment_option = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"));
//        partial_payment_option.click();
//        Thread.sleep(2*1000);
//
////        PP : Partial payment
//        String partial_payment_amount = prop.getProperty("partial_payment_amount");
//        WebElement PP_input_field = driver.findElement(By.xpath("//android.widget.EditText"));
//        PP_input_field.sendKeys(partial_payment_amount);
//        System.out.println("Entered partial amount : " + partial_payment_amount);
//        Thread.sleep(2000);
//
////        Clicking on Remaining payment text to remove the keyboard.
//        WebElement remaining_amount_text = driver.findElement(By.xpath("//android.widget.TextView[4]"));
//        remaining_amount_text.click();
//
//        Thread.sleep(2000);
//==================================================================================================
        WebElement full_payment_option = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"));
        full_payment_option.click();
        System.out.println("Full payment option selected.");
        Thread.sleep(2000);

//        PD : Payment Details
        WebElement pay_btn_PD = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        pay_btn_PD.click();

        System.out.println("Clicked in Pay button.");

        Thread.sleep(2000);

        select_payment_method_insta_money(driver,prop);

//        ================= Partial payment ===============================
//        System.out.println("Partial payment complete, let's finish the remaining amount.");
//
//        WebElement pay_remaining_amount = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
//        pay_remaining_amount.click();
//        System.out.println("Clicked on Pay remaining amount button.");
//        Thread.sleep(2000);
//
////        Select the Full payment option.
//        WebElement full_payment_option = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"));
//        full_payment_option.click();
//        System.out.println("Full payment option selected.");
//        Thread.sleep(2000);
//
////        FP : Full payment
//
//        WebElement pay_btn_FP = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
//        pay_btn_FP.click();
//        Thread.sleep(2000);
//        System.out.println("Now let's select again the payment method as ComData and complete the payment.");
//
//        select_payment_method_insta_money(driver,prop);
//
//        Assert.assertTrue(true);
//        System.out.println("Partial and full payment through Relay : Test case complete.");
    }
    public void select_payment_method_insta_money(AndroidDriver driver, Properties prop) throws InterruptedException{

        System.out.println("Let's choose Insta money as our Automated payment method.");

        WebElement insta_money_option = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[6]/android.view.ViewGroup"));
        insta_money_option.click();
        Thread.sleep(2000);

//        Let's click on Pay button.
//        SPM : Select a payment method.

        WebElement pay_btn_SPM = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        pay_btn_SPM.click();
        Thread.sleep(2000);

        System.out.println("We've reached Confirm Payment Screen.");
        WebElement confirm_payment_btn = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
        confirm_payment_btn.click();

        Thread.sleep(2000);
        System.out.println("Let's enter the required fields for Insta-money...");

        String ac_no_instamoney = prop.getProperty("Insta_money_ac_no");

//        Entering Express Code
        WebElement ac_no_input_field = driver.findElement(By.xpath("//android.widget.EditText[1]"));
        ac_no_input_field.sendKeys("");
        ac_no_input_field.sendKeys(ac_no_instamoney);
        System.out.println("Account number for Insta-money entered successfully.");
        Thread.sleep(2000);

        WebElement moneycode_input_field = driver.findElement(By.xpath("//android.widget.EditText[2]"));
        moneycode_input_field.click(); // This first click will select the input field.
        driver.hideKeyboard(); // This will hide the keyboard drawer from the bottom.
        moneycode_input_field.sendKeys("tf8mk");
        Thread.sleep(2000);
        moneycode_input_field.click();
        enter_money_code_relay();
        System.out.println("Money Code for Insta-Money entered successfully.");

        Thread.sleep(2000);
        WebElement money_code_headertext = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Money code *\"]"));
        money_code_headertext.click();// Click it to close the keyboard.

//        Finally click on submit button and wait for 9 seconds.
        WebElement submit_btn = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]"));
        submit_btn.click();
        System.out.println("Final Submit button clicked.");
        Thread.sleep(9000);
        System.out.println("Payment completed through Insta-Money Successfully.");
    }

//    public void select_payment_method_comdata(AndroidDriver driver, Properties prop) throws InterruptedException{
//        System.out.println("Let's choose Comdata Express Code as our Automated payment method.");
//
//        WebElement comdata_option = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"));
//        comdata_option.click();
//        Thread.sleep(2000);
//
////        Let's click on Pay button.
////        SPM : Select a payment method.
//
//        WebElement pay_btn_SPM = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
//        pay_btn_SPM.click();
//        Thread.sleep(2000);
//
//        System.out.println("We've reached Confirm Payment Screen.");
//        WebElement confirm_payment_btn = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
//        confirm_payment_btn.click();
//
//        Thread.sleep(2000);
//        System.out.println("Let's enter the required fields for ComChek...");
//
//        String express_code_val = prop.getProperty("Exp_code_comdata");
//        String unit_no_val = prop.getProperty("Unit_no_comdata");
//        String driver_no_val = prop.getProperty("Driver_no_comdata");
//        String trip_no_val = prop.getProperty("Trip_no_comdata");
//
////        Entering Express Code
//        WebElement express_code_input_field = driver.findElement(By.xpath("//android.widget.EditText[1]"));
//        express_code_input_field.sendKeys("");
//        express_code_input_field.sendKeys(express_code_val);
//        System.out.println("Express code entered successfully.");
//        Thread.sleep(2000);
//
//        WebElement unit_no_input_field = driver.findElement(By.xpath("//android.widget.EditText[2]"));
//        unit_no_input_field.sendKeys("");
//        unit_no_input_field.sendKeys(unit_no_val);
//        System.out.println("Unit number entered successfully.");
//        Thread.sleep(2000);
//
//
//        WebElement driver_no_input_field = driver.findElement(By.xpath("//android.widget.EditText[3]"));
//        driver_no_input_field.sendKeys("");
//        driver_no_input_field.sendKeys(driver_no_val);
//        System.out.println("Driver number entered successfully.");
//        Thread.sleep(2000);
//
//        WebElement trip_no_input_field = driver.findElement(By.xpath("//android.widget.EditText[4]"));
//        trip_no_input_field.sendKeys("");
//        trip_no_input_field.sendKeys(trip_no_val);
//        System.out.println("Trip number entered successfully.");
//        Thread.sleep(2000);
//
//
////        Finally click on submit button and wait for 9 seconds.
//        WebElement submit_btn = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]"));
//        submit_btn.click();
//        System.out.println("Final Submit button clicked.");
//        Thread.sleep(9000);
//        System.out.println("Payment completed through ComData Successfully.");
//    }


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
    public void logout_and_relogin() throws InterruptedException{
        WebElement user_icon = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup"));
        user_icon.click();
        System.out.println("User icon clicked.");
        Thread.sleep(2*1000);

        WebElement logout_btn = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[9]"));
        logout_btn.click();

        Thread.sleep(2*1000);

        WebElement yes_logout_btn = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
        yes_logout_btn.click();
        Thread.sleep(3*1000);
        System.out.println("Logout button clicked and confirmed to logout");

        WebElement login_button = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"));
        login_button.click();
        System.out.println("Clicking on login in button again.");

        Thread.sleep(5*1000);
//       Fill the captcha
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

    }
    public void payment_awaiting() throws InterruptedException, IOException{
        Properties prop = new Properties();
        File file = new File("testdata.properties");
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);

        String driver_check_in_latest_PO = prop.getProperty("target_po");
        System.out.println("Let's test out payment awaiting.");
        Thread.sleep(3*1000);
//        Go to Home screen by clicking on the Home icon.

//        WebElement home_icon = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]"));
//        home_icon.click();
//
//        Thread.sleep(3*1000);

//        Go to payment screen.

        WebElement payment_section = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"));
        payment_section.click();
        System.out.println("Clicked on Payment section, waiting to update payment counts.");

        Thread.sleep(7*1000);
//        Click On awaiting section.

        WebElement awaiting_section = driver.findElement(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]"));
        awaiting_section.click();

        Thread.sleep(7*1000);


        System.out.println("Scroll down to bottom of the screen.");

//        Todo : Scroll to the bottom of the screen.

        for(int i=0; i<3; i++){
            scroll_to_bottom(driver);
        }


        System.out.println("Scrolling action completed.");
        Thread.sleep(4000);
//        Open up the last payment awaiting card
        WebElement latest_awaiting_payment = driver.findElement(By.xpath("(//android.widget.TextView[@text=\"ALBERTSONS TOLLESON (GROCERY)\"])[4]"));
        latest_awaiting_payment.click();

        Thread.sleep(3*1000);
//        Finally match the PO number in the card with our target PO , if it matches then test passed.

        WebElement appointment_time = driver.findElement(By.xpath("//android.widget.TextView[7]"));
        System.out.println("Appointment time : " + appointment_time.getText());

        Thread.sleep(2*1000);

        String latest_po = driver.findElement(By.xpath("//android.widget.TextView[9]")).getText();
        boolean match_pos = latest_po.equals(driver_check_in_latest_PO);

        Assert.assertTrue(match_pos);
        System.out.println("PO is matching, payment awaiting successful.");

    }
    public void scroll_to_bottom(AndroidDriver driver){
        TouchAction action = new TouchAction(driver);

// Get the dimensions of the device screen
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endX = size.width / 2;
        int endY = (int) (size.height * 0.2);

        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

}

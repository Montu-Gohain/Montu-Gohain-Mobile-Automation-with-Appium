package FreightSmith;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class captchaFilling extends Login {

    @Test
    public void captcha_Filling() throws InterruptedException {
        Thread.sleep(2000);
        try {
            String captchaText = driver.findElement(By.xpath("//android.widget.TextView")).getText();
            System.out.println(captchaText);

            String option =captchaText.split("\\s+")[2];
            System.out.println(option);
            String xpath = getOptionXPath(option);


            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(2*1000);
            String captchaText_2 = driver.findElement(By.xpath("//android.widget.TextView")).getText();
            System.out.println(captchaText_2);

            String option_2 =captchaText_2.split("\\s+")[2];
            System.out.println(option_2);
            String xpath2 = getOptionXPath_2(option);
            driver.findElement(By.xpath(xpath2)).click();
            System.out.println("Captcha Filling completed.");

            Thread.sleep(5* 1000);
            WebElement location_permission_accept = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]"));
            location_permission_accept.click();

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

}

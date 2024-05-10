# Montu-Gohain-Mobile-Automation-with-Appium

Ways to run the test scripts locally and in Browserstack.

1. The main branch contains the code to run the script locally (On Real Device / Emulator)
2. While running a test in Browserstack we can create a temporary git branch and there we have to follow some step to run these test on cloud.
3. Let's discuss about the steps.
   1. Remove the recurse_till_download function and the highlighted part.
      ![image](https://github.com/Montu-Gohain/Montu-Gohain-Mobile-Automation-with-Appium/assets/76866991/9b54efd0-9af0-4ac4-a27c-aee6c53f4978)

   2. Install Browserstack plugin and use the plugin. In the plugin click on : Integrate with App Automate sdk.This will generate a browserstack.yml file and download the required mvn dependencies automatically.
      ![image](https://github.com/Montu-Gohain/Montu-Gohain-Mobile-Automation-with-Appium/assets/76866991/f764c12f-1d0d-4a4a-8e57-5f58fa81863f)
   3. In the browserstack.yml file add the env values accordingly. This includes , 
     ```yml
      framework : testng
      username : <enter your accordingly>
      passkey : <Copy from browserstack>
      gpsLocation: 33.449626,-112.248189
     ```
   4. After adding all these required values , you can run the test file as you ran to perform the locally.While the script runs in IDE, you'll see it also running on browserstack dashboard along with the project build name.
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



# Test Results public link : 

 >  Test 1 : Sign In and Edit Profile : 
https://app-automate.browserstack.com/builds/79de7bbbb45ecf854d48534a0e9160183af1c1b7/sessions/317a5594550a1e8ea7f8f2ede5ed2ff8a9d05605?auth_token=e000925def09303dd8bf0140ffb46e5c463853616522d22c8f4dfa5ea1421626

 >  Test 2 : Driver CheckIn and PO Verification : https://app-automate.browserstack.com/builds/29e137d6ecb23ae8f4cf69acb29563225f400a91/sessions/b2bc77ce29664ba46a625b704176e32ecaebc4d1?auth_token=a71a219163142b771f8fd322cf5fef0e4b2f6dadc412ab9559719f342e5cce0b
 
 >  Test 3 : Payment Section (Download receipt & Share receipt) :  https://app-automate.browserstack.com/builds/22739f5ef31fcb87e5d0ea387012376faa2f8204/sessions/18a131f493e65f8a1903a4185b3bfe22f2338b5e?auth_token=7883c3577b06fb177e0259ab9f02c9411f9b1f092b22ceb787f5837d7e9ad196
 
 >  Test 4 : Payment section Awaiting payments : https://app-automate.browserstack.com/builds/a3c9398590f495110950a788c2239547bd9301c5/sessions/173791fa801abfa9aa8c4d59d4aa6637dc1eb2cb?auth_token=d83a9dfd1956cdb46b29b54146ad754486412a05d8a705d74b30ce0f0ee51f7b
package app.android;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
//import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DeviceFeaturesAndroid {
    public static String username = System.getenv("BROWSERSTACK_USERNAME");
    public static String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public static String QR_IMAGE = System.getenv("QR_IMAGE");
    public static String CAMERA_INJECTION_IMAGE = System.getenv("CAMERA_INJECTION_IMAGE");
    public static String BROWSERSTACK_APP_ID = System.getenv("BROWSERSTACK_APP_ID"); //regression.apk
    @Test
    public void cameraInjectionTest() throws MalformedURLException, InterruptedException {
        String mediaUrl = CAMERA_INJECTION_IMAGE;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","Google Pixel 7");
        caps.setCapability("build","Device Features - Android");
        caps.setCapability("name","cameraInjectionTest");
        caps.setCapability("autoGrantPermissions","true");
        caps.setCapability("interactiveDebugging","true");
        caps.setCapability("app",BROWSERSTACK_APP_ID); //regression.apk
        caps.setCapability("browserstack.enableCameraImageInjection", "true");

        AndroidDriver driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.executeScript("browserstack_executor: {\"action\":\"cameraImageInjection\", \"arguments\": {\"imageUrl\" : \""+mediaUrl+"\"}}");
        driver.findElement(By.id("com.example.all_in_one:id/camintent")).click();
        driver.findElement(By.id("com.example.all_in_one:id/camera_button")).click();
        //driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();

//        try{
//            driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        try{
//            driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
//        }catch (Exception e){
//            System.out.println(e);
//        }
        driver.findElement(By.id("com.google.android.GoogleCamera:id/center_placeholder")).click();
        driver.findElement(By.id("com.google.android.GoogleCamera:id/center_placeholder")).click();
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void qrScanTest() throws MalformedURLException, InterruptedException {
        String mediaUrl = QR_IMAGE;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","Google Pixel 7");
        caps.setCapability("build","Device Features - Android");
        caps.setCapability("name","qrScanTest");
        caps.setCapability("autoGrantPermissions","true");
        caps.setCapability("interactiveDebugging","true");
        caps.setCapability("app",BROWSERSTACK_APP_ID); //regression.apk
        caps.setCapability("browserstack.enableCameraImageInjection", "true");

        AndroidDriver driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.executeScript("browserstack_executor: {\"action\":\"cameraImageInjection\", \"arguments\": {\"imageUrl\" : \""+mediaUrl+"\"}}");
        driver.findElement(By.id("com.example.all_in_one:id/camera1Bar")).click();
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void biometricTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","Google Pixel 7");
        caps.setCapability("build","Device Features - Android");
        caps.setCapability("name","biometricTest");
        caps.setCapability("autoGrantPermissions","true");
        caps.setCapability("interactiveDebugging","true");
        caps.setCapability("app",BROWSERSTACK_APP_ID); //regression.apk
        caps.setCapability("browserstack.enableBiometric", "true");

        AndroidDriver driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.findElement(By.id("com.example.all_in_one:id/bio_prompt")).click();
        driver.findElement(By.id("com.example.all_in_one:id/prompt")).click();
        driver.executeScript("browserstack_executor: {\"action\":\"biometric\", \"arguments\": {\"biometricMatch\" : \"pass\"}}");
        driver.findElement(By.id("com.example.all_in_one:id/prompt")).click();
        driver.executeScript("browserstack_executor: {\"action\":\"biometric\", \"arguments\": {\"biometricMatch\" : \"fail\"}}");
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void devicePasscodeTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","Google Pixel 7");
        caps.setCapability("build","Device Features - Android");
        caps.setCapability("name","passcodeTest");
        caps.setCapability("autoGrantPermissions","true");
        caps.setCapability("interactiveDebugging","true");
        caps.setCapability("enablePasscode","true");
        caps.setCapability("app",BROWSERSTACK_APP_ID); //regression.apk

        AndroidDriver driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);


        driver.findElement(By.id("com.example.all_in_one:id/passcode")).click();

        //driver.findElement(By.id("com.example.all_in_one:id/passcode")).click();
        driver.findElement(By.id("com.example.all_in_one:id/deviceSecure")).click();
        Thread.sleep(5000);
        driver.quit();
    }


}

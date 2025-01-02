package app.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.percy.appium.AppPercy;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;


public class AndroidBaseTest {

    public AppiumDriver driver;
    //public AppPercy appPercy;
    /*@BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        MutableCapabilities capabilities = new UiAutomator2Options();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        //appPercy = new AppPercy(driver);
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }*/

    public WebDriverWait wait;

    @SuppressWarnings("deprecation")
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:build", "BrowserStack SDK Tests");
        capabilities.setCapability("appium:project", "BrowserStack SDK Tests");

        driver = new AppiumDriver(new URL("http://hub.browserstack.com/wd/hub"), capabilities);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}

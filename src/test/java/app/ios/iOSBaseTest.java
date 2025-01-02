package app.ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.percy.appium.AppPercy;
import org.openqa.selenium.MutableCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;


public class iOSBaseTest {

    public IOSDriver driver;
    public AppPercy appPercy;
    @BeforeMethod(alwaysRun=true)
    public void setUp() throws Exception {
        MutableCapabilities options = new XCUITestOptions();
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),options);
        appPercy = new AppPercy(driver);
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}

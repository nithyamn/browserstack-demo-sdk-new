package web;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BaseTestWeb {
    public WebDriver driver;
    public static final String AUTOMATE_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    @BeforeMethod(alwaysRun = true)
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
        Map<String, Object> chromePrefs = new HashMap<>();
//        chromePrefs.put("credentials_enable_service", false);
//        chromePrefs.put("profile.password_manager_enabled", false);
//        chromePrefs.put("profile.password_manager_leak_detection", false);
        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //driver = new ChromeDriver(options);
        //browserstackOptions.put("uploadMedia",new Object[]{""});
        //DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("bstack:options", new JSONObject()
//                .put("uploadMedia", new JSONArray().put(""))
//        );
//        MutableCapabilities capabilities = new MutableCapabilities();
//        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
//        browserstackOptions.put("uploadMedia",new String[]{""});
//        capabilities.setCapability("bstack:options", browserstackOptions);

//        driver= new RemoteWebDriver(new URL(URL),capabilities);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}

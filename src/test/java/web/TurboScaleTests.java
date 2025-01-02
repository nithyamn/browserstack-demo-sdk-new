package web;

import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class TurboScaleTests {
    public WebDriver driver;
    public String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accesskey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    @BeforeMethod
    public void setup() throws MalformedURLException {

        ChromeOptions browserOptions = new ChromeOptions();
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("browser", "chrome");
//        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("projectName", "TurbosScale Project");
        browserstackOptions.put("buildName", "Bstackdemo Web Tests");
        browserstackOptions.put("sessionName", "e2eTest");
//        browserstackOptions.put("buildTags", new String[]{"e2e"});
        browserstackOptions.put("username", username);
        browserstackOptions.put("accessKey", accesskey);
        browserOptions.setCapability("bstack:options", browserstackOptions);

        driver = new RemoteWebDriver(new URL("https://xsp07ibw-hub.browserstack-ats.com/wd/hub"),browserOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @Test
    public void test() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        driver.get("https://bstackdemo.com/");
        driver.findElement(By.id("signin")).click();
        driver.findElement(By.cssSelector("#username input")).sendKeys("demouser", Keys.ENTER);
        driver.findElement(By.cssSelector("#password input")).sendKeys("testingisfun99", Keys.ENTER);
        driver.findElement(By.id("login-btn")).click();

        String productName = "iPhone 12";
        List<WebElement> allProductsList = driver.findElements(By.cssSelector(".shelf-item"));
        for(int i=0;i<allProductsList.size();i++){
            WebElement selectProduct = allProductsList.get(i);
            if(selectProduct.findElement(By.cssSelector(".shelf-item__title")).getText().equals(productName)){
                selectProduct.findElement(By.cssSelector(".shelf-item__buy-btn")).click();
                System.out.println(productName + " added to bag!");
                break;
            }else{
                continue;
            }
        }

        driver.findElement(By.cssSelector(".buy-btn")).click();
        driver.findElement(By.id("firstNameInput")).sendKeys("Nithya");
        driver.findElement(By.id("lastNameInput")).sendKeys("Mani");
        driver.findElement(By.id("addressLine1Input")).sendKeys("Mumbai");
        driver.findElement(By.id("provinceInput")).sendKeys("Maharashtra");
        driver.findElement(By.id("postCodeInput")).sendKeys("400080");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String confirmationMessage = driver.findElement(By.id("confirmation-message")).getText();
        String orderId = driver.findElement(By.cssSelector(".checkout-form div:nth-child(2) strong")).getText();
        Assert.assertTrue(confirmationMessage.contains("successfully"));
    }
    @AfterMethod
    public void teardown(ITestResult result){
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        if( result.getStatus() == ITestResult.SUCCESS) {
            //jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \""+result.getName()+" passed!\"}}");
            setSessionStatus("passed","Order placed successfully!");
        }
        else{
            //jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"failed\", \"reason\": \""+result.getThrowable()+"\"}}");
            setSessionStatus("failed","Something went wrong!");
        }
        driver.quit();
    }

    public void setSessionStatus(String status, String reason){
        final JavascriptExecutor jse = (JavascriptExecutor) driver;
        JSONObject executorObject = new JSONObject();
        JSONObject argumentsObject = new JSONObject();
        argumentsObject.put("status", status);
        argumentsObject.put("reason", reason);
        executorObject.put("action", "setSessionStatus");
        executorObject.put("arguments", argumentsObject);
        jse.executeScript(String.format("browserstack_executor: %s", executorObject));
    }
}

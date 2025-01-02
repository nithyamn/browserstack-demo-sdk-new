package web;

import io.percy.selenium.Percy;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class BasicTest {
    public WebDriver driver;
    public String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accesskey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public Percy percy;
    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "11");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "latest");
        driver = new RemoteWebDriver(new URL("https://"+username+":"+accesskey+"@hub-cloud.browserstack.com/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        percy = new Percy(driver);
    }
    @Test
    public void test() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        driver.get("https://bstackdemo.com/");
        percy.snapshot("Home Page");
        driver.findElement(By.id("signin")).click();
        driver.findElement(By.cssSelector("#username input")).sendKeys("demouser", Keys.ENTER);
        driver.findElement(By.cssSelector("#password input")).sendKeys("testingisfun99", Keys.ENTER);
        driver.findElement(By.id("login-btn")).click();
        jse.executeScript("browserstack_executor: {\"action\": \"annotate\", \"arguments\": {\"data\": \"Logged In\", \"level\": \"info\"}}");

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
        //driver.findElement(By.cssSelector(".float-cart__close-btn")).click();
        driver.findElement(By.cssSelector(".buy-btn")).click();
        driver.findElement(By.id("firstNameInput")).sendKeys("Nithya");
        driver.findElement(By.id("lastNameInput")).sendKeys("Mani");
        driver.findElement(By.id("addressLine1Input")).sendKeys("Mumbai");
        driver.findElement(By.id("provinceInput")).sendKeys("Maharashtra");
        driver.findElement(By.id("postCodeInput")).sendKeys("400080");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        String confirmationMessage = driver.findElement(By.id("confirmation-message")).getText();
        percy.snapshot("Final Page");
        String orderId = driver.findElement(By.cssSelector(".checkout-form div:nth-child(2) strong")).getText();
        Assert.assertTrue(confirmationMessage.contains("successfully"));
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

package web;
import io.appium.java_client.ios.IOSDriver;
import io.percy.selenium.Percy;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


class SingleWeb extends BaseTestWeb {
    public JavascriptExecutor jse;

    @Test
    public void e2eTest() throws Exception {
        /*** Fetch Session ID***/
//        Percy percy = new Percy(driver);
//        SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
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
        //driver.findElement(By.cssSelector(".float-cart__close-btn")).click();
        driver.findElement(By.cssSelector(".buy-btn")).click();
        driver.findElement(By.id("firstNameInput")).sendKeys("Nithya");
        driver.findElement(By.id("lastNameInput")).sendKeys("Mani");
        driver.findElement(By.id("addressLine1Input")).sendKeys("Mumbai");
        driver.findElement(By.id("provinceInput")).sendKeys("Maharashtra");
        driver.findElement(By.id("postCodeInput")).sendKeys("400080");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String confirmationMessage = driver.findElement(By.id("confirmation-message")).getText();
        String orderId = driver.findElement(By.cssSelector(".checkout-form div:nth-child(2) strong")).getText();
        driver.findElement(By.className("optimizedCheckout-buttonSecondary")).click();
        //percy.screenshot("Final Page");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("browserstack_executor: {\"action\": \"annotate\", \"arguments\": {\"data\": \"https://bstackdemo.com/\", \"level\": \"warn\"}}");


        Assert.assertTrue(confirmationMessage.contains("successfully"));

    }
}

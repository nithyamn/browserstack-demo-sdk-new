package app.android;

import io.appium.java_client.AppiumBy;
//import io.percy.appium.AppPercy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class AndroidTest extends AndroidBaseTest {

    @Test
    public void wikiAppTest() throws Exception {
//        AppPercy percy = new AppPercy(driver);
        //appPercy.screenshot("First Page");
      WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));

      searchElement.click();
      WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
          ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
      insertTextElement.sendKeys("BrowserStack");
      Thread.sleep(5000);



      List<WebElement> allProductsName = driver.findElements(AppiumBy.className("android.widget.TextView"));
      Assert.assertTrue(allProductsName.size() > 0);
    }
}

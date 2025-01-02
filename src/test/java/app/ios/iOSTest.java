package app.ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class iOSTest extends iOSBaseTest {

  @Test
  public void testOutputField() throws Exception {
    appPercy.screenshot("First iOS Screenshot", false);
    WebElement textButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Button")));
    textButton.click();
    WebElement textInput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Input")));
    textInput.sendKeys("hello@browserstack.com"+"\n");

    Thread.sleep(5000);

    WebElement textOutput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Output")));

    Assert.assertEquals(textOutput.getText(),"hello@browserstack.com");
  }
}

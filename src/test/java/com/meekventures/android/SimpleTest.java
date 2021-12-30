package com.meekventures.android;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class SimpleTest {

    private static WebDriver driver;
    private String emailAddress = "rajmentor-KattieSchuppeGladyce.Lind99@gmail.com";
    private String password = "test123";

    @BeforeAll
    static void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Pixel XL API 30");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("app", "/Users/R.Beemireddy/Documents/projects/personal/meek-android-appium-tests/src/test/resources/apps/beta/meek-android-debug.apk");
        desiredCapabilities.setCapability("appPackage", "com.meekventures.meek");

        try {
            driver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(60, java.util.concurrent.TimeUnit.SECONDS);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    @Disabled
    public void test() {
        WebElement email = driver.findElement(By.id("com.meekventures.meek:id/edtEmail"));
        email.click();
        email.sendKeys(this.emailAddress);

        WebElement password = driver.findElement(By.id("com.meekventures.meek:id/edtPassword"));
        password.click();
        password.sendKeys(this.password);

        WebElement keepMeLoggedIn = driver.findElement(By.id("com.meekventures.meek:id/checkBoxLogin"));
        keepMeLoggedIn.click();

        WebElement loginButton = driver.findElement(By.partialLinkText("Login"));
        loginButton.click();
    }
}

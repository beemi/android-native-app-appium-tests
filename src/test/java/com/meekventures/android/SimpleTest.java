package com.meekventures.android;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class SimpleTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Android Emulator");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("app", "/Users/meekventures/Downloads/app-debug.apk");
        desiredCapabilities.setCapability("appPackage", "com.meekventures.android");
        desiredCapabilities.setCapability("appActivity", "com.meekventures.android.MainActivity");

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
    public void test() {
        driver.switchTo().defaultContent();
        driver.findElement(By.name("test")).click();
    }
}

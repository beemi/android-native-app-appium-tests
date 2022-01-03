package com.meekventures.android;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseAndroidTest {

    protected static AndroidDriver driver;
    private static final String APK_PATH = System.getProperty("user.dir") + "/src/test/resources/apps/beta/eaa7eddf-b305-4151-b298-cb7ca5079fb1.apk";
    private static final String APP_PACKAGE = "com.meekventures.meek";
    private static final String DEVICE_NAME = "Pixel XL API 30";
    private static final String PLATFORM_NAME = "Android";

    @BeforeAll
    public static void setUp() {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);
        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("app", APK_PATH);
        desiredCapabilities.setCapability("appPackage", APP_PACKAGE);

        try {
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(60, java.util.concurrent.TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

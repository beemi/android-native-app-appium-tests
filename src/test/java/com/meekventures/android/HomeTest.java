package com.meekventures.android;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.*;


public class HomeTest {

    private static AppiumDriver driver;
    private static String emailAddress = "rajmentor-KattieSchuppeGladyce.Lind99@gmail.com";
    private static String password = "test123";

    @BeforeAll
    static void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Pixel XL API 30");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("app", "/Users/R.Beemireddy/Documents/projects/personal/meek-android-appium-tests/src/test/resources/apps/beta/eaa7eddf-b305-4151-b298-cb7ca5079fb1.apk");
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
    public void test() throws InterruptedException {
        WebElement email = driver.findElement(By.id("edtEmail"));
        assertThat(email.isDisplayed()).as("email input should be displayed").isTrue();
        email.sendKeys(emailAddress);

        WebElement passwordInput = driver.findElement(By.id("edtPassword"));
        passwordInput.sendKeys(password);
        driver.hideKeyboard();

        WebElement keepMeLoggedIn = driver.findElement(By.id("checkBoxLogin"));
        keepMeLoggedIn.click();

        WebElement loginButton = driver.findElement(By.xpath("//*[@text='Login']"));
        loginButton.click();

        WebElement menuStartups = driver.findElement(By.id("startupConstraint"));
        assertThat(menuStartups.isDisplayed()).as("Login is not successful").isTrue();
        menuStartups.click();

        driver.findElement(By.id("topUsersLay")).isDisplayed();

        driver.findElement(By.id("imgBack")).click();

        WebElement menuMentors = driver.findElement(By.id("mentorConstraint"));
        assertThat(menuStartups.isDisplayed()).isTrue();
        menuMentors.click();

        driver.findElement(By.id("topUsersLay")).isDisplayed();
        driver.findElement(By.id("imgBack")).click();

        WebElement menuInvestors = driver.findElement(By.id("investorConstraint"));
        menuInvestors.click();
        driver.findElement(By.id("topUsersLay")).isDisplayed();
        driver.findElement(By.id("imgBack")).click();

        WebElement menuEvents = driver.findElement(By.id("eventsConstraint"));
        menuEvents.click();
        driver.findElement(By.id("relMessage")).isDisplayed();
        driver.findElement(By.id("imgBack")).click();

        WebElement menuSocieties = driver.findElement(By.id("societiesConstraint"));
        menuSocieties.click();
        driver.findElement(By.id("imgAddSociety")).isDisplayed();
        driver.findElement(By.id("imgBack")).click();
    }
}

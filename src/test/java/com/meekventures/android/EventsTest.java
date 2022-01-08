package com.meekventures.android;

import io.appium.java_client.android.AndroidElement;
import lombok.extern.java.Log;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

@Log
public class EventsTest extends BaseAndroidTest {

    private static final String emailAddress = "rajmentor-KattieSchuppeGladyce.Lind99@gmail.com";
    private static final String password = "test123";

    WebDriverWait wait = new WebDriverWait(driver, 30);

    @BeforeEach()
    public void resetAppBefore() {
        driver.resetApp();
    }

    @AfterEach
    public void resetAppAfter() {
        driver.resetApp();
    }

    @Test
    public void home_screen_tests() {
        val email = driver.findElement(By.id("edtEmail"));
        assertThat(email.isDisplayed()).as("email input should be displayed").isTrue();
        email.sendKeys(emailAddress);
        log.info("email input is displayed");

        val passwordInput = driver.findElement(By.id("edtPassword"));
        passwordInput.sendKeys(password);
        driver.hideKeyboard();
        log.info("password input is displayed");

        val keepMeLoggedIn = driver.findElement(By.id("checkBoxLogin"));
        keepMeLoggedIn.click();
        log.info("keep me logged in checkbox is displayed");

        WebElement loginButton = driver.findElement(By.xpath("//*[@text='Login']"));
        loginButton.click();
        log.info("login button is displayed and clicked");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("events"))
        ));

        val eventsOption = driver.findElementById("events");
        assertThat(eventsOption.isDisplayed()).as("events option should be displayed").isTrue();
        eventsOption.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("addNewEvent"))
        ));
        log.info("events option is displayed and clicked");
        assertThat(driver.findElementById("imgTrending").isDisplayed())
                .as("Trending event image is displayed").isTrue();

        val numberOfEvents = driver.findElementsById("nameTv");
        assertThat(numberOfEvents.size()).isGreaterThan(0);
        log.info("number of events is greater than 0");

        // select 1st event
        ((AndroidElement) numberOfEvents.get(0)).click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("btnRegister"))
        ));

        val registerEvent = driver.findElementById("btnRegister");
        assertThat(registerEvent.isDisplayed()).as("register event button should be displayed").isTrue();
        registerEvent.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("tvCloseDate"))
        ));
        assertThat(driver.findElementById("tvCloseDate").isDisplayed()).isTrue();
        log.info("register event button is displayed and clicked");

        // Plus symbol
        val plusSymbol = driver.findElementsById("btnPlus");
        ((AndroidElement) plusSymbol.get(0)).click();
        log.info("plus symbol is displayed and clicked");

        val registerEventNext = driver.findElementById("btnRegister");
        assertThat(registerEventNext.isEnabled()).as("register event button should be enabled").isTrue();
        registerEventNext.click();
    }
}

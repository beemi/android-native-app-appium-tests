package com.meekventures.android;

import lombok.extern.java.Log;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

@Log
public class ForgotPasswordTest extends BaseAndroidTest {

    WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeEach()
    public void resetAppBefore() {
        driver.resetApp();

    }

    @AfterEach
    public void resetAppAfter() {
        driver.resetApp();
    }

    @Test
    public void forgot_PasswordTest() {
        val forgotYourPasswordOption = driver.findElementByXPath("//*[@text='Forgot your password?']");
        assertThat(forgotYourPasswordOption.isDisplayed()).as("Forgot your password input should be displayed")
                .isTrue();
        forgotYourPasswordOption.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("edtEmail"))
        ));
        val emailInput = driver.findElementById("edtEmail");
        assertThat(emailInput.isDisplayed()).as("Email input should be displayed").isTrue();
        emailInput.sendKeys("test@test.com");

        val sendEmail = driver.findElementByXPath("//*[@text='Send Email']");
        assertThat(sendEmail.isDisplayed()).as("Send Email button should be displayed").isTrue();
        sendEmail.click();
    }
}

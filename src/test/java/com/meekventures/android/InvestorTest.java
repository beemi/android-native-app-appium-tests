package com.meekventures.android;

import io.appium.java_client.android.AndroidElement;
import lombok.extern.java.Log;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Log
public class InvestorTest extends BaseAndroidTest{

    @BeforeEach()
    public void resetAppBefore() {
        driver.resetApp();
    }

    @AfterEach
    public void resetAppAfter() {
        driver.resetApp();
    }

    @Test
    public void investor_registration(){
        val email = driver.findElement(By.id("edtEmail"));
        assertThat(email.isDisplayed()).as("email input should be displayed").isTrue();
        val joinNow = driver.findElement(By.xpath("//*[@text='Join Now!']"));
        joinNow.click();

        val studentRegisterOption = driver.findElement(By.id("sign_up_as_investor_image"));
        assertThat(studentRegisterOption.isDisplayed()).as("Signup as Entrepreneur option displayed").isTrue();
        studentRegisterOption.click();

        val firstName = driver.findElement(By.id("edtFirstName"));
        assertThat(firstName.isDisplayed()).as("First name input should be displayed").isTrue();
        firstName.sendKeys("Raju");
        driver.hideKeyboard();

        val lastName = driver.findElement(By.id("edtLastName"));
        assertThat(lastName.isDisplayed()).as("Last name input should be displayed").isTrue();
        lastName.sendKeys("Beemireddy");
        driver.hideKeyboard();

        val countyCodes = driver.findElement(By.id("tvCountry"));
        assertThat(countyCodes.isDisplayed()).as("Country Code input should be displayed").isTrue();
        countyCodes.click();

        val countryNames = driver.findElementsById("textView_countryName");
        assertThat(countryNames.size()).as("University list should be displayed").isGreaterThan(0);
        ((AndroidElement) countryNames.get(0)).click();

        val random = new Random();

        val mobileNumber = "1234567" + random.nextInt(999999);
        val contact = driver.findElement(By.id("edtPhone"));
        contact.sendKeys(mobileNumber);

        val emailAddress = driver.findElement(By.id("edtEmail"));
        assertThat(emailAddress.isDisplayed()).as("Email input should be displayed").isTrue();
        emailAddress.sendKeys("test-" + (UUID.randomUUID().toString().replaceAll("-", "")) + "@example.com");
        driver.hideKeyboard();

        val password = driver.findElement(By.id("edtPassword"));
        assertThat(password.isDisplayed()).as("Password input should be displayed").isTrue();
        password.sendKeys("test1212121");
        driver.hideKeyboard();

        val confirmPassword = driver.findElement(By.id("edtConfirmPassword"));
        assertThat(confirmPassword.isDisplayed()).as("Confirm password input should be displayed").isTrue();
        confirmPassword.sendKeys("test1212121");
        driver.hideKeyboard();

        val ternsAndCondition = driver.findElement(By.id("termsCondition"));
        assertThat(ternsAndCondition.isDisplayed()).as("Terms and condition input should be displayed").isTrue();
        ternsAndCondition.click();

        TestUtils.scrollToElement("com.meekventures.meek:id/signUpNowButton");

        val signupButton = driver.findElement(By.id("signUpNowButton"));
        assertThat(signupButton.isDisplayed()).as("Signup button should be displayed").isTrue();
        signupButton.click();

        val registrationSuccess = driver.findElementById("confirmEmailHint");
        assertThat(registrationSuccess.isDisplayed()).as("Registration success message should be displayed").isTrue();
        assertThat(driver.findElementById("loginBtn").isDisplayed()).as("Login Now displayed").isTrue();
        log.info("Registration Success");
    }
}

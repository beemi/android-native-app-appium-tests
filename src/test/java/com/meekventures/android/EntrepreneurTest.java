package com.meekventures.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class EntrepreneurTest extends BaseAndroidTest {

    @BeforeEach()
    public void resetAppBefore() {
        driver.resetApp();
    }

    @AfterEach
    public void resetAppAfter() {
        driver.resetApp();
    }

    @Test
    public void entrepreneur_registration() {
        WebElement email = driver.findElement(By.id("edtEmail"));
        assertThat(email.isDisplayed()).as("email input should be displayed").isTrue();
        WebElement joinNow = driver.findElement(By.xpath("//*[@text='Join Now!']"));
        joinNow.click();

        WebElement studentRegisterOption = driver.findElement(By.id("sign_up_as_entrepreneur_image"));
        assertThat(studentRegisterOption.isDisplayed()).as("Signup as Entrepreneur option displayed").isTrue();
        studentRegisterOption.click();

        WebElement firstName = driver.findElement(By.id("edtFirstName"));
        assertThat(firstName.isDisplayed()).as("First name input should be displayed").isTrue();
        firstName.sendKeys("Raju");
        driver.hideKeyboard();

        WebElement lastName = driver.findElement(By.id("edtLastName"));
        assertThat(lastName.isDisplayed()).as("Last name input should be displayed").isTrue();
        lastName.sendKeys("Beemireddy");
        driver.hideKeyboard();

        WebElement company = driver.findElementById("edtCompany");
        assertThat(company.isDisplayed()).as("Company input should be displayed").isTrue();
        company.sendKeys("Test Company");

        WebElement countyCodes = driver.findElement(By.id("tvCountry"));
        assertThat(countyCodes.isDisplayed()).as("Country Code input should be displayed").isTrue();
        countyCodes.click();

        List countryNames = driver.findElementsById("textView_countryName");
        assertThat(countryNames.size()).as("University list should be displayed").isGreaterThan(0);
        ((AndroidElement) countryNames.get(0)).click();

        Random random = new Random();

        String mobileNumber = "1234567" + random.nextInt(999999);
        WebElement contact = driver.findElement(By.id("edtPhone"));
        contact.sendKeys(mobileNumber);

        WebElement emailAddress = driver.findElement(By.id("edtEmail"));
        assertThat(emailAddress.isDisplayed()).as("Email input should be displayed").isTrue();
        emailAddress.sendKeys("test-" + (UUID.randomUUID().toString().replaceAll("-", "")) + "@example.com");
        driver.hideKeyboard();

        WebElement password = driver.findElement(By.id("edtPassword"));
        assertThat(password.isDisplayed()).as("Password input should be displayed").isTrue();
        password.sendKeys("test1212121");
        driver.hideKeyboard();

        WebElement confirmPassword = driver.findElement(By.id("edtConfirmPassword"));
        assertThat(confirmPassword.isDisplayed()).as("Confirm password input should be displayed").isTrue();
        confirmPassword.sendKeys("test1212121");
        driver.hideKeyboard();

        WebElement ternsAndCondition = driver.findElement(By.id("termsCondition"));
        assertThat(ternsAndCondition.isDisplayed()).as("Terms and condition input should be displayed").isTrue();
        ternsAndCondition.click();

        driver.executeScript("scroll", ImmutableMap.of("direction", "down"));
        WebElement signupButton = driver.findElement(By.id("signUpNowButton"));
        assertThat(signupButton.isDisplayed()).as("Signup button should be displayed").isTrue();
        signupButton.click();

//        WebElement registrationSuccess = driver.findElementById("btnMentor");
//        assertThat(registrationSuccess.isDisplayed()).as("Registration success message should be displayed").isTrue();
//        assertThat(driver.findElementById("tvGoHome").isDisplayed()).as("Go Back to home page option").isTrue();
//        driver.findElement(By.id("tvGoHome")).click();
//        assertThat(email.isDisplayed()).as("email input should be displayed").isTrue();
        WebElement registrationSuccess = driver.findElementById("confirmEmailHint");
        assertThat(registrationSuccess.isDisplayed()).as("Registration success message should be displayed").isTrue();
        assertThat(driver.findElementById("loginBtn").isDisplayed()).as("Login Now displayed").isTrue();
    }
}

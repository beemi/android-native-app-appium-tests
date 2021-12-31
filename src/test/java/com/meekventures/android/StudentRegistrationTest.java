package com.meekventures.android;

import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class StudentRegistrationTest extends BaseAndroidTest {

    private static String emailAddress = "rajmentor-KattieSchuppeGladyce.Lind99@gmail.com";
    private static String password = "test123";

    @BeforeEach()
    public void resetAppBefore() {
        driver.resetApp();
    }

    @AfterEach
    public void resetAppAfter() {
        driver.resetApp();
    }

    @Test
    public void student_register() {
        WebElement email = driver.findElement(By.id("edtEmail"));
        assertThat(email.isDisplayed()).as("email input should be displayed").isTrue();
        WebElement joinNow = driver.findElement(By.xpath("//*[@text='Join Now!']"));
        joinNow.click();

        WebElement studentRegisterOption = driver.findElement(By.id("sign_up_as_student_image"));
        assertThat(studentRegisterOption.isDisplayed()).as("Signup as student option displayed").isTrue();
        studentRegisterOption.click();

        WebElement firstName = driver.findElement(By.id("edtFirstName"));
        assertThat(firstName.isDisplayed()).as("First name input should be displayed").isTrue();
        firstName.sendKeys("Raju");
        driver.hideKeyboard();

        WebElement lastName = driver.findElement(By.id("edtLastName"));
        assertThat(lastName.isDisplayed()).as("Last name input should be displayed").isTrue();
        lastName.sendKeys("Beemireddy");
        driver.hideKeyboard();

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

        WebElement selectUniversity = driver.findElement(By.id("universitySpinner"));
        assertThat(selectUniversity.isDisplayed()).as("University input should be displayed").isTrue();
        selectUniversity.click();
        WebElement myUniversity = driver.findElement(By.id("search_src_text"));
        myUniversity.sendKeys("Abertay University");
        List universityList = driver.findElementsById("tv_search_list_item");
        assertThat(universityList.size()).as("University list should be displayed").isGreaterThan(0);
        ((AndroidElement) universityList.get(0)).click();

        WebElement ternsAndCondition = driver.findElement(By.id("termsCondition"));
        assertThat(ternsAndCondition.isDisplayed()).as("Terms and condition input should be displayed").isTrue();
        ternsAndCondition.click();

        WebElement signupButton = driver.findElement(By.id("signUpNowButton"));
        assertThat(signupButton.isDisplayed()).as("Signup button should be displayed").isTrue();
        signupButton.click();

        WebElement registrationSuccess = driver.findElementById("confirmEmailHint");
        assertThat(registrationSuccess.isDisplayed()).as("Registration success message should be displayed").isTrue();
        assertThat(driver.findElementById("loginBtn").isDisplayed()).as("Login Now displayed").isTrue();
    }
}

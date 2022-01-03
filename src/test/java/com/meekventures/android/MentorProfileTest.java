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
public class MentorProfileTest extends BaseAndroidTest {

    private static final String emailAddress = "rajmentor-KattieSchuppeGladyce.Lind99@gmail.com";
    private static final String password = "test123";

    WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeEach()
    public void resetAppBefore() {
        driver.resetApp();

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

        val loginButton = driver.findElement(By.xpath("//*[@text='Login']"));
        loginButton.click();
        log.info("login button is displayed and clicked");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("profile"))
        ));
    }

    @AfterEach
    public void resetAppAfter() {
        driver.resetApp();
    }

    @Test
    public void mentor_profile_update_test() {

        val profile = driver.findElement(By.id("profile"));
        assertThat(profile.isDisplayed()).as("profile should be displayed").isTrue();
        log.info("profile is displayed");
        profile.click();
        log.info("profile is clicked");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("tvEditProfile"))
        ));

        val userType = driver.findElement(By.id("tvUserType"));
        assertThat(userType.isDisplayed()).as("profile type should be displayed").isTrue();
        assertThat(userType.getText()).as("profile type should be Mentor").isEqualTo("Mentor");

        val editProfile = driver.findElement(By.id("tvEditProfile"));
        assertThat(editProfile.isDisplayed()).as("edit profile should be displayed").isTrue();
        log.info("edit profile is displayed");

        // Update the profile
        editProfile.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("edtFirstName"))
        ));

        val editFirstName = driver.findElement(By.id("edtFirstName"));
        assertThat(editFirstName.isDisplayed()).as("edit first name should be displayed").isTrue();
        assertThat(editFirstName.getText()).as("edit first name should be Kattie").isNotNull();
        editFirstName.clear();
        editFirstName.sendKeys("Raj test Kattie");
        driver.hideKeyboard();

        // Next button
        val nextButton = driver.findElementByXPath("//*[@text='Next']");
        nextButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("tvSkip"))
        ));

        val skipButton = driver.findElement(By.id("tvSkip"));
        assertThat(skipButton.isDisplayed()).as("skip button should be displayed").isTrue();
        skipButton.click();

        // Save button Finish
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("buttonAdd"))
        ));

        val saveButton = driver.findElement(By.id("buttonAdd"));
        assertThat(saveButton.isDisplayed()).as("save button should be displayed").isTrue();
        saveButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("btnMentor"))
        ));

        val profileCreated = driver.findElementByXPath("//*[@text='Profile Created!']");
        assertThat(profileCreated.isDisplayed()).as("profile created message should be displayed").isTrue();
        assertThat(profileCreated.getText()).as("profile created message should be displayed").isEqualTo("Profile Created!");
        log.info("profile created message is displayed");

        val mentorButton = driver.findElement(By.id("btnMentor"));
        assertThat(mentorButton.isDisplayed()).as("mentor button should be displayed").isTrue();
        mentorButton.click();
        log.info("mentor button is displayed and clicked");
        assertThat(driver.findElementById("tvEditProfile").isDisplayed()).as("edit profile should be displayed").isTrue();
        log.info("edit profile is displayed");
    }

    @Test
    public void mentor_complete_now_test() {

        val completeNow = driver.findElement(By.id("completeProfile"));
        assertThat(completeNow.isDisplayed()).as("complete now should be displayed").isTrue();
        log.info("complete now is displayed");
        completeNow.click();
        log.info("complete now is clicked");

        // Update the profile
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("edtFirstName"))
        ));

        val editFirstName = driver.findElement(By.id("edtFirstName"));
        assertThat(editFirstName.isDisplayed()).as("edit first name should be displayed").isTrue();
        assertThat(editFirstName.getText()).as("edit first name should be Kattie").isNotNull();
        editFirstName.clear();
        editFirstName.sendKeys("Raj test Kattie");
        driver.hideKeyboard();

        // Next button
        val nextButton = driver.findElementByXPath("//*[@text='Next']");
        nextButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("tvSkip"))
        ));

        val skipButton = driver.findElement(By.id("tvSkip"));
        assertThat(skipButton.isDisplayed()).as("skip button should be displayed").isTrue();
        skipButton.click();

        // Save button Finish
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("buttonAdd"))
        ));

        val saveButton = driver.findElement(By.id("buttonAdd"));
        assertThat(saveButton.isDisplayed()).as("save button should be displayed").isTrue();
        saveButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("btnMentor"))
        ));

        val profileCreated = driver.findElementByXPath("//*[@text='Profile Created!']");
        assertThat(profileCreated.isDisplayed()).as("profile created message should be displayed").isTrue();
        assertThat(profileCreated.getText()).as("profile created message should be displayed").isEqualTo("Profile Created!");
        log.info("profile created message is displayed");

        val mentorButton = driver.findElement(By.id("btnMentor"));
        assertThat(mentorButton.isDisplayed()).as("mentor button should be displayed").isTrue();
        mentorButton.click();
        log.info("mentor button is displayed and clicked");
        assertThat(driver.findElementById("tvEditProfile").isDisplayed()).as("edit profile should be displayed").isTrue();
        log.info("edit profile is displayed");

    }
}

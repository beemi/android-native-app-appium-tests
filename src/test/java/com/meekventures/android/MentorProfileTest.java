package com.meekventures.android;

import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.*;
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
@Epic("Mentor Profile")
@Feature("Mentor Profile update & job apply")
public class MentorProfileTest extends BaseAndroidTest {

    @BeforeEach()
    public void resetAppBefore() throws Exception {
        driver.resetApp();

        val emailAddress = propertyReader.getProperty("mentor.email");
        val password = propertyReader.getProperty("meek_test.password");

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

    @Test
    @Description("Mentor apply job test")
    @Severity(SeverityLevel.BLOCKER)
    public void mentor_apply_job_test() {
        val jobMenu = driver.findElement(By.id("job"));
        assertThat(jobMenu.isDisplayed()).as("job menu should be displayed").isTrue();
        jobMenu.click();
        log.info("job menu is displayed and clicked");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("tvApply"))
        ));

        val applyButton = driver.findElementsById("tvApply");
        assertThat(applyButton.size()).as("apply button should be displayed & list of jobs should be greater than 1").isGreaterThan(1);
        ((AndroidElement) applyButton.get(0)).click();
        log.info("apply button is displayed and clicked");
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("edtCover"))
        ));

        val coverLetter = driver.findElementById("edtCover");
        assertThat(coverLetter.isDisplayed()).as("cover letter should be displayed").isTrue();
        coverLetter.sendKeys("This is a test cover letter");
        log.info("cover letter is displayed and typed");
        driver.hideKeyboard();

        val jobApplyButton = driver.findElementById("btnApply");
        jobApplyButton.click();
        log.info("apply button is displayed and clicked");

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("btnMoreJobs"))
        ));
        log.info("more jobs button is displayed");

        val jobApplied = driver.findElementByXPath("//*[@text='Applied Successfully!']");
        assertThat(jobApplied.isDisplayed()).as("job applied message should be displayed").isTrue();
        assertThat(jobApplied.getText()).as("job applied message should be displayed").isEqualTo("Applied Successfully!");

        val moreJobsButton = driver.findElementById("btnMoreJobs");
        assertThat(moreJobsButton.isDisplayed()).as("more jobs button should be displayed").isTrue();
        moreJobsButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("imgFilter"))
        ));

        val filterButton = driver.findElementById("imgFilter");
        assertThat(filterButton.isDisplayed()).as("filter button should be displayed").isTrue();
        log.info("filter button is displayed");
    }
}

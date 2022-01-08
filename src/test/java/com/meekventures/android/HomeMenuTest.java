package com.meekventures.android;

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
public class HomeMenuTest extends BaseAndroidTest {

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

        WebElement menuStartups = driver.findElement(By.id("startupConstraint"));
        assertThat(menuStartups.isDisplayed()).as("Login is not successful").isTrue();
        menuStartups.click();
        log.info("startup menu is displayed and clicked");

        driver.findElement(By.id("topUsersLay")).isDisplayed();
        log.info("startup menu is displayed");

        driver.findElement(By.id("imgBack")).click();

        val menuMentors = driver.findElement(By.id("mentorConstraint"));
        assertThat(menuStartups.isDisplayed()).isTrue();
        menuMentors.click();
        log.info("mentor menu is displayed and clicked");

        driver.findElement(By.id("topUsersLay")).isDisplayed();
        driver.findElement(By.id("imgBack")).click();

        val menuInvestors = driver.findElement(By.id("investorConstraint"));
        menuInvestors.click();
        driver.findElement(By.id("topUsersLay")).isDisplayed();
        driver.findElement(By.id("imgBack")).click();
        log.info("investor menu is displayed and clicked");

        val menuEvents = driver.findElement(By.id("events"));
        assertThat(menuEvents.isDisplayed()).isTrue();
        menuEvents.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("addNewEvent"))
        ));

        driver.findElement(By.id("relMessage")).isDisplayed();
        driver.findElement(By.id("imgBack")).click();

        val menuSocieties = driver.findElement(By.id("societiesConstraint"));
        menuSocieties.click();
        driver.findElement(By.id("imgAddSociety")).isDisplayed();
        driver.findElement(By.id("imgBack")).click();
    }
}

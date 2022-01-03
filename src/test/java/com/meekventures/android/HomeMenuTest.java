package com.meekventures.android;

import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

@Log
public class HomeMenuTest extends BaseAndroidTest {

    private static final String emailAddress = "rajmentor-KattieSchuppeGladyce.Lind99@gmail.com";
    private static final String password = "test123";

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
        WebElement email = driver.findElement(By.id("edtEmail"));
        assertThat(email.isDisplayed()).as("email input should be displayed").isTrue();
        email.sendKeys(emailAddress);
        log.info("email input is displayed");

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

package com.meekventures.android;

import com.meekventures.android.testutils.PropertyReader;
import io.appium.java_client.android.AndroidElement;
import lombok.extern.java.Log;
import lombok.val;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@Log
public class EventsTest extends BaseAndroidTest {

    WebDriverWait wait = new WebDriverWait(driver, 30);

    @BeforeEach()
    public void resetAppBefore() throws Exception {
        val propertyReaders = new PropertyReader();

        val emailAddress = propertyReaders.getProperty("mentor.email");
        val password = propertyReaders.getProperty("meek_test.password");

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
    }

    @AfterEach
    public void resetAppAfter() {
        driver.resetApp();
    }

    @Test
    public void register_event_test() {
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

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("edtName"))
        ));
        val ticketHolderName = driver.findElementById("edtName");
        assertThat(ticketHolderName.isDisplayed()).as("ticket holder name input should be displayed").isTrue();
        assertThat(ticketHolderName.getText()).as("ticket holder name input should be enabled").isNotNull();

        val ticketHolderEmail = driver.findElementById("edtEmail");
        assertThat(ticketHolderEmail.isDisplayed()).as("ticket holder email input should be displayed").isTrue();
        assertThat(ticketHolderEmail.getText()).as("ticket holder email input should be enabled").isNotNull();

        val ticketHolderCompany = driver.findElementById("edtCompany");
        assertThat(ticketHolderCompany.isDisplayed()).as("ticket holder company input should be displayed").isTrue();
        ticketHolderCompany.sendKeys("test company");

        val ticketHolderPhone = driver.findElementById("edtPhone");
        assertThat(ticketHolderPhone.isDisplayed()).as("ticket holder phone input should be displayed").isTrue();
        assertThat(ticketHolderPhone.getText()).as("ticket holder phone input should be enabled").isNotNull();

        val saveButton = driver.findElementById("btnAdd");
        assertThat(saveButton.isDisplayed()).as("save button should be displayed").isTrue();
        saveButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("relDone"))
        ));
        assertThat(driver.findElementById("relDone").isDisplayed()).as("done button should be displayed").isTrue();

        val nextButton = driver.findElementById("btnRegister");
        assertThat(nextButton.isEnabled()).as("next button should be enabled").isTrue();
        nextButton.click();
    }

    @Test
    @Disabled
    public void filter_free_events_register_test() throws InterruptedException {
        assertThat(driver.findElementById("imgFilter").isDisplayed()).as("filter option should be displayed").isTrue();
        driver.findElementById("imgFilter").click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("tveventTypeLabel"))
        ));

        driver.findElementById("conseventCost").click();
        assertThat(driver.findElementByXPath("//*[@text='Free Events']")
                .isDisplayed()).as("free events should be displayed").isTrue();
        driver.findElementByXPath("//*[@text='Free Events']").click();

        driver.findElementById("btnSave").click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("tvFilter"))
        ));
        assertThat(driver.findElementById("tvFilter").isDisplayed()).as("filter option should be displayed").isTrue();
        assertThat(driver.findElementById("tvFilter").getText()).as("filter option should be enabled").isEqualTo("Free Events");
        Thread.sleep(5000);

       val freePrices =  driver.findElementsById("tvPrice");
       assertThat(freePrices.size()).as("free events should be displayed").isGreaterThan(0);
       assertThat(((AndroidElement) freePrices.get(0)).getText()).as("free events should be enabled").contains("0.00");
       // select first event from list
        ((AndroidElement) freePrices.get(0)).click();

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

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("edtName"))
        ));
        val ticketHolderName = driver.findElementById("edtName");
        assertThat(ticketHolderName.isDisplayed()).as("ticket holder name input should be displayed").isTrue();
        assertThat(ticketHolderName.getText()).as("ticket holder name input should be enabled").isNotNull();

        val ticketHolderEmail = driver.findElementById("edtEmail");
        assertThat(ticketHolderEmail.isDisplayed()).as("ticket holder email input should be displayed").isTrue();
        assertThat(ticketHolderEmail.getText()).as("ticket holder email input should be enabled").isNotNull();

        val ticketHolderCompany = driver.findElementById("edtCompany");
        assertThat(ticketHolderCompany.isDisplayed()).as("ticket holder company input should be displayed").isTrue();
        ticketHolderCompany.sendKeys("test company");

        val ticketHolderPhone = driver.findElementById("edtPhone");
        assertThat(ticketHolderPhone.isDisplayed()).as("ticket holder phone input should be displayed").isTrue();
        assertThat(ticketHolderPhone.getText()).as("ticket holder phone input should be enabled").isNotNull();

        val saveButton = driver.findElementById("btnAdd");
        assertThat(saveButton.isDisplayed()).as("save button should be displayed").isTrue();
        saveButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("relDone"))
        ));
        assertThat(driver.findElementById("relDone").isDisplayed()).as("done button should be displayed").isTrue();

        val nextButton = driver.findElementById("btnRegister");
        assertThat(nextButton.isEnabled()).as("next button should be enabled").isTrue();
        nextButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("btnEvent"))
        ));
        assertThat(driver.findElementById("btnEvent").isDisplayed()).as("event button should be displayed").isTrue();
        assertThat(driver.findElementByXPath("//*[@text='Event Booked!']")
                .isDisplayed()).as("Events booked should be displayed").isTrue();

        assertThat(driver.findElementById("tvCount").isEnabled()).as("event button should be enabled").isTrue();
        assertThat(driver.findElementById("tvCount").getText()).as("event button should be enabled").isEqualTo("1");
    }

    @Test
    @Disabled
    public void filter_paid_events_register_test() throws InterruptedException {

        assertThat(driver.findElementById("imgFilter").isDisplayed()).as("filter option should be displayed").isTrue();
        driver.findElementById("imgFilter").click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("tveventTypeLabel"))
        ));

        driver.findElementById("conseventCost").click();
        assertThat(driver.findElementByXPath("//*[@text='Paid Events']")
                .isDisplayed()).as("Paid events should be displayed").isTrue();
        driver.findElementByXPath("//*[@text='Paid Events']").click();

        driver.findElementById("btnSave").click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("tvFilter"))
        ));
        assertThat(driver.findElementById("tvFilter").isDisplayed()).as("filter option should be displayed").isTrue();
        assertThat(driver.findElementById("tvFilter").getText()).as("filter option should be enabled").isEqualTo("Free Events");
        Thread.sleep(8000);

        val paidPricesEvents =  driver.findElementsById("tvPrice");
        assertThat(paidPricesEvents.size()).as("paid events should be displayed").isGreaterThan(0);
        assertThat(((AndroidElement) paidPricesEvents.get(0)).getText()).as("paid events should be enabled")
                .doesNotContain("0.00");
        // select first event from list
        ((AndroidElement) paidPricesEvents.get(0)).click();

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

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("edtName"))
        ));
        val ticketHolderName = driver.findElementById("edtName");
        assertThat(ticketHolderName.isDisplayed()).as("ticket holder name input should be displayed").isTrue();
        assertThat(ticketHolderName.getText()).as("ticket holder name input should be enabled").isNotNull();

        val ticketHolderEmail = driver.findElementById("edtEmail");
        assertThat(ticketHolderEmail.isDisplayed()).as("ticket holder email input should be displayed").isTrue();
        assertThat(ticketHolderEmail.getText()).as("ticket holder email input should be enabled").isNotNull();

        val ticketHolderCompany = driver.findElementById("edtCompany");
        assertThat(ticketHolderCompany.isDisplayed()).as("ticket holder company input should be displayed").isTrue();
        ticketHolderCompany.sendKeys("test company");

        val ticketHolderPhone = driver.findElementById("edtPhone");
        assertThat(ticketHolderPhone.isDisplayed()).as("ticket holder phone input should be displayed").isTrue();
        assertThat(ticketHolderPhone.getText()).as("ticket holder phone input should be enabled").isNotNull();

        val saveButton = driver.findElementById("btnAdd");
        assertThat(saveButton.isDisplayed()).as("save button should be displayed").isTrue();
        saveButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("relDone"))
        ));
        assertThat(driver.findElementById("relDone").isDisplayed()).as("done button should be displayed").isTrue();

        val nextButton = driver.findElementById("btnRegister");
        assertThat(nextButton.isEnabled()).as("next button should be enabled").isTrue();
        nextButton.click();

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.id("btnEvent"))
        ));
        assertThat(driver.findElementById("btnEvent").isDisplayed()).as("event button should be displayed").isTrue();
        assertThat(driver.findElementByXPath("//*[@text='Event Booked!']")
                .isDisplayed()).as("Events booked should be displayed").isTrue();

        assertThat(driver.findElementById("tvCount").isEnabled()).as("event button should be enabled").isTrue();
        assertThat(driver.findElementById("tvCount").getText()).as("event button should be enabled").isEqualTo("1");

    }
}

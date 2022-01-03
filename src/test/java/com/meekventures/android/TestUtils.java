package com.meekventures.android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils extends BaseAndroidTest {

    /**
     * This method is used to check if the element is displayed on the screen, scroll vertically if not displayed.
     * @param elementName String - Name of the element
     */
    public static void scrollToElement(final String elementName) {
        MobileElement element = null;

        try {
            element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList()" +
                            ".scrollIntoView(new UiSelector().resourceId(\""+elementName+"\"));"));
            if (element != null) {
                return;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        assert false;
        assertThat(element.isDisplayed()).as("Scroll to Signup button didn't work").isTrue();
    }

}

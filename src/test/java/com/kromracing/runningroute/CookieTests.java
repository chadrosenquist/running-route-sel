package com.kromracing.runningroute;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebElement;


/**
 * Performs that save and load values to and from local storage
 * or cookies.
 *
 */
public class CookieTests extends Common {
    @Test
    public void testFollowRoadNo() throws Exception {
        driver.get(RUNNING_ROUTE_URL);
        
        final WebElement checkBoxBefore = homePage.followRoadCheckbox();
        unCheckCheckBox(checkBoxBefore);
        driver.navigate().refresh();
        final WebElement checkBoxAfter = homePage.followRoadCheckbox();
        assertEquals(false, checkBoxAfter.isSelected());
    }
    
    @Test
    public void testFollowRoadYes() throws Exception {
        driver.get(RUNNING_ROUTE_URL);
        
        final WebElement checkBoxBefore = homePage.followRoadCheckbox();
        unCheckCheckBox(checkBoxBefore);
        checkBoxBefore.click();
        driver.navigate().refresh();
        final WebElement checkBoxAfter = homePage.followRoadCheckbox();
        assertEquals(true, checkBoxAfter.isSelected());
    }
    
    /**
     * Makes a checkbox unchecked
     * @param checkBox
     */
    private void unCheckCheckBox(final WebElement checkBox) {
        if (checkBox.isSelected())
            checkBox.click();
    }

}

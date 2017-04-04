package com.kromracing.runningroute;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Tests that modify the running route, such as loading from a URL, undo, and new route.
 * 
 */
public class ModifyTests extends Common {
    /**
     * Loads up a URL that contains straight lines and following the road.
     * @throws Exception
     */
    @Test
    public void testLoadUrl() throws Exception {
        driver.get(RUNNING_ROUTE_URL + ROUTE_PALATINE_TRAIL);
        
        waitForGoogleLong();
        assertDistanceEqualsMiles(1.40);        
    }
    
    /**
     * Loads a short route and repeatedly calls undo.
     * @throws Exception
     */
    @Test
    public void testUndo() throws Exception {
        driver.get(RUNNING_ROUTE_URL + ROUTE_TWIN_LAKES_SHORT);
                
        waitForPageLoad();
        final WebElement undoButton = homePage.undoButton();
        undoButton.click();
        waitForPageLoad();
        assertDistanceEqualsMiles(0.34);
        undoButton.click();
        assertDistanceEqualsMiles(0.0);
        
        // Undo the initial marker
        undoButton.click();
        assertDistanceEqualsMiles(0.0);
        
        // Nothing to undo.  Make sure it doesn't error out.
        undoButton.click();
        assertDistanceEqualsMiles(0.0);        
    }
    
    /**
     * Clicks "New Route".  Verifies popup and distance is set to 0.
     * @throws Exception
     */
    @Test
    public void testNewRoute() throws Exception {
        driver.get(RUNNING_ROUTE_URL + ROUTE_TWIN_LAKES_SHORT);
                
        // click new route
        waitForPageLoad();
        homePage.newRouteButton().click();

        // Verify alert with correct message.
        final Alert alert = driver.switchTo().alert();
        final String alertText = alert.getText();
        assertEquals("Start a new route?  The current route will be deleted.", alertText);
        alert.accept(); 
        
        // Verify distance is 0.
        assertDistanceEqualsMiles(0.0);        
    }
    
    /**
     * Clicks "New Route" and cancels.
     * @throws InterruptedException 
     */
    @Test
    public void testNewRouteCancel() throws Exception {
        driver.get(RUNNING_ROUTE_URL + ROUTE_PALATINE_TRAIL);
        waitForGoogleLong();
        
        homePage.newRouteButton().click();
        
        // Cancel alert
        final Alert alert = driver.switchTo().alert();
        alert.dismiss();
        
        // Verify the route hasn't changed.
        assertDistanceEqualsMiles(1.40);
    }
    
    /**
     * Clicks the "New Route" button when there is no route.
     * Verify no popup is displayed.
     * 
     * IE throws WebDriverException.  FF throws NullPointerException
     */
    @Test(expected=WebDriverException.class)
    public void testNewRouteNoRoute() {
        driver.get(RUNNING_ROUTE_URL);
        
        homePage.newRouteButton().click();
        try {
            driver.switchTo().alert().getText();
        }
        catch (final NullPointerException ex) {
            throw new WebDriverException();
        }
        
    }
}

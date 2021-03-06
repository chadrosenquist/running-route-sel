package com.kromracing.runningroute;


import org.junit.Test;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Tests undo and new.
 * 
 */
public class UndoNewTest extends Common {  
    /**
     * Loads a short route and repeatedly calls undo.
     * @throws Exception
     */
    @Test
    public void testUndo() throws Exception {
        driver.get(config.getRunningRouteUrl() + ROUTE_TWIN_LAKES_SHORT);
                
        waitForGoogleLong();
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
        driver.get(config.getRunningRouteUrl() + ROUTE_TWIN_LAKES_SHORT);
                
        // click new route
        waitForGoogleLong();
        homePage.newRouteButton().click();

        // Verify alert with correct message.
        alertVerifyTextAndAccept("Start a new route?  The current route will be deleted.");
        
        // Verify distance is 0.
        assertDistanceEqualsMiles(0.0);        
    }
    
    /**
     * Clicks "New Route" and cancels.
     * @throws InterruptedException 
     */
    @Test
    public void testNewRouteCancel() throws Exception {
        driver.get(config.getRunningRouteUrl() + ROUTE_PALATINE_TRAIL);
        waitForGoogleLong();
        
        homePage.newRouteButton().click();
        
        // Cancel alert
        alertVerifyTextAndDismiss("Start a new route?  The current route will be deleted.");
        
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
        driver.get(config.getRunningRouteUrl());
        
        homePage.newRouteButton().click();
        try {
            driver.switchTo().alert().getText();
        }
        catch (final NullPointerException ex) {
            throw new WebDriverException();
        }
        
    }
}

package com.kromracing.runningroute;

import org.junit.Test;
import org.openqa.selenium.WebElement;

/**
 * Tests the browsers navigation buttons, such as Back and Refresh.
 *
 */
public class NavigateTest extends Common {
    
    /**
     * Tests the browser's back button.
     * @throws Exception 
     */
    @Test
    public void testBack() throws Exception {
        driver.get(config.getRunningRouteUrl() + ROUTE_TWIN_LAKES_SHORT);
        
        waitForGoogleLong();
        final WebElement undoButton = homePage.undoButton();
        undoButton.click();
        
        driver.navigate().back();
        waitForPageLoad();
        assertDistanceEqualsMiles(0.38);
    }
    
    /**
     * Tests the browser's back button.
     * Goes to another website and comes back, making
     * sure the route isn't lost.
     * 
     * I absolutely hate websites that accidentally hitting the
     * back button causes you to lose your work!
     */
    @Test
    public void testBackAnotherSite() throws Exception {
        driver.get(config.getRunningRouteUrl() + ROUTE_TWIN_LAKES_SHORT);
        waitForGoogleLong();
        driver.navigate().to("http://www.google.com");
        driver.navigate().back();
        waitForGoogleLong();
        assertDistanceEqualsMiles(0.38);
    }
    
    /**
     * Verifies clicking the refresh button doesn't destroy the route.
     * @throws Exception
     */
    @Test
    public void testRefresh() throws Exception {
        driver.get(config.getRunningRouteUrl() + ROUTE_TWIN_LAKES_SHORT);
        waitForGoogleLong();
        driver.navigate().refresh();
        waitForPageLoad();
        assertDistanceEqualsMiles(0.38);
    }
}

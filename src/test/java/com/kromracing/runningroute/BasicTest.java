package com.kromracing.runningroute;

import org.junit.Test;

/**
 * Performs some very basic tests.  Such as verifying buttons are on the screen.
 * 
 */
public class BasicTest extends Common {
    /**
     * Verify the basic elements are on the webpage.
     * @throws Exception
     */
    @Test
    public void testBasicElements() throws Exception {
       driver.get(RUNNING_ROUTE_URL);
       
       // Verify location controls are on the screen.
       homePage.locationLabel();
       homePage.locationTextbox();
       homePage.searchButton();
       
       // Verify management controls are on the screen.       
       homePage.distanceLiteralLabel();
       homePage.distanceLabel();
       homePage.undoButton();
       homePage.newRouteButton();
       homePage.followRoadCheckbox();
  }
}

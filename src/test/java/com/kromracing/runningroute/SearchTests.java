package com.kromracing.runningroute;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.WebElement;


/**
 * Tests the search functionalities.
 * 
 */
public class SearchTests extends Common {
    /**
     * Searches for various cities and verifies the center in the URL is correct.
     * @throws Exception
     */
    @Test
    public void testSearch() throws Exception {
        // Set zoom to 16 for this test case.
        driver.get(RUNNING_ROUTE_URL + "#z=16");
        
        final WebElement locationTextbox = homePage.locationTextbox();
        
        // New York City
        locationTextbox.clear();
        locationTextbox.sendKeys("10001");
        homePage.searchButton().click();
        waitForPageLoad();
        assertEquals("c=40.753685,-73.999164&z=16", getRouteInUrl());
        
        // Menlo Park, CA
        locationTextbox.clear();
        locationTextbox.sendKeys("Menlo Park, CA");
        homePage.searchButton().click();
        waitForPageLoad();
        assertEquals("c=37.453827,-122.182187&z=16", getRouteInUrl());     
    }
    
    /**
     * Enters an invalid location and verifies there is a popup dialog.
     * @throws Exception
     */
    @Test
    public void testLocationNotFound() throws Exception {
        driver.get(RUNNING_ROUTE_URL + "#z=10");
        
        final WebElement locationTextbox = homePage.locationTextbox();
        
        locationTextbox.clear();
        locationTextbox.sendKeys("aflaweljlrj");
        homePage.searchButton().click();                  
        
        // Verify a popup indicating the location is not found.
        alertVerifyTextAndAccept("The location 'aflaweljlrj' was not found.");
   
    }
    
}

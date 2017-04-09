package com.kromracing.runningroute;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.WebElement;


/**
 * Tests the search functionalities.
 * 
 */
public class SearchTest extends Common {
    /**
     * Searches for NYC and verifies the center in the URL is correct.
     * @throws Exception
     */
    @Test
    public void testSearchNewYorkCity() throws Exception {
        // Set zoom to 16 for this test case.
        driver.get(RUNNING_ROUTE_URL + "#z=16");
        
        final WebElement locationTextbox = homePage.locationTextbox();
        
        // New York City
        waitForPageLoad();  // Wait for page to load.
        locationTextbox.clear();
        locationTextbox.sendKeys("10001");
        homePage.searchButton().click();
        waitForPageLoad();  // Wait for new page to load after click.
        assertEquals("c=40.753685,-73.999164&z=16", getRouteInUrl());
    }
    
    /**
     * Searches for Menlo Park, CA and verifies the center in the URL is correct.
     * @throws Exception
     */
    @Test
    public void testSearchMenloPark() throws Exception {
        // Set zoom to 16 for this test case.
        driver.get(RUNNING_ROUTE_URL + "#z=16");
        
        final WebElement locationTextbox = homePage.locationTextbox();
        
        // Menlo Park, CA
        locationTextbox.clear();
        waitForPageLoad();  // Wait for page to load.
        locationTextbox.sendKeys("Menlo Park, CA");
        homePage.searchButton().click();
        waitForPageLoad();  // Wait for new page to load after click.
        assertEquals("c=37.45296,-122.181725&z=16", getRouteInUrl());
    }
    
    
    /**
     * Enters an invalid location and verifies there is a popup dialog.
     * @throws Exception
     */
    @Test
    public void testLocationNotFound() throws Exception {
        driver.get(RUNNING_ROUTE_URL + "#z=10");
        
        final WebElement locationTextbox = homePage.locationTextbox();
        
        waitForPageLoad();  // Wait for page to load.
        locationTextbox.clear();
        locationTextbox.sendKeys("aflaweljlrj");
        homePage.searchButton().click();                  
        
        // Verify a popup indicating the location is not found.
        alertVerifyTextAndAccept("The location 'aflaweljlrj' was not found.");
   
    }
    
}

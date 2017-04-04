package com.kromracing.runningroute;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Tests the Running Route website from the browser level.
 * 
 *
 */
public class RunningRouteSelTest {
    private static final String RUNNING_ROUTE_URL = "http://127.0.0.1:8888/";
    private static final String ROUTE_PALATINE_TRAIL
        = "#c=42.121448,-88.016517&z=16&s=42.115841,-88.012043&v=s42.115921,-88.012333&v=s42.11629,-88.0122&v=f42.12076,-88.00957&v=f42.12428,-88.00966&v=f42.12478,-88.01458&v=f42.12483,-88.02229&v=s42.125729,-88.022268";
    private static final String ROUTE_TWIN_LAKES_SHORT
        = "#c=42.105176,-88.012553&z=18&s=42.10629,-88.01016&v=f42.10446,-88.01448&v=s42.104782,-88.015101";
    
    private WebDriver driver = null;
    
    private static enum Browser {
        IE,
        FF,
    }

    /**
     * Create the WebDriver
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        final Browser browser = Browser.FF;
        switch (browser) {
        case FF:
            driver = new FirefoxDriver();
            break;
        case IE:
            driver = new InternetExplorerDriver();
            break;
        }
    }
    
    /**
     * Close the browser window.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
    
    /**
     * Verify the basic elements are on the webpage.
     * @throws Exception
     */
    @Test
    public void testBasicElements() throws Exception {                  
       driver.get(RUNNING_ROUTE_URL);
       
       // Verify location controls are on the screen.
       driver.findElement(By.id("label-location"));
       driver.findElement(By.id("textbox-location"));
       driver.findElement(By.id("button-search"));
       
       // Verify management controls are on the screen.
       driver.findElement(By.id("label-distance-literal"));
       driver.findElement(By.id("label-distance"));
       driver.findElement(By.id("button-undo"));
       driver.findElement(By.id("button-new-route"));
       driver.findElement(By.id("checkbox-follow-road"));
       
       //assertNotNull(searchButton);
       
  
       ///searchButton.sendKeys("60005");
       //searchButton.submit();
       //Thread.sleep(3000);
       //driver.close();
       //driver.

    }
    
    /**
     * Searches for various cities and verifies the center in the URL is correct.
     * @throws Exception
     */
    @Test
    public void testSearch() throws Exception {
        // Set zoom to 16 for this test case.
        driver.get(RUNNING_ROUTE_URL + "#z=16");
        
        final WebElement searchTextbox = driver.findElement(By.id("textbox-location"));
        final WebElement searchButton = driver.findElement(By.id("button-search"));
        
        // New York City
        searchTextbox.clear();
        searchTextbox.sendKeys("10001");
        searchButton.click();
        waitForGoogle();
        assertEquals("c=40.748328,-73.996225&z=16", getRouteInUrl());
        
        // Menlo Park, CA
        searchTextbox.clear();
        searchTextbox.sendKeys("menlo park, ca");
        searchButton.click();
        waitForGoogle();
        assertEquals("c=37.453827,-122.182187&z=16", getRouteInUrl());     
    }
    
    /**
     * Loads up a URL that contains straight lines and following the road.
     * @throws Exception
     */
    @Test
    public void testLoadUrl() throws Exception {
        driver.get(RUNNING_ROUTE_URL + ROUTE_PALATINE_TRAIL);
        
        final WebElement distanceLabel = driver.findElement(By.id("label-distance"));
        waitForGoogle();
        assertEquals("1.40 miles", distanceLabel.getText());
    }
    
    /**
     * Loads a short route and repeatedly calls undo.
     * @throws Exception
     */
    @Test
    public void testUndo() throws Exception {
        driver.get(RUNNING_ROUTE_URL + ROUTE_TWIN_LAKES_SHORT);
        
        final WebElement distanceLabel = driver.findElement(By.id("label-distance"));
        final WebElement undoButton = driver.findElement(By.id("button-undo"));
        waitForGoogle();
        undoButton.click();
        waitForGoogle();
        assertEquals( "0.34 miles", distanceLabel.getText());
        undoButton.click();
        assertEquals("0.00 miles", distanceLabel.getText());
        
        // Undo the initial marker
        undoButton.click();
        assertEquals("0.00 miles", distanceLabel.getText());
        
        // Nothing to undo.  Make sure it doesn't error out.
        undoButton.click();
        assertEquals("0.00 miles", distanceLabel.getText());
        
    }
    
    /**
     * Call while waiting for Google to come back.
     * Either after a search, or while waiting for follow the road.
     * Unfortunately, this website doesn't have any loading indicators
     * when making a Google call.  For now, just sleep.
     * @throws InterruptedException 
     */
    private void waitForGoogle() throws InterruptedException {
        Thread.sleep(1000);        
    }
    
    /**
     * Returns the route in the URL.
     * For example, if the URL is:
     *     http://127.0.0.1:8888/RunningRoute.html#c=40.750606,-73.993349&z=16
     * this function will return:
     *     c=40.750606,-73.993349&z=16
     * @return
     */
    private String getRouteInUrl() {
        final String url = driver.getCurrentUrl();
        final String[] urlParts = url.split("#");
        if (urlParts.length >= 2)
            return urlParts[1];
        else
            return null;
    }

}

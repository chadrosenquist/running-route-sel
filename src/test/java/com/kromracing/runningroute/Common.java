package com.kromracing.runningroute;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Provides functionality to test RunningRoute that is common to all test cases.
 *
 */
public class Common {
    protected static final String ROUTE_PALATINE_TRAIL
        = "#c=42.121448,-88.016517&z=16&s=42.115841,-88.012043&v=s42.115921,-88.012333&v=s42.11629,-88.0122&v=f42.12076,-88.00957&v=f42.12428,-88.00966&v=f42.12478,-88.01458&v=f42.12483,-88.02229&v=s42.125729,-88.022268";
    protected static final String ROUTE_TWIN_LAKES_SHORT
        = "#c=42.105176,-88.012553&z=18&s=42.10629,-88.01016&v=f42.10446,-88.01448&v=s42.104782,-88.015101";
    
    private static final int POPUP_TIME_OUT = 10;   // Wait for a popup for 10 seconds.
    
    protected WebDriver driver = null;
    protected HomePage homePage = null;
    
    protected Config config = null;
    
    /**
     * Create the WebDriver
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // Read in the config for this Selenium run.
        config = Config.ConfigBuilder.buildDefault();
        
        switch (config.getBrowser()) {
        case FF:
            System.setProperty("webdriver.gecko.driver", config.getFirefoxDriverLocation());
            driver = new FirefoxDriver();
            break;
        case IE:
            System.setProperty("webdriver.ie.driver", config.getIEDriverLocation());
            driver = new InternetExplorerDriver();
            break;
        case HTMLUnit:
            driver = new HtmlUnitDriver();
            break;
        default:
            throw new IllegalArgumentException("Invalid Browser enum!");
        }        
        
        homePage = new HomePage(driver);        
    }
    
    /**
     * Close the browser window.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            //driver.close();
            driver.quit();
            driver = null;
        }
    }
    
    /**
     * Call while waiting for Google to come back.
     * Either after a search, or while waiting for follow the road.
     * Unfortunately, this website doesn't have any loading indicators
     * when making a Google call.  For now, just sleep.
     * @throws InterruptedException 
     */
    protected void waitForPageLoad() throws InterruptedException {
        //Thread.sleep(1000);
        Thread.sleep(1500);
    }
    
    protected void waitForGoogleLong() throws InterruptedException {
        //Thread.sleep(2000);
        Thread.sleep(3000);
    }
    
    protected void waitForGoogleReallyLong() throws InterruptedException {
        //Thread.sleep(5000);
        Thread.sleep(15000);
    }
    
    /**
     * Returns the route in the URL.
     * For example, if the URL is:
     *     http://127.0.0.1:8888/RunningRoute.html#c=40.750606,-73.993349&z=16
     * this function will return:
     *     c=40.750606,-73.993349&z=16
     * @return
     */
    protected String getRouteInUrl() {
        final String url = driver.getCurrentUrl();
        final String[] urlParts = url.split("#");
        if (urlParts.length >= 2)
            return urlParts[1];
        else
            return null;
    }  
        
    /**
     * Wait for an alert to popup.
     */
    private void waitForAlert() {
        // Wait for the alert to popup.
        final ExpectedCondition<Boolean> expected = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver d) {
                try {                    
                    final Alert alert = d.switchTo().alert();
                    alert.getText();
                }
                catch (final WebDriverException ex) {
                    return false;
                }               
                return true;
            }
        };
        
        final WebDriverWait wait = new WebDriverWait(driver, POPUP_TIME_OUT);
        wait.until(expected);
    } 
    
    /**
     * Verifies the distance in miles is correct.  If the page is set to kilometers,
     * this method converts.
     * @param distance Expected distance, in miles.
     */
    protected void assertDistanceEqualsMiles(final double expectedDistanceInMiles) {
        final String[] distance = homePage.distanceLabel().getText().split(" ");
        final double actualDistanceInMiles = Double.parseDouble(distance[0]);
        assertEquals(expectedDistanceInMiles, actualDistanceInMiles, 0.01);
    }
    
    /**
     * Verifies an alert is displayed with the text.  And then clicks accept.
     * @param expectedText text the alert is expected to contain
     */
    protected void alertVerifyTextAndAccept(final String expectedText) {
        waitForAlert();
        
        final Alert alert = driver.switchTo().alert();
        final String actualText = alert.getText();
        assertEquals(expectedText, actualText);
        alert.accept();
    }
    
    /**
     * Verifies an alert is displayed with the text.  And then clicks dismiss.
     * @param expectedText text the alert is expected to contain
     */
    protected void alertVerifyTextAndDismiss(final String expectedText) {
        waitForAlert();
        
        final Alert alert = driver.switchTo().alert();
        final String actualText = alert.getText();
        assertEquals(expectedText, actualText);
        alert.dismiss();
    }
}

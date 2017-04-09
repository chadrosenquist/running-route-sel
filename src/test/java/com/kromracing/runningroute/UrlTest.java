package com.kromracing.runningroute;

import org.junit.Test;

/**
 * Tests loading URLs and error conditions with loading URLs.
 * 
 */
public class UrlTest extends Common {
    private static final String TOO_MANY_QUERIES_ROUTE = "#c=42.040624,-87.859554&z=13&s=42.08678,-87.88673&v=f42.07348,-87.8836"
        + "&v=f42.06274,-87.8732&v=f42.05233,-87.87008&v=f42.04501,-87.8742&v=f42.04277,-87.87755&v=f42.04008,-87.87823"
        + "&v=f42.03836,-87.88239&v=f42.03697,-87.88&v=f42.03419,-87.87491&v=f42.03228,-87.87473&v=f42.02899,-87.87199"
        + "&v=f42.02484,-87.86913&v=f42.01832,-87.86696&v=f42.01755,-87.86138";
    
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
     * Verifies error message is given if URL parameter has no equal sign.
     * Without the equal sign, the parameter cannot be split into a name/value pair.
     * @throws Exception
     */
    @Test
    public void testUrlParamNoEqual() throws Exception {
        driver.get(RUNNING_ROUTE_URL + "#noequalssign");        
        alertVerifyTextAndAccept("Failed to load route from URL: noequalssign in the URL is invalid.");
    }
    
    /**
     * Verifies error message if the zoom is not an integer.
     * @throws Exception
     */
    @Test
    public void testUrlInvalidZoom() throws Exception {
        driver.get(RUNNING_ROUTE_URL + "#z=abc");
        alertVerifyTextAndAccept("Failed to load route from URL: z in z=abc is not a valid zoom level.");
    }
    
    /**
     * Verifies an error message if the vertex is not a straight line (s) or follow road (f).
     * @throws Exception
     */
    @Test
    public void testUrlInvalidVertex() throws Exception {
        driver.get(RUNNING_ROUTE_URL + "#v=a42.11629,-88.0122");
        alertVerifyTextAndAccept("Failed to load route from URL: a in v=a42.11629,-88.0122 in the URL is invalid.");
    }
    
    /**
     * Verifies an error message if a parameter has an invalid name.
     * @throws Exception
     */
    @Test
    public void testUrlInvalidName() throws Exception {
        driver.get(RUNNING_ROUTE_URL + "#garbage=bad");
        alertVerifyTextAndAccept("Failed to load route from URL: garbage in garbage=bad in the URL is invalid.");
    }
    
    /**
     * If the URL is really long, sometimes the error message "Too many queries" is displayed.
     * 
     * As of April 2017, this bug is still not fixed!  Part of the running route disappears, resulting
     * in the route length of only 6.30, not 6.79!
     */
    //@Test
    public void testTooManyQueriesBug() throws Exception {
        driver.get(RUNNING_ROUTE_URL + TOO_MANY_QUERIES_ROUTE);
        
        //waitForGoogleLong();
        waitForGoogleReallyLong();
        assertDistanceEqualsMiles(6.79);
    }
    
}

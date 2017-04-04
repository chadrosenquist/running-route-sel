package com.kromracing.runningroute;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class represents the elements found on the home page.
 *
 */
final public class HomePage {
    private final WebDriver driver;
    
    public HomePage(WebDriver driver) {  
        this.driver = driver;
    }
    
    // Location Controls
    
    public WebElement locationLabel() {
        return driver.findElement(By.id("label-location"));
    }
    
    public WebElement locationTextbox() {
        return driver.findElement(By.id("textbox-location"));
    }  
    
    public WebElement searchButton() {
        return driver.findElement(By.id("button-search"));
    }    
        
    // Management controls
    
    public WebElement distanceLiteralLabel() {
        return driver.findElement(By.id("label-distance-literal"));
    }
    
    public WebElement distanceLabel() {
        return driver.findElement(By.id("label-distance"));
    }
    
    public WebElement undoButton() {
        return driver.findElement(By.id("button-undo"));
    }

    public WebElement newRouteButton() {        
        return driver.findElement(By.id("button-new-route"));
    }    
    
    public WebElement followRoadCheckbox() {
        return driver.findElement(By.id("checkbox-follow-road"));
    }
 
    

}

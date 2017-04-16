# running-route-sel
Selenium tests for [Running Route](https://github.com/chadrosenquist/running-route).

# WebDriver Installation
You must install WebDrivers for the browsers.

### Mozilla GeckoDriver
http://toolsqa.com/selenium-webdriver/how-to-use-geckodriver/

### Internet Explorer Driver
https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver

# Configuration
Ideally, the configuration for a run is read from a file.  Currently, it's stored in code.  Edit the following file:

`src/test/java/com/kromracing/runningroute/Config.java`

Set the following values:

```
private static final String DEFAULT_RUNNING_ROUTE_URL = "file:///D:/Git/running-route/target/running-route-1.4-SNAPSHOT/RunningRoute.html";
private static final Browser DEFAULT_BROWSER = Browser.FF;
private static final String DEFAULT_FIREFOX_DRIVER_LOCATION = "D:\\\\eclipse\\webdrivers\\geckodriver.exe";
private static final String DEFAULT_IE_DRIVER_LOCATION = "D:\\\\eclipse\\webdrivers\\IEDriverServer.exe";
```

# Run
`mvn test`


# Design
The Selenium test files are located at:

`src/test/java/com/kromracing/runningroute`

* Common.java - Provides functionality to test RunningRoute that is common to all test cases.
* HomePage.java - Tests use this class to access elements found on the home page.
* BasicTest.java - Verifies all the elements are on the home page.
* UrlTest.java - Tests loading the route from a URL.
* SearchTest.java - Tests the search functionality.
* UndoNewTest.java - Tests the "undo" and "new" buttons.
* NavigateTests.java - Tests browser navigation buttons - refresh and back.
* CookieTest.java - Tests cookies are correctly saved.
 

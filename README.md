# running-route-sel
Selenium tests for Running Route.

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

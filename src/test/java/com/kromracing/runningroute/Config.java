package com.kromracing.runningroute;

/**
 * Holds the configuration for a Selenium run.
 * 
 * @author Chad Rosenquist
 *
 */
public class Config {
    // Default configuration.
    private static final String DEFAULT_RUNNING_ROUTE_URL = "file:///D:/Git/running-route/target/running-route-1.4-SNAPSHOT/RunningRoute.html";
    //protected static final String DEFAULT_RUNNING_ROUTE_URL = "http://localhost:8888/RunningRoute.html";
    //protected static final String DEFAULT_RUNNING_ROUTE_URL = "http://kromracing.com/RunningRoute.html";
    private static final Browser DEFAULT_BROWSER = Browser.FF;
    private static final String DEFAULT_FIREFOX_DRIVER_LOCATION = "D:\\\\eclipse\\webdrivers\\geckodriver.exe";
    private static final String DEFAULT_IE_DRIVER_LOCATION = "D:\\\\eclipse\\webdrivers\\IEDriverServer.exe";
    
    private final String runningRouteUrl;
    private final Browser browser;
    private final String firefoxDriverLocation;
    private final String IEDriverLocation;
    
    public static enum Browser {
        IE,
        FF,
        HTMLUnit,
    }
    
    public Config(
            final String runningRouteUrl,
            final Browser browser,
            final String firefoxDriverLocation,
            final String IEDriverLocation) {
        this.runningRouteUrl = runningRouteUrl;
        this.browser = browser;
        this.firefoxDriverLocation = firefoxDriverLocation;
        this.IEDriverLocation = IEDriverLocation;
    }
    
    public String getRunningRouteUrl() {
        return this.runningRouteUrl;
    }
    
    public Browser getBrowser() {
        return this.browser;
    }
    
    public String getFirefoxDriverLocation() {
        return this.firefoxDriverLocation;
    }
    
    public String getIEDriverLocation() {
        return this.IEDriverLocation;
    }
    
    
    /**
     * Use to build a Config object.
     * 
     * @author Chad
     *
     */
    public static class ConfigBuilder {
        private String runningRouteUrl;
        private Browser browser;
        private String firefoxDriverLocation;
        private String IEDriverLocation;
        
        public ConfigBuilder() {
        }
        
        public ConfigBuilder setRunningRouteUrl(final String runningRouteUrl) {
            this.runningRouteUrl = runningRouteUrl;
            return this;
        }
        
        public ConfigBuilder setBrowser(final Browser browser) {
            this.browser = browser;
            return this;
        }
        
        public ConfigBuilder setFireFoxDriverLocation(final String firefoxDriverLocation) {
            this.firefoxDriverLocation = firefoxDriverLocation;
            return this;
        }
        
        public ConfigBuilder setIEDriverLocation(final String IEDriverLocation) {
            this.IEDriverLocation = IEDriverLocation;
            return this;
        }
        
        /**
         *  Builds the config object and returns it.
         * @return Config
         */
        public Config build() {
            return new Config(runningRouteUrl, browser, firefoxDriverLocation, IEDriverLocation);
        }
        
        /**
         * Returns a config with the default settings.
         * @return Config
         */
        public static Config buildDefault() {
            return new Config(
                    DEFAULT_RUNNING_ROUTE_URL,
                    DEFAULT_BROWSER,
                    DEFAULT_FIREFOX_DRIVER_LOCATION,
                    DEFAULT_IE_DRIVER_LOCATION);
        }
    }
}

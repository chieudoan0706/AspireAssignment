package com.test.driverFactories;

public class DriverManagerFactory {

    /**
     * Get driver manafer
     *
     * @param type
     * @return DriverManager
     */
    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }

}

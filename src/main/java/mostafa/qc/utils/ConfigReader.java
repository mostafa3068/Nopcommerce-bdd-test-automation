package mostafa.qc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ============================================================================
 * ConfigReader Utility Class
 * ============================================================================
 * Description: Reads and provides access to configuration values from
 *              config.properties file. Implements singleton pattern to
 *              load the file only once during the test run.
 * Author: Mostafa QC
 * ============================================================================
 */
public class ConfigReader {

    // ========================================================================
    // INSTANCE VARIABLES
    // ========================================================================

    /** Holds all key-value pairs from config.properties */
    private static Properties properties;

    /** Path to the configuration file */
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    // ========================================================================
    // STATIC INITIALIZER - Loads properties file once at class load time
    // ========================================================================
    static {
        loadProperties();
    }

    // ========================================================================
    // CONSTRUCTOR - Private to prevent instantiation
    // ========================================================================
    private ConfigReader() {
        // Utility class - should not be instantiated
    }

    // ========================================================================
    // PRIVATE METHODS
    // ========================================================================

    /**
     * Loads the properties file into memory.
     * Called once via static initializer block.
     */
    private static void loadProperties() {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties = new Properties();
            properties.load(fis);
            System.out.println("Config file loaded successfully: " + CONFIG_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + CONFIG_FILE_PATH, e);
        }
    }

    // ========================================================================
    // PUBLIC METHODS
    // ========================================================================

    /**
     * Retrieves a value from config.properties by key.
     *
     * @param key The property key to look up
     * @return The value associated with the key
     * @throws RuntimeException if the key is not found
     */
    public static String get(String key) {
        String override = System.getProperty(key);

        if (override != null && !override.isEmpty()) {
            return override.trim();
        }

        String value = properties.getProperty(key);

        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Property not found in config file: " + key);
        }

        return value.trim();
    }

    /**
     * Retrieves the base URL from config.properties.
     * Convenience method used frequently across the framework.
     *
     * @return Base URL of the application under test
     */
    public static String getBaseUrl() {
        return get("base.url");
    }

    /**
     * Retrieves the browser name from config.properties.
     * Defaults to "chrome" if not specified.
     *
     * @return Browser name (chrome, firefox, edge)
     */
    public static String getBrowser() {
        try {
            return get("browser");
        } catch (RuntimeException e) {
            // Default to chrome if not specified
            return "chrome";
        }
    }
}
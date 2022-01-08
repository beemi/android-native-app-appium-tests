package com.meekventures.android.testutils;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Properties;
import java.util.logging.Level;

@Slf4j
public class PropertyReader {

    private final Properties properties = new Properties();
    private static final String PROP_FILE_ROOT = "src/test/resources/";
    private static final String PROP_FILE_SUFFIX = ".environment.properties";
    private final String propFilePath;

    /**
     * Default constructor
     * Initialises the target environment variable.
     */
    public PropertyReader() throws Exception {

        final String environmentKey = "TEST_ENV";
        String environment = System.getenv(environmentKey);
        if (environment == null || environment.isEmpty()) {
            log.warn("Environment variable TEST_ENV didn't set, so default set to Stage");
            environment = "stage";
        }

        this.propFilePath = PROP_FILE_ROOT + environment.toLowerCase().trim() + PROP_FILE_SUFFIX;
        if (isPropFileThere(this.propFilePath)) {
            loadPropFile();
        } else {
            throw new Exception("Property file doesn't exist in path " + this.propFilePath);
        }
    }

    /**
     * Check if file exists in given path.
     *
     * @param path file path
     * @return boolean
     */
    private boolean isPropFileThere(final String path) {

        return new File(path).exists();
    }

    /**
     * Load property file.
     */
    private void loadPropFile() {

        try (FileReader fileReader = new FileReader(this.propFilePath)) {
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            log.debug("****** Unable to find property file %s %s", propFilePath, e);
        } catch (IOException e) {
            log.debug("****** Unable to read Property file %s %s", propFilePath, e);
        }
    }

    /**
     * Get property by key.
     *
     * @param propKey String
     * @return propValue String
     * @throws InvalidKeyException - Throws Invalid key exception.
     */
    public String getProperty(final String propKey) throws Exception {

        final String key;
        final String value;

        if (propKey == null) {
            throw new Exception("Null argument: property key");
        } else if (propKey.isEmpty()) {
            throw new Exception("Empty argument: property key");
        } else {
            key = propKey;
        }

        value = properties.getProperty(key);
        if (value == null) {
            throw new InvalidKeyException("The property file doesn't contain property with key " + propKey);
        } else if (value.isEmpty()) {
            throw new Exception("The value for property key " + propKey + " is empty");
        } else {
            return value;
        }
    }
}

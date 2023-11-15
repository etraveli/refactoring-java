package config;

import exception.RentalException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RentalConfigIntegrationTest {
    private static final String VALID_CONFIG_FILE = "/config.properties";
    private static final String INVALID_CONFIG_FILE = "/nonexistent_config.properties";
    private static final String KEY_NOT_EXIST = "NOTExist";

    @BeforeEach
    void setUp() {
        setPropertiesToNull();
    }

    //Since Properties is static, we need to call this method before and after Each test to make sure
    //that non of them has side effects on others.
    private void setPropertiesToNull() {
    if(RentalConfig.getProperties()!=null)
        RentalConfig.setProperties(null);
    }

    @AfterEach
    void tearDown() {
        setPropertiesToNull();
    }


    @Test
    void given_SetupConfig_When_ConfigFileIsValid_Then_PropertyIsLoadedCorrectly()
    {
        RentalConfig.setupConfigByConfigFileName(VALID_CONFIG_FILE);

        assertEquals("2", RentalConfig.getPropertyByKey(PropertyKeyStatement.NEW_RELEASE_RENTAL_DAY_BONUS));
    }

    @Test
    void given_SetupConfig_When_ConfigFileIsNotValid_Then_ERROR2AndPropertiesNotLoaded() {
        assertThrows(RentalException.class, () ->
                RentalConfig.setupConfigByConfigFileName(INVALID_CONFIG_FILE),
                "ERR02:Error loading configuration from the file name "+INVALID_CONFIG_FILE);

        // Verify that properties are not loaded
        assertThrows(RentalException.class, () -> RentalConfig.getPropertyByKey(PropertyKeyStatement.NEW_RELEASE_RENTAL_DAY_BONUS));
    }
    @Test
    void given_getPropertyByKey_When_KeyNotFound_Then_ERROR04KeyNotFound() {
        RentalConfig.setupConfigByConfigFileName(VALID_CONFIG_FILE);

        assertThrows(RentalException.class, () -> RentalConfig.getPropertyByKey(KEY_NOT_EXIST),
                "ERR04:The property is not found! Property is "+KEY_NOT_EXIST);
    }

    @Test
    void given_getPropertyByKey_When_KeyNotFound_Then_ERROR03KeyNotFound() {

        assertThrows(RentalException.class, () -> RentalConfig.getPropertyByKey(KEY_NOT_EXIST),
                "ERR03:The configuration property is not setup correctly. Please run the program again!");
    }

    @Test
    void given_LoadPropertyFromStream_When_ConfigFileIsValid_Then_PropertyIsLoadedCorrectly() throws IOException {
        try (InputStream input = getClass().getResourceAsStream(VALID_CONFIG_FILE)) {
            RentalConfig.loadPropertyFromStream(input);
        }

        assertEquals("2", RentalConfig.getPropertyByKey(PropertyKeyStatement.NEW_RELEASE_RENTAL_DAY_BONUS));
    }

}



package config;

import exception.ExceptionCode;
import exception.RentalException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class RentalConfig {
    private static Properties properties;

    public static void setupConfigByConfigFileName(String configFileName) {
        //When setupConfigByConfigFileName is called that means the properties should be loaded.
        //However, if the properties was leaded before and there is a problem to update it, properties will not
        // be loaded with new value just has the last value.
        // So it is important to set it null to prevent from unwanted side effects.
        setProperties(null);
        try (InputStream input = RentalConfig.class.getResourceAsStream(configFileName)) {
            if (input != null) {
                loadPropertyFromStream(input);
            } else {
                throw new RentalException(ExceptionCode.ERR01, configFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RentalException(ExceptionCode.ERR02, configFileName);
        }

    }

    public static void loadPropertyFromStream(InputStream input) throws IOException {
        Properties props = new Properties();
        props.load(input);
        setProperties(props);
    }


    public static String getPropertyByKey(String key) {
        if (properties == null)
            throw new RentalException(ExceptionCode.ERR03);
        if (properties.getProperty(key) == null)
            throw new RentalException(ExceptionCode.ERR04, key);
        return properties.getProperty(key);
    }

    public static void setProperties(Properties props) {
        properties = props;
    }

    public static Properties getProperties() {
        return properties;
    }
}

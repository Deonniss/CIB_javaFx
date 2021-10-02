package logic.propertyHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandler {

    private static Properties property = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("resources/beginTitle/title.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperty() {
        return property;
    }
}

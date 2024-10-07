package configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropReader {

    private Properties properties;

    public  ConfigPropReader(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getUrl() {
        return properties.getProperty("url");
    }
}

package api.endpoints;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Routes {

    Properties prop = new Properties();

    public Routes() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getApiKey() {
        return prop.getProperty("API_KEY");
    }

    public String getBaseUrl() {
        return prop.getProperty("BASE_URL");
    }
}

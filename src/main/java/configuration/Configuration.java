package configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Configuration instance;
    private static final String PATH = "./setting/settings.txt";
    private int port;
    private String host;

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public Configuration() {
        try (FileReader fileReader = new FileReader(PATH)) {
            Properties lineReader = new Properties();
            lineReader.load(fileReader);
            port = Integer.parseInt(lineReader.getProperty("port"));
            host = lineReader.getProperty("host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }
}

package configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationTest {

    private final Configuration configuration = Configuration.getInstance();
    private int port;
    private String host;

    @BeforeEach
    void setConfiguration() {
        final String PATH = "./setting/settings.txt";
        try (FileReader fileReader = new FileReader(PATH)) {
            Properties lineReader = new Properties();
            lineReader.load(fileReader);
            port = Integer.parseInt(lineReader.getProperty("port"));
            host = lineReader.getProperty("host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getInstance() {
        Assertions.assertEquals(Configuration.getInstance(), configuration);
    }

    @Test
    void getPort() {
        Assertions.assertEquals(configuration.getPort(), port);
    }

    @Test
    void getHost() {
        Assertions.assertEquals(configuration.getHost(), host);

    }
}

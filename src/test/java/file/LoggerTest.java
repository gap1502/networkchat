package file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoggerTest {

    private final Logger file = Logger.getInstance();
    private static final String msg = "Test";

    @Test
    void getInstance() {
        assertEquals(Logger.getInstance(), file);
    }

    @Test
    void file() {
        assertTrue(file.log(msg));
    }
}

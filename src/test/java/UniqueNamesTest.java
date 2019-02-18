import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class UniqueNamesTest {
    @Test
    public void createsTwoFileWithUniqueRecords() throws IOException, InvalidFormatException {
        String inputFile = UniqueNamesTest.class.getResource("test-data.xlsx").getFile();
        new UniqueNames().createUniqueValues(inputFile);

        assertTrue(new File("./firstNames.txt").exists());
        assertTrue(new File("./lastNames.txt").exists());
    }
}

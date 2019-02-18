import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UniqueNames {

    public void createUniqueValues(String inputFile) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(inputFile));
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        Set<String> firstNames = new HashSet<>();
        Set<String> lastNames = new HashSet<>();
        // skip first row
        if ((rowIterator.hasNext())) rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String firstName = row.getCell(0).toString().trim();
            String lastName = row.getCell(1).toString().trim();
            firstNames.add(firstName);
            lastNames.add(lastName);
        }

        write(firstNames, "./firstNames.txt");
        write(lastNames, "./lastNames.txt");
    }

    public void write(Set<String> set, String fileName) throws IOException {
        File out = new File(fileName);
        FileWriter writer = new FileWriter(out);
        set.forEach(name -> {
            try {
                if (!"".equals(name) && !name.contains("(") && !name.contains("jll.com") && !name.contains("JLL.COM")) {
                    writer.append("\"" + name + "\",\"" + name + "\"");
                    writer.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }
}

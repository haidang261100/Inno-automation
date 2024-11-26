package helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcellHelper {
    public static Map<String, String> readExcelColumnAsMap(String excelFilePath, String sheetName, int columnIndex) {
        Map<String, String> keyValueMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum();

            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(columnIndex);
                if (cell != null) {
                    String cellValue = cell.toString();
                    // Phân tách key và value dựa trên dấu phân tách (ví dụ: dấu hai chấm)
                    String[] keyValue = cellValue.split(":");
                    if (keyValue.length == 2) {
                        keyValueMap.put(keyValue[0].trim(), keyValue[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyValueMap;
    }
}

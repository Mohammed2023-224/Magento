package engine.dataDriven;


import engine.logger.CustomLogger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ReadExcel {

    FileInputStream is;
    XSSFWorkbook workbook;
    XSSFCell cell;
    XSSFRow row;
    XSSFSheet sheet;

    public ReadExcel() {
    }

    public ReadExcel(String filePath, String sheetName) {
        readFile(filePath, sheetName);
    }

    public void readFile(String filePath, String sheetName) {
        try {
            is = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            CustomLogger.logger.fatal("Couldn't find the file at: " + filePath);
        }
        sheet = switchToSheet(sheetName);
    }


    public String readData(String filePath, String sheetName, String rowName, String columnName) {
        try {
            is = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            CustomLogger.logger.fatal("Couldn't find the file at: " + filePath);
        }
        sheet = switchToSheet(sheetName);
        return getCellData(rowName, columnName);
    }


//    public Object[][] readDataAsHAshMapByRowName(String filePath, String sheetName, String columnName, String rowName) {
//        //initializing variables
//        Object[][] mainData = null;
//        int numberOfTestCases = 0;
//        LinkedHashMap<String, String> data;
//        readFile(filePath, sheetName);
//        row = sheet.getRow(0);
//        int columnNumber = getColumnNumberFromColumnName(columnName);
//        for (int j = 0; j < getNumberOfRows(); j++) {
//            row = sheet.getRow(j);
//            String condition = getCellData(row.getCell(columnNumber));
//            if (condition.equalsIgnoreCase(rowName)) {
//                numberOfTestCases++;
//            }
//        }
//        int dataIndex = 0;
//        mainData = new Object[numberOfTestCases][1];
//        for (int j = 0; j < getNumberOfRows(); j++) {
//            data = new LinkedHashMap<>();
//            row = sheet.getRow(j);
//            String condition = getCellData(row.getCell(columnNumber));
//            if (condition.equalsIgnoreCase(rowName)) {
//                for (int k = 0; k < getNumberOfColumns(); k++) {
//                    String colValue = getCellData(sheet.getRow(0).getCell(k));
//                    row = sheet.getRow(j);
//                    data.put(colValue, getCellData(row.getCell(k)));
//                }
//                mainData[dataIndex][0] = data;
//                dataIndex++;
//            }
//        }
//        return mainData;
//    }

    public String getCellData(XSSFCell cell) {
        if (cell == null) {
            return "";
        } else {
            if (cell.getCellType() == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                double numericValue = cell.getNumericCellValue();
                int intValue = (int) numericValue;
                return String.valueOf(intValue);
            } else if (cell.getCellType() == CellType.FORMULA) {
                return String.valueOf(cell.getCellFormula());
            } else if (cell.getCellType() == CellType._NONE || cell.getCellType() == CellType.BLANK) {
                return "";
            } else {
                return "";
            }
        }
    }


    public String getCellData(int rowNum, int columnNumber) {
        return getCellNameFromRowNumberAndColumnNumber(rowNum, columnNumber);
    }

    public String getCellData(String rowName, int columnNumber) {
        int rowNumber = getRowNumberFromRowName(rowName);
        return getCellNameFromRowNumberAndColumnNumber(rowNumber, columnNumber);
    }

    public String getCellData(int rowNum, String columnName) {
        int columnNumber = getColumnNumberFromColumnName(columnName);
        return getCellNameFromRowNumberAndColumnNumber(rowNum, columnNumber);
    }

    public String getCellData(String rowName, String columnName) {
        int columnNumber = getColumnNumberFromColumnName(columnName);
        int rowNum = getRowNumberFromRowName(rowName);
        return getCellNameFromRowNumberAndColumnNumber(rowNum, columnNumber);
    }


    private int getNumberOfColumns() {
        if (row == null) {
            return 0;
        }
        return row.getLastCellNum();
    }

    private int getNumberOfRows() {
        if (sheet == null) {
            return 0;
        }
        return sheet.getPhysicalNumberOfRows();
    }


    private String getCellNameFromRowNumberAndColumnNumber(int rowNum, int columnNumber) {
        if (sheet == null) {
            return "";
        }
        if (rowNum < 0 || rowNum >= getNumberOfRows()) {
            return "";
        }
        row = sheet.getRow(rowNum);
        if (row == null) {
            return ""; // return empty string if row is null
        }
        return getCellData(row.getCell(columnNumber));
    }

    private int getRowNumberFromRowName(String rowName) {
        if (sheet == null) {
            return -1;
        }
        for (int i = 0; i < getNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null || row.getCell(0) == null) {
                continue; // skip null rows and cells
            }
            if (getCellData(row.getCell(0)).equalsIgnoreCase(rowName)) {
                return i;
            }
        }
        return -1;
    }

    private int getColumnNumberFromColumnName(String columnName) {
        if (sheet == null) {
            return -1;
        }
        row = sheet.getRow(0);
        if (row == null) {
            return -1; // return -1 if the first row is null
        }
        for (int i = 0; i < getNumberOfColumns(); i++) {
            if (getCellData(row.getCell(i)).equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    private XSSFSheet switchToSheet(String sheetName) {
        try {
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            CustomLogger.logger.fatal("Couldn't find sheet: " + sheetName);
        }
        return sheet;
    }

//    private int getColumnNumberFromColumnNameAndRowNum(String columnName, int rowNum) {
//        if (sheet == null) {
//            return -1;
//        }
//        row = sheet.getRow(rowNum);
//        if (row == null) {
//            return -1; // return -1 if the first row is null
//        }
//        for (int i = 0; i < getNumberOfColumns(); i++) {
//            if (getCellData().equalsIgnoreCase(columnName)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    private int getRowNumberFromRowNameAndColumnNum(String rowName, int columnNumber) {
//        if (sheet == null) {
//            return -1;
//        }
//        for (int i = 0; i < getNumberOfRows(); i++) {
//            row = sheet.getRow(i);
//            if (row == null || row.getCell(0) == null) {
//                continue; // skip null rows and cells
//            }
//            if (getCellData().equalsIgnoreCase(rowName)) {
//                return i;
//            }
//        }
//        return -1;
//    }

    public Object[][] readDataHashMapByRowCondition(String fileName, String sheetName, String column, String rowCondtion) {
        //init variables
        LinkedHashMap<String, String> table;
        Object[][] data;
        int numberOfTests = 0;
        int tableIndex = 0;
        //read file
        readFile(fileName, sheetName);
        //get first row(headers)
        row = sheet.getRow(0);
        //iterate over rows in a column with the name column(parameter) and when the cell is equal to row condition add 1 to tests
        for (int i = 0; i < getNumberOfRows(); i++) {
            if (rowCondtion.equalsIgnoreCase(getCellData(i, column))) {
                numberOfTests++;
            }
        }
        if (numberOfTests == 0) {
            CustomLogger.logger.error("No data found at file located: " + fileName);
            Assert.fail("No data found that matches the row condition: " + rowCondtion);
        }
        CustomLogger.logger.info("number of test cases = " + numberOfTests);
        data = new Object[numberOfTests][1];
        //iterate over all rows
        for (int i = 0; i < getNumberOfRows(); i++) {
            table = new LinkedHashMap<>();
            //if row condition = cell in the row this will add that row to the linked hashmap with the correct headers
            if (row != null && rowCondtion.equalsIgnoreCase(getCellData(i, column))) {
                for (int k = 0; k < getNumberOfColumns(); k++) {
                    String key = getCellData(0, k).replaceAll(" ", "").toLowerCase();
                    String value = getCellData(i, k);
                    table.put(key, value);
                }
                data[tableIndex][0] = table;
                tableIndex++;
            }
        }
        return data;
    }
}

package com.flipkart.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsUtil {

    public String           XlsDataPath;
    public FileInputStream  fi;
    public FileOutputStream fo;
    public XSSFWorkbook     workbook;
    public XSSFSheet        sheet;
    public XSSFRow          row;
    public XSSFCell         cell;
    public CellStyle        style;

    public static String dataSheetPath = "./testData/TestData.xlsx";

    public XlsUtil(String XlsDataPath)
    {

        this.XlsDataPath = XlsDataPath;
    }

    public void openExcelFile(String xlsDataPath, String sheetName, int rowNum) {
        try (FileInputStream fi = new FileInputStream(xlsDataPath)) {
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        openExcelFile(dataSheetPath, sheetName, rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;
    }

    public int getRowCount(String sheetName) throws IOException {
        ;
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {

        openExcelFile(dataSheetPath, sheetName, rowNum);
        cell = row.getCell(colNum);
        DataFormatter dataFormatter = new DataFormatter();
        String data;
        try {
            data = dataFormatter.formatCellValue(cell);
        }
        catch (Exception e) {
            data = " ";
        }
        workbook.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        openExcelFile(dataSheetPath, sheetName, rowNum);
        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fo = new FileOutputStream(dataSheetPath);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

}




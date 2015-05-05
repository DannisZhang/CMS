package org.dannis.cms;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dannis.cms.model.PhoneNumber;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码Excel解析器
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-05-04 21:56
 */
public class MobilePhoneExcelParser {
    public static List<PhoneNumber> parseExcel(String filePath) throws IOException {
        List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
        InputStream inputStream = new FileInputStream(filePath);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        //Read the sheets of workbook
        for (int sheetIndex = 0;sheetIndex < workbook.getNumberOfSheets();sheetIndex++) {
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            if (sheet == null) {
                continue;
            }
            //Read the rows of sheet
            for (int rowIndex = 1;rowIndex <= sheet.getLastRowNum();rowIndex++) {
                HSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    PhoneNumber phoneNumber = new PhoneNumber();
                    phoneNumber.setNumber(getValue(row.getCell(0)));
                    phoneNumber.setType(getValue(row.getCell(1)));
                    phoneNumber.setOperator(getValue(row.getCell(2)));
                    phoneNumber.setAttribution(getValue(row.getCell(3)));
                    phoneNumber.setWholesalePrice(row.getCell(4).getNumericCellValue());
                    phoneNumber.setFloorPrice(row.getCell(5).getNumericCellValue());
                    phoneNumber.setDescription(row.getCell(6).getStringCellValue());

                    phoneNumbers.add(phoneNumber);
                }
            }
        }
        return phoneNumbers;
    }

    private static String getValue(HSSFCell cell) {
        if (HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    public static void main(String[] args) throws IOException {
        List<PhoneNumber> phoneNumbers = parseExcel("E:\\phoneNumber.xls");
        System.out.println(phoneNumbers);
    }
}

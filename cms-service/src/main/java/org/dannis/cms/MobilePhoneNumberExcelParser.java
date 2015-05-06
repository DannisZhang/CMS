package org.dannis.cms;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dannis.cms.model.MobilePhoneNumber;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 手机号码Excel解析器
 *
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-05-04 21:56
 */
public class MobilePhoneNumberExcelParser {
    public static List<MobilePhoneNumber> parseExcel(String filePath) throws IOException {
        List<MobilePhoneNumber> mobilePhoneNumbers = new ArrayList<MobilePhoneNumber>();
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
                    String mobilePhoneNumber = getCellData(row.getCell(0));
                    if (mobilePhoneNumber != null && !"".equals(mobilePhoneNumber.trim())) {
                        MobilePhoneNumber mobilePhone = new MobilePhoneNumber();
                        mobilePhone.setNumber(getCellData(row.getCell(0)));
                        mobilePhone.setOperator(getCellData(row.getCell(1)));
                        mobilePhone.setAttribution(getCellData(row.getCell(2)));
                        String wholesalePriceStr = getCellData(row.getCell(3));
                        if (wholesalePriceStr != null && !"".equals(wholesalePriceStr.trim())) {
                            mobilePhone.setWholesalePrice(Double.parseDouble(wholesalePriceStr));
                        } else {
                            mobilePhone.setWholesalePrice(0.00);
                        }
                        String floorPriceStr = getCellData(row.getCell(4));
                        if (floorPriceStr != null && !"".equals(floorPriceStr.trim())) {
                            mobilePhone.setFloorPrice(Double.parseDouble(floorPriceStr));
                        } else {
                            mobilePhone.setFloorPrice(0.00);
                        }
                        String balanceStr = getCellData(row.getCell(5));
                        if (balanceStr != null && !"".equals(balanceStr.trim())) {
                            mobilePhone.setBalance(Double.parseDouble(balanceStr));
                        } else {
                            mobilePhone.setBalance(0.00);
                        }
                        mobilePhone.setRemark(getCellData(row.getCell(6)));

                        mobilePhoneNumbers.add(mobilePhone);
                    }
                }
            }
        }
        return mobilePhoneNumbers;
    }

    public static List<MobilePhoneNumber> parseExcel(InputStream inputStream) throws IOException {
        List<MobilePhoneNumber> mobilePhoneNumbers = new ArrayList<MobilePhoneNumber>();
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
                    String mobilePhoneNumber = getCellData(row.getCell(0));
                    if (mobilePhoneNumber != null && !"".equals(mobilePhoneNumber.trim())) {
                        MobilePhoneNumber mobilePhone = new MobilePhoneNumber();
                        mobilePhone.setNumber(getCellData(row.getCell(0)));
                        mobilePhone.setOperator(getCellData(row.getCell(1)));
                        mobilePhone.setAttribution(getCellData(row.getCell(2)));
                        String wholesalePriceStr = getCellData(row.getCell(3));
                        if (wholesalePriceStr != null && !"".equals(wholesalePriceStr.trim())) {
                            mobilePhone.setWholesalePrice(Double.parseDouble(wholesalePriceStr));
                        } else {
                            mobilePhone.setWholesalePrice(0.00);
                        }
                        String floorPriceStr = getCellData(row.getCell(4));
                        if (floorPriceStr != null && !"".equals(floorPriceStr.trim())) {
                            mobilePhone.setFloorPrice(Double.parseDouble(floorPriceStr));
                        } else {
                            mobilePhone.setFloorPrice(0.00);
                        }
                        String balanceStr = getCellData(row.getCell(5));
                        if (balanceStr != null && !"".equals(balanceStr.trim())) {
                            mobilePhone.setBalance(Double.parseDouble(balanceStr));
                        } else {
                            mobilePhone.setBalance(0.00);
                        }
                        mobilePhone.setRemark(getCellData(row.getCell(6)));

                        mobilePhoneNumbers.add(mobilePhone);
                    }
                }
            }
        }
        return mobilePhoneNumbers;
    }

    /**
     * 获取单元格数据（String）
     *
     * @param cell 单元格
     * @return 单元格数据（String）
     */
    private static String getCellData(HSSFCell cell) {
        if (cell == null) {
            return null;
        }
        String data;
        if (HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
            data = String.valueOf(cell.getBooleanCellValue());
        } else if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
            data =  String.valueOf(cell.getNumericCellValue());
        } else {
            data = cell.getStringCellValue();
        }

        return data;
    }

    public static void main(String[] args) throws IOException {
        List<MobilePhoneNumber> mobilePhoneNumbers = parseExcel("E:\\模板-批量导入手机号码.xls");
        System.out.println(mobilePhoneNumbers);
    }
}

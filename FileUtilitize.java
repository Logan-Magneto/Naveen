package naveen.automate.utilitize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class FileUtilitize {
	
	
	public List<HashMap<String, Object>> readExcelFile(String filePath,String fileName,int sheetIndex) {
		
		File file = new File(System.getProperty("user.dir") + filePath+"\\"+fileName);
		
		FileInputStream fileInputStream;
		String filExtensionName;
		List<HashMap<String, Object>> returnList = new ArrayList<HashMap<String,Object>>();
		
		try {
			fileInputStream = new FileInputStream(file);
		
		
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		
		
		Row firstRow = sheet.getRow(0);
		
		List<String> heading = new ArrayList<String>();
	
		for(int i=0; i <= firstRow.getLastCellNum()-1; i++) {
			heading.add(firstRow.getCell(i).getStringCellValue());
		}
		
		for (int i = 1; i < rowCount+1; i++) {

	        Row row = sheet.getRow(i);

	        //Create a loop to print cell values in a row
	        
	        HashMap<String, Object> valuesList = new HashMap<String, Object>();

	        for (int j = 0; j < row.getLastCellNum(); j++) {

	            //Print Excel data in console
	        	Cell cell = row.getCell(j);
	        	
	        	CellType cellType = cell.getCellType();
	        
	        	switch (cellType) {
				case STRING:
					valuesList.put(heading.get(j), cell.getStringCellValue());
					break;
                case NUMERIC:
                	valuesList.put(heading.get(j), cell.getNumericCellValue());
					break;
                case BOOLEAN:
                	valuesList.put(heading.get(j), cell.getBooleanCellValue());
 					break;
                case FORMULA:
                	valuesList.put(heading.get(j), cell.getCellFormula());
					break;
                case ERROR:
                	valuesList.put(heading.get(j), cell.getErrorCellValue());
					break;
                case _NONE:
                	valuesList.put(heading.get(j), "");
					break;
                case BLANK:
                	valuesList.put(heading.get(j), "");
					break;
				default:
					System.out.println("No values are here");
					break;
				}

	            

	        }

	        returnList.add(valuesList);
	        System.out.println();
	    } 
		
		}catch (Exception e) {
		
			e.printStackTrace();
		}
		return returnList;
		
		
		
		
	}
	
	public void writeExcelFile(String filePath,String fileName,int sheetIndex,String[] dataToWrite) {
		
		File file = new File(System.getProperty("user.dir") + filePath+"\\"+fileName);
		FileInputStream fileInputStream;
		
		try {
			fileInputStream = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}

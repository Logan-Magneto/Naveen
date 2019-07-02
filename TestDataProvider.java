package naveen.automate.dataprovider;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import naveen.automate.utilitize.FileUtilitize;

public class TestDataProvider {
	
	@DataProvider(name = "dataRead1")
	public static Object[][] testMethod(){
		System.out.println("test");
		List<HashMap<String, Object>> returnList;
		FileUtilitize fileUtilitize = new FileUtilitize();
		returnList = fileUtilitize.readExcelFile("/src/main/resources/Excels", "TestFile.xlsx", 0);
		Object[] converted = returnList.toArray();
		Object[][] array = new Object[converted.length][1];
		for(int i = 0; i < converted.length; i++)
		{
			array[i][0] = converted[i];
		}
		return array;
		
	}

}

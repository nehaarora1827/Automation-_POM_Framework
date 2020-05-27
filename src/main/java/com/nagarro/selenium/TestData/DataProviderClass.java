package com.nagarro.selenium.TestData;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.nagarro.selenium.Base.BaseClass;
import com.nagarro.selenium.Utilities.ExcelReader;

public class DataProviderClass {
public static Logger log = Logger.getLogger(DataProviderClass.class.getName());
	
	public static Object[][] getData(ExcelReader xls, String testCaseName){
		String sheetName="TestData";
		// reads data for only testCaseName
		
		int testStartRowNum=1;
		System.out.println("test case name:" + xls.getCellData(sheetName, 0, testStartRowNum));
		log.debug(xls.getCellData(sheetName, 0, testStartRowNum));
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)){
			testStartRowNum++;
		}
		System.out.println("Test starts from row - "+ testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		// calculate rows of data
		int rows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
		System.out.println("Total rows are  - "+rows );
		
		//calculate total cols
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
		System.out.println("Total cols are  - "+cols );
		Object[][] data = new Object[rows][1];
		//read the data
		int dataRow=0;
		Hashtable<String,String> table=null;
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key=xls.getCellData(sheetName,cNum,colStartRowNum);
				String value= xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
				// 0,0 0,1 0,2
				//1,0 1,1
			}
			data[dataRow][0] =table;
			dataRow++;
		}
		return data;
	}
	
	@DataProvider
	public Object[][] getData(){
        return getData(BaseClass.exl, "verifyProductOnSearchResultsPage");
    }

}

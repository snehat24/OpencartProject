package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
    
	@DataProvider(name="Logindata")
	public String[][] getData() throws IOException{
		String path=".\\testdata\\OpencartDatas.xlsx";
		LoginUtility uti=new LoginUtility(path);
		int totalrows = uti.getRowCount("Sheet1");
		int totalcols = uti.getCellCount("Sheet1",1);
		String data[][]=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcols;j++) {
				data[i-1][j]=uti.getCellData("Sheet1", i, j);
			}
		}
		return data;
	}
}

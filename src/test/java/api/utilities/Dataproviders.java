package api.utilities;

import java.io.IOException;



import org.testng.annotations.DataProvider;

public class Dataproviders {

	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//testData.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int rownum=xl.getRowCount("Hoja1");
		int colcount=xl.getCellCount("Hoja1",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=xl.getCellData("Hoja1", i, j);
			}
		}
		//System.out.println(Integer.toString(rownum)+"    "+Integer.toString(colcount));
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//testData.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int rownum=xl.getRowCount("Hoja1");
		
		String apidata[]=new String[rownum];
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]=xl.getCellData("Hoja1", i, 1);
		}
		//System.out.println(apidata);
		return apidata;

	}

}

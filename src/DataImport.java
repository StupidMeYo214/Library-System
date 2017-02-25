import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataImport {
	
/***********************TAB AS SEPARATOR***********************************/
	public static List<String[]> getData(String file)
	{	
		 List<String[]> result=new ArrayList<String[]>();
		InputStream stream = DataImport.class.getResourceAsStream(file);
		BufferedReader reader = null;
		try{
			String readerLine;
			reader = new BufferedReader(new InputStreamReader(stream));
			
			while((readerLine = reader.readLine())!=null)
			{
				ArrayList<String> al=CSVtoAL(readerLine);
				String[] temp=new String[al.size()];
				for(int j=0; j<al.size(); j++)
				{
				temp[j]=al.get(j);
				}
				result.add(temp);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{if(reader!=null)
				reader.close();}
			catch(IOException readerException)
			{
				readerException.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public static ArrayList<String> CSVtoAL(String readerRST)
	{
		ArrayList<String> result = new ArrayList<String>();
		
		if(readerRST != null)
		{
			String[] splitdata = readerRST.split("	");
			for(int i=0; i<splitdata.length;i++)
			{
				if(!(splitdata[i]==null)||!(splitdata[i].length()==0))
				{
					result.add(splitdata[i].trim());
				}
			}
		}
		return result;
	}
	
/**********************************COMMA AS SEPARATOR*****************************************************/
	public static List<String[]> getData2(String file)
	{	
		 List<String[]> result=new ArrayList<String[]>();
		InputStream stream = DataImport.class.getResourceAsStream(file);
		BufferedReader reader = null;
		try{
			String readerLine;
			reader = new BufferedReader(new InputStreamReader(stream));
			
			while((readerLine = reader.readLine())!=null)
			{
				ArrayList<String> al=CSVtoAL2(readerLine);
				String[] temp=new String[al.size()];
				for(int j=0; j<al.size(); j++)
				{
				temp[j]=al.get(j);
				}
				result.add(temp);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{if(reader!=null)
				reader.close();}
			catch(IOException readerException)
			{
				readerException.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public static ArrayList<String> CSVtoAL2(String readerRST)
	{
		ArrayList<String> result = new ArrayList<String>();
		
		if(readerRST != null)
		{
			String[] splitdata = readerRST.split("\\s*,\\s*");
			for(int i=0; i<splitdata.length;i++)
			{
				if(!(splitdata[i]==null)||!(splitdata[i].length()==0))
				{
					result.add(splitdata[i].trim());
				}
			}
		}
		return result;
	}

	//*********test****************************
	  public static void main(String args[]){
		List<String []> result=DataImport.getData("books.csv");
		for(int i=0; i<result.size(); i++)
		{
			
			System.out.println(result.get(i)[3]+"	");
			System.out.println("");
		}
	  	}

	  
}

import java.util.ArrayList;
import java.util.List;

public class NameSplit{

	public static List<String> nameSplit(String x)
	{
		List<String> str=new ArrayList<>();
		String Title="";
		String Fname="";
		String Mname="";
		String Lname="";
		String Suffix="";
		String [] temp=x.split("\\ ");
		List<String> nameComponents=new ArrayList<>();
		
		for(int i = 0; i<temp.length; i++)
		{
			nameComponents.add(temp[i]);
		}
		
		if(nameComponents.contains("PhD."))
		{
			Title=Title+"PhD. ";
			nameComponents.remove("PhD.");
		}
		
		else if(nameComponents.contains("PhD"))
		{
			Title=Title+"PhD ";
			nameComponents.remove("PhD");
		}
		
		else if(nameComponents.contains("Phd"))
		{
			Title=Title+"Phd ";
			nameComponents.remove("Phd");
		}
		
		else if(nameComponents.contains("Professor"))
		{
			Title=Title+"Professor ";
			nameComponents.remove("Professor");
		}
		
		else if(nameComponents.contains("RN"))
		{
			Title=Title+"RN ";
			nameComponents.remove("RN");
		}
		
		else if(nameComponents.contains("FAAN"))
		{
			Title=Title+"FAAN ";
			nameComponents.remove("FAAN");
		}
		
		else if(nameComponents.contains("DR.")) 
		{
			Title=Title+"DR.";
			nameComponents.remove("DR.");
		}
		
		else if (nameComponents.contains("Dr."))
		{
			Title=Title+"Dr. ";
			nameComponents.remove("Dr.");
		}
		
		if (nameComponents.contains("Jr."))
		{
			Suffix=Suffix+"Jr.";
			nameComponents.remove("Jr.");
		}
		
		else if (nameComponents.contains("Sr."))
		{
			Suffix=Suffix+"Sr.";
			nameComponents.remove("Sr.");
		}
		
		else if (nameComponents.contains("III"))
		{
			Suffix=Suffix+"III";
			nameComponents.remove("III");
		}
		
		else if (nameComponents.contains("II"))
		{
			Suffix=Suffix+"II";
			nameComponents.remove("II");
		}
		
		
		else if (nameComponents.contains("IV"))
		{
			Suffix=Suffix+"IV";
			nameComponents.remove("IV");
		}
		
		
		else if (nameComponents.contains("XIV"))
		{
			Suffix=Suffix+"XIV";
			nameComponents.remove("XIV");
		}
		
		if(nameComponents.size()>0)
		{
			Fname=nameComponents.get(0);
			nameComponents.remove(Fname);
		}
		if(nameComponents.size()>0)
		{
			Lname=nameComponents.get(nameComponents.size()-1);
			nameComponents.remove(Lname);
		}
		if(nameComponents.size()>0)
		{
			for(int k=0; k<nameComponents.size();k++)
		{
			Mname=Mname+nameComponents.get(k);
		}
		}
		
		str.add(Title);
		str.add(Fname);
		str.add(Mname);
		str.add(Lname);
		str.add(Suffix);
		return str;
	} 
	
	public static void main(String [] args){
		List<String> str=nameSplit("Dalai Lama XIV");
		System.out.println(str.size());
		for(int i =0 ; i< str.size(); i++)
		{
			System.out.println(str.get(i));
		}
	}
}

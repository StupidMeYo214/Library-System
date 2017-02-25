import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CheckinSystem {
String book_id="";
String Card_no="";
String Fname="";
String Lname="";

public static String search(String BID, String CID, String FN,String LN){
	String result="Loan_id	Card_Number ISBN	Due_date	Borrower F/L Name	Book_Title:\n";
	
	List<String[]> temp2=new ArrayList<>();
	Connection conn;
	ResultSet rs=null;

	try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
		Statement stmt = conn.createStatement();
		stmt.execute("use LIBRARY;");
		//If Card Number is provided, then ignore Name information
		//Card number only
		if((!CID.equals("")) && BID.equals("") && (FN.equals("")) &&(LN.equals("")))
			{
				rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K , book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
			}
		else if((!CID.equals("")) && BID.equals("") && (!FN.equals("")) &&(LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && BID.equals("") && (FN.equals("")) &&(!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && BID.equals("") && (!FN.equals("")) &&(!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		
			//Card number + ISBN
		else if((!CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (LN.equals("")))
			{
				rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
			}
		else if((!CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		
		//FN only
		else if((CID.equals("")) && BID.equals("") && (!FN.equals("")) && LN.equals(""))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no=B.Card_no and B.Fname='"+FN+"'  and L.Isbn=K.Isbn order by L.loan_id;");}
			//LN only
		else if((CID.equals("")) && BID.equals("") && (FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no=B.Card_no and B.Lname='"+LN+"' and L.Isbn=K.Isbn order by L.loan_id;");}
			//FN+LN
		else if((CID.equals("")) && BID.equals("") && (!FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no=B.Card_no and B.Fname='"+FN+"' and B.Lname='"+LN+"' and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN only
		else if((CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN+FN
		else if((CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.ISBN='"+BID+"' and L.Card_no=B.Card_no and B.Fname='"+FN+"'  and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN+LN
		else if((CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.ISBN='"+BID+"' and L.Card_no=B.Card_no and B.Lname='"+LN+"'  and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN+FN+LN
		else if((CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.ISBN='"+BID+"' and L.Card_no=B.Card_no and B.Fname='"+FN+"' and B.Lname='"+LN+"' and L.Isbn=K.Isbn order by L.loan_id;");}
			//empty
		else if((CID.equals("")) && (BID.equals("")) && (FN.equals("")) && (LN.equals("")))
			{result="Please enter at least one part of the information cell!";}
	
		if(rs==null)
		{return result;}
	
		while(rs.next())
		{
			String [] temp1={"","","","","","",""};
			
			/*result=result+rs.getString("K.title")+"\n";
			result=result+rs.getString("L.Loan_id")+"	";
			result=result+rs.getString("L.Card_no")+"	";
			result=result+rs.getString("L.Isbn")+"	";
			result=result+rs.getString("L.Due_Date")+"	";
			result=result+rs.getString("B.Fname")+"	";
			result=result+rs.getString("B.Lname")+"\n";
			*/
			temp1[6]=rs.getString("K.title");
			temp1[0]=rs.getString("L.Loan_id");
			temp1[1]=rs.getString("L.Card_no");
			temp1[2]=rs.getString("L.Isbn");
			temp1[3]=rs.getString("L.Due_Date");
			temp1[4]=rs.getString("B.Fname");
			temp1[5]=rs.getString("B.Lname")+"\n";
			
			temp2.add(temp1);
		}
		
		for(int i =0; i<temp2.size(); i++)
		{
			for(int j = 0; j<temp2.get(i).length; j++)
			{
				result=result+temp2.get(i)[j]+"	";
			}
			result=result+"\n";
		}	
		
		rs.close();
		conn.close();
		
	}
	catch(SQLException e){};
	return result;
}

public static String[] searchToArray(String BID, String CID, String FN,String LN){
	String result="Loan_id	Card_Number ISBN	Due_date	Borrower F/L Name	Book_Title:\n";
	
	List<String[]> temp2=new ArrayList<>();
	Connection conn;
	ResultSet rs=null;
	String [] none={"Borrow Information Cannot be Found."};
	try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
		Statement stmt = conn.createStatement();
		stmt.execute("use LIBRARY;");
		//If Card Number is provided, then ignore Name information
		//Card number only
		if((!CID.equals("")) && BID.equals("") && (FN.equals("")) &&(LN.equals("")))
			{
				rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K , book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
			}
		else if((!CID.equals("")) && BID.equals("") && (!FN.equals("")) &&(LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && BID.equals("") && (FN.equals("")) &&(!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && BID.equals("") && (!FN.equals("")) &&(!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		
			//Card number + ISBN
		else if((!CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (LN.equals("")))
			{
				rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
			}
		else if((!CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		
		//FN only
		else if((CID.equals("")) && BID.equals("") && (!FN.equals("")) && LN.equals(""))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no=B.Card_no and B.Fname='"+FN+"'  and L.Isbn=K.Isbn order by L.loan_id;");}
			//LN only
		else if((CID.equals("")) && BID.equals("") && (FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no=B.Card_no and B.Lname='"+LN+"' and L.Isbn=K.Isbn order by L.loan_id;");}
			//FN+LN
		else if((CID.equals("")) && BID.equals("") && (!FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no=B.Card_no and B.Fname='"+FN+"' and B.Lname='"+LN+"' and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN only
		else if((CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN+FN
		else if((CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.ISBN='"+BID+"' and L.Card_no=B.Card_no and B.Fname='"+FN+"'  and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN+LN
		else if((CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.ISBN='"+BID+"' and L.Card_no=B.Card_no and B.Lname='"+LN+"'  and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN+FN+LN
		else if((CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.ISBN='"+BID+"' and L.Card_no=B.Card_no and B.Fname='"+FN+"' and B.Lname='"+LN+"' and L.Isbn=K.Isbn order by L.loan_id;");}
			//empty
		else if((CID.equals("")) && (BID.equals("")) && (FN.equals("")) && (LN.equals("")))
			{result="Please enter at least one part of the information cell!";
			none[0]=result ;
			return none;}
	
		if(rs==null)
		{return none;}
	
		while(rs.next())
		{
			String [] temp1={"","","","","","",""};
			
			/*result=result+rs.getString("K.title")+"\n";
			result=result+rs.getString("L.Loan_id")+"	";
			result=result+rs.getString("L.Card_no")+"	";
			result=result+rs.getString("L.Isbn")+"	";
			result=result+rs.getString("L.Due_Date")+"	";
			result=result+rs.getString("B.Fname")+"	";
			result=result+rs.getString("B.Lname")+"\n";
			*/
			temp1[6]=rs.getString("K.title");
			temp1[0]=rs.getString("L.Loan_id");
			temp1[1]=rs.getString("L.Card_no");
			temp1[2]=rs.getString("L.Isbn");
			temp1[3]=rs.getString("L.Due_Date");
			temp1[4]=rs.getString("B.Fname");
			temp1[5]=rs.getString("B.Lname")+"\n";
			
			temp2.add(temp1);
		}
		
		int length=temp2.size();
		String [] resultToArray=new String[length];
		
		for(int i =0; i<temp2.size(); i++)
		{
			String row="";
			for(int j = 0; j<temp2.get(i).length; j++)
			{
				row=row+temp2.get(i)[j]+"	";
			}
			row="<html><pre>"+row+"</pre></html>";
			resultToArray[i]=row;
		}	
		
		rs.close();
		conn.close();
		return resultToArray;
	}
	catch(SQLException e){};
	return none;
}

public static String[] searchLoan_idToArray(String BID, String CID, String FN,String LN){
	
	List<String> temp2=new ArrayList<>();
	Connection conn;
	ResultSet rs=null;
	String [] none={"Borrow Information Cannot be Found."};
	try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
		Statement stmt = conn.createStatement();
		stmt.execute("use LIBRARY;");
		//If Card Number is provided, then ignore Name information
		//Card number only
		if((!CID.equals("")) && BID.equals("") && (FN.equals("")) &&(LN.equals("")))
			{
				rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K , book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
			}
		else if((!CID.equals("")) && BID.equals("") && (!FN.equals("")) &&(LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && BID.equals("") && (FN.equals("")) &&(!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && BID.equals("") && (!FN.equals("")) &&(!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		
			//Card number + ISBN
		else if((!CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (LN.equals("")))
			{
				rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
			}
		else if((!CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		else if((!CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (!LN.equals("")))
		{
			rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no='"+CID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");
		}
		
		//FN only
		else if((CID.equals("")) && BID.equals("") && (!FN.equals("")) && LN.equals(""))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no=B.Card_no and B.Fname='"+FN+"'  and L.Isbn=K.Isbn order by L.loan_id;");}
			//LN only
		else if((CID.equals("")) && BID.equals("") && (FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no=B.Card_no and B.Lname='"+LN+"' and L.Isbn=K.Isbn order by L.loan_id;");}
			//FN+LN
		else if((CID.equals("")) && BID.equals("") && (!FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Card_no=B.Card_no and B.Fname='"+FN+"' and B.Lname='"+LN+"' and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN only
		else if((CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.Isbn='"+BID+"' and L.Card_no=B.Card_no and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN+FN
		else if((CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.ISBN='"+BID+"' and L.Card_no=B.Card_no and B.Fname='"+FN+"'  and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN+LN
		else if((CID.equals("")) && (!BID.equals("")) && (FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.ISBN='"+BID+"' and L.Card_no=B.Card_no and B.Lname='"+LN+"'  and L.Isbn=K.Isbn order by L.loan_id;");}
			//ISBN+FN+LN
		else if((CID.equals("")) && (!BID.equals("")) && (!FN.equals("")) && (!LN.equals("")))
			{rs=stmt.executeQuery("select K.title, L.Loan_id,L.Card_no,L.Isbn,L.Due_Date,B.Fname,B.Lname from book K ,book_loans L, borrower B where L.ISBN='"+BID+"' and L.Card_no=B.Card_no and B.Fname='"+FN+"' and B.Lname='"+LN+"' and L.Isbn=K.Isbn order by L.loan_id;");}
			//empty
	
		if(rs==null)
		{return none;}
	
		while(rs.next())
		{
			String temp=rs.getString("L.Loan_id");
			
			/*result=result+rs.getString("K.title")+"\n";
			result=result+rs.getString("L.Loan_id")+"	";
			result=result+rs.getString("L.Card_no")+"	";
			result=result+rs.getString("L.Isbn")+"	";
			result=result+rs.getString("L.Due_Date")+"	";
			result=result+rs.getString("B.Fname")+"	";
			result=result+rs.getString("B.Lname")+"\n";
			*/
		
			/*temp1[0]=rs.getString("L.Loan_id")+"\n";*/
			
			temp2.add(temp);
		}
		
		int length=temp2.size();
		String [] resultToArray=new String[length];
		
		for(int i =0; i<temp2.size(); i++)
		{
			String row="";
			
			row=row+temp2.get(i);

			resultToArray[i]=row;
		}	
		
		rs.close();
		conn.close();
		return resultToArray;
	}
	catch(SQLException e){};
	return none;
}

public static String checkin(String LoanID){
	Connection conn=null;
	ResultSet rs=null;
	String result="Checkin Success. Your Fine Amount is: ";
	try{
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat format1=new SimpleDateFormat("YYYY-MM-dd");
		String today=format1.format(cal.getTime());
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
		Statement stmt = conn.createStatement();
		stmt.execute("use LIBRARY;");
	
		
		//1_update book_loans(date_in)
		stmt.execute("update book_loans set Date_in='"+today+"' where Loan_id='"+LoanID+"';");
		//2_update book_copies(No_out, No_available)
		stmt.execute("update book_copies C, book_loans L set C.no_out=C.no_out-1, C.no_available=C.no_available+1 where  L.loan_id='"+LoanID+"' and  L.isbn=C.book_id and L.branch_id=C.branch_id");
		//3_update borrower(N_Book_Borrowed)
		stmt.execute("update book_loans L, borrower B set B.n_book_borrowed=B.n_book_borrowed-1 where L.loan_id='"+LoanID+"' and L.card_no=B.card_no;");
		//4_update fines(fine_amt)
		stmt.execute("update fines F, book_loans L set F.fine_amt = 0.25*datediff('"+today+"',L.Due_Date) where F.loan_id=L.Loan_id and L.loan_id='"+LoanID+"' and '"+today+"'>L.Due_date");
		
		rs=stmt.executeQuery("select fine_amt from fines where Loan_id='"+LoanID+"';");
		
		while(rs.next()){
			result=result+rs.getString("fine_amt");
		}
		rs.close();
		conn.close();
	}
	catch(SQLException e){}
	return result;
	
}


}

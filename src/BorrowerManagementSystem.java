import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BorrowerManagementSystem {

	public static String CreateBorrowerID(String SSN, String FN, String LN, String Address, String PN)
	{
		String prompt="";
		
		Connection conn=null;
		ResultSet rs=null;
		ResultSet temp=null;
		
		//1.Check whether the information is satisfied with the requirement
		if(SSN.equals("")||FN.equals("")||LN.equals("")||Address.equals(""))
			{
				prompt="Some of the required messages are missing, please check your SSN, First Name, Last Name, Address inputs.";
				return prompt;
			}
		//1.1 Check whether the SSN is a legal one
		if(SSN.length()!=9 || !SSN.matches("[0-9]+"))
		{
			prompt="The SSN Is Illegal! Please Enter 9 Digits SSN!";
			return prompt;
		}
		try{
			//2.Check whether the SSN has owed a card
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
			Statement stmt = conn.createStatement();
			stmt.execute("use library;");
			
			rs=stmt.executeQuery("select Card_no from BORROWER where Ssn='"+SSN+"';");
			
			int rows=0;
			while(rs.next())
			{
				rows++;
			}
			if(rows!=0)
			{
				String card="";
				rs=stmt.executeQuery("select Card_no from BORROWER where Ssn='"+SSN+"';");
				while (rs.next())
				{
					card=rs.getString("Card_no");
				}
				prompt="You Have Already Owned a Card.\nYour Card Number is: "+card;
			
				rs.close();
				conn.close();
				return prompt;
			}
			
			//Creation requirements are all satisfied, start to insert
			else
			{
				//1_generate new Card ID
				int borrowers=1;
				temp=stmt.executeQuery("select * from BORROWER;");
				
				while(temp.next())
				{borrowers++;}
				String newCardId="ID"+String.format("%06d", borrowers);
				//2_insert values
				stmt.execute("insert into BORROWER(Card_no,Ssn,Fname,Lname,Address,Phone,N_Book_Borrowed) values ('"+newCardId+"','"+SSN+"','"+FN+"','"+LN+"','"+Address+"','"+PN+"',0);");
				prompt="Congratulations! Your new Account Has benn Created!\nYour Card Number is: "+newCardId;
				temp.close();
				conn.close();
				return prompt;
				
			}
			
			
			
		}
		catch(SQLException e){System.out.println("Eroor occurs: "+ e.getStackTrace());}
		
		
		
		
		
		return prompt;
	}
}

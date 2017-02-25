import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FineManegementSystem {

	public static String refresh(){
		String result="";
		Connection conn=null;
		try{
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
		Statement stmt=conn.createStatement();
		stmt.execute("use library;");
		
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat format1=new SimpleDateFormat("YYYY-MM-dd");
		String today=format1.format(cal.getTime());
		
		//1_ set fines paid attributes of loans that has been turned in on time in TRUE
		stmt.execute("update fines F, book_loans L set F.paid = TRUE where L.loan_id=F.Loan_id and datediff(L.due_date,L.date_in)>=0 and F.paid=FALSE and datediff(curdate(),L.date_in)>=0;");
		//2_ set fine amounts for book turned in but not paid yet
		stmt.execute("update fines F, book_loans L set F.fine_amt=0.25*datediff(L.date_in,L.due_date) where L.loan_id=F.loan_id and F.paid=FALSE and datediff(L.date_in,L.due_date)>0 and (L.date_in is not null or datediff(curdate(),L.Date_in)>0);");
		//3_ set fine amounts for book that has not turned in
		stmt.execute("update fines F, book_loans L set F.fine_amt = 0.25*datediff(curdate(),L.due_date) where F.Loan_id=L.Loan_id and F.paid=FALSE and datediff(curdate(),L.due_date)>0 and datediff(L.date_in,L.due_date)>0 and (datediff(L.date_in,curdate())>0 or L.date_in is null);");
		
		result="Fine Update Success!";
		
		conn.close();
		}
		catch(SQLException e){System.out.println("Eoor occurs in Fine Refreshing. "+e.getErrorCode());}
		
		return result;
	}
	
	public static String check(String card){
		String result="";
		
		Connection conn=null;
		ResultSet rs=null;
		
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
			Statement stmt=conn.createStatement();
			
			stmt.execute("use library;");
			
			int count=0;
			rs=stmt.executeQuery("select * from borrower where card_no='"+card+"';");
			while(rs.next())
			{count++;}
			if(count==0)
			{
				return "Please Enter A Valid Card ID";
			}
			
			//check total fine amount
			rs=stmt.executeQuery("select sum(F.fine_amt) from FINES F, BOOK_LOANS L where L.Card_no='"+card+"' and L.Loan_id=F.Loan_id and paid=0;");
			String totalfine="";
			while(rs.next())
			{
				totalfine=totalfine+rs.getString("sum(F.fine_amt)");
			}
			
			//check payable fine amount, i.e. book has been returned
			rs=stmt.executeQuery("select sum(F.fine_amt) from FINES F, BOOK_LOANS L where L.Card_no='"+card+"' and L.Loan_id=F.Loan_id and (L.Date_in is not null or datediff(L.date_in,curdate())<0) and paid=0; ");
			String payablefine="";
			while(rs.next())
			{
				payablefine=payablefine+rs.getString("sum(F.fine_amt)");
			}
			
			result="Your Total Fine Fee is: $"+totalfine+". The amount you can pay is $"+payablefine;
			rs.close();
			conn.close();
		}
		catch(SQLException e){System.out.println("Error Occurs in Fine Checking System: "+e.getErrorCode());}
		
		
		return result;
	}
	
	public static String pay(String CardID)
	{
		String result="";
		Connection conn=null;
		
		
		try
		{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
			Statement stmt=conn.createStatement();
			stmt.execute("use library");
			
			stmt.execute("update book_loans L, fines F set F.paid=TRUE where L.Card_no='"+CardID+"' and L.Loan_id=F.Loan_id and (L.Date_in is not null or datediff(L.date_in,curdate())<0) and paid=0; ");
			result="Fine Payed Success!";
			
			conn.close();
		}
		catch(SQLException e){System.out.print("Error Occurs in Paying Fines:"+e.getErrorCode());}
		
		
		return result;
	}
	
}

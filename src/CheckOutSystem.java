import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CheckOutSystem {

	public static String checkout(String ISBN,String BID,String CID)
	{
		String prompt="";
		
		if(ISBN.equals("") || BID.equals("") ||CID.equals(""))
		{prompt="Some Information is Missing. \nPlease Enter Full Information.";
		return prompt;}
		
		Connection conn=null;
		ResultSet rs=null;
		try{
			Calendar cal=Calendar.getInstance();
			Calendar cal2=Calendar.getInstance();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			//ResultSetMetaData rsMetaData = rs.getMetaData();
			
//A			//Does Card Number Exist?
			rs=stmt.executeQuery("select * "
								+ "from BORROWER"
								+ "where Card_no="+"'"+CID+"';");
			int noRows=0;
			while(rs.next())
			{noRows++;}
			
			if(noRows==0){
			prompt="Sorry, No Such ID Exist, \nPlease Create A New ID First.";
			return prompt;
			}
			
//B			//Does the Number of Book Borrowed Under 3?-------1)update data 2)check
			//1
			
			String date = format1.format(cal.getTime());
			
			stmt.execute("update BORROWER B inner join ( select L.Card_no,L.Date_in, count(*) as num from  BOOK_LOANS L where L.Card_no='"+CID+"' and L.Date_in>"+"'"+date+"' group by L.Card_no ) J ON B.Card_no=J.Card_no set N_Book_Borrowed = num;");
			//2
			int bbn=0;
			rs=stmt.executeQuery("select N_Book_Borrowed from BORROWER where Card_no='"+CID+"';");
			while(rs.next()){
			bbn=rs.getInt(1);
			}
			if(bbn==3)
			{
				prompt="You Have Already Reached Maximum Limit \n of Borrwing Book Number! Please Turn back Books before Borrowing New Ones.";
				return prompt;
			}
			
//C			//Does the Book is Available in This Library Branch? If not, show other branches that has the book.
			int ban=0;
			rs=stmt.executeQuery("select No_available from BOOK_COPIES where Book_id='"+ISBN+"' and Branch_id='"+BID+"';");
			while(rs.next())
			{
				ban=rs.getInt(1);
			}
			if(ban==0)
			{
				rs=stmt.executeQuery("select * from LIBRARY_BRANCH L, BOOK_COPIES C where C.Book_id='"+ISBN+"' and L.Branch_id=C.Branch_id and C.No_available>0;");
				int row=0;
				while(rs.next())
				{row++;}
				
				if(row==0)
				{prompt="This book is not available in this branch \n or other branches right now.";
				return prompt;}
				else
				{
					prompt="This book is not available in this branch.\n";
					rs=stmt.executeQuery("select L.Branch_name, C.No_available from LIBRARY_BRANCH L, BOOK_COPIES C where C.Book_id='"+ISBN+"' and L.Branch_id=C.Branch_id and C.No_available>0;");
					while(rs.next())
					{
						String branchname=rs.getString(1);
						int numav=rs.getInt(2); 
						prompt=prompt+branchname+"still have "+numav+" copies in store.\n";
					}
					return prompt;
				}
					
			}
			
//D			//checked, checking out book.
			else
			{
				//1_update borrower(N_Book_Borrowed)
				stmt.execute("update BORROWER set N_Book_Borrowed=N_Book_Borrowed+1 where Card_no='"+CID+"';");
				
				//2_insert book_loans(loan_id```)-----1) generate new loan_id(count(*)+1 2) insert all columns
				//2.1_generate new loan_id
				ResultSet r=stmt.executeQuery("select * from BOOK_LOANS;");
				int loans=1;
				while(r.next())
				{
					loans++;
				}
				
				String loanid=Integer.toString(loans);
				//2.2_insert
				String today=format1.format(cal.getTime());
				cal2.add(Calendar.DATE, 14);
				String duedate = format1.format(cal2.getTime());
				stmt.execute("insert into BOOK_LOANS(Loan_id,Isbn,Branch_id,Card_no,Date_out,Due_date) values ('"+loanid+"','"+ISBN+"','"+BID+"','"+CID+"','"+today+"','"+duedate+"');");				
				
				//3_update book_copies(No_out+1 & No_available-1)
				stmt.execute("update BOOK_COPIES set No_out=No_out+1, No_available=No_available-1 where Book_id='"+ISBN+"' and Branch_id='"+BID+"' ;");
				
				//4_insert Fines(loan_id,0,FALSE)
				stmt.execute("insert into FINES(Loan_id,fine_amt,paid) values('"+loanid+"','0',FALSE)");
				
				//5_return success message and remind the turn in due date
				prompt="Check-Out Success! Enjoy Your Reading! \nNotice: Please return this book before "+duedate+" . \nOr you will receive fines $0.25 for each extra date out.\nThank you!";
				r.close();
				
			}
				
				rs.close();
				conn.close();
				return prompt;
			
		}
		catch(SQLException e){System.out.println("Error in connextion attempt:"+e.getMessage());}
		
		return prompt;
	}
	
}

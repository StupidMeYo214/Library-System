import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookSearchSystem {
	
public static String modify(String x){
		
		if(x.contains("'")){
			String y="";
			String[] subx=x.split("\\'");
			for(int j=0; j<subx.length-1;j++)
			{y=y+subx[j]+"'"+"'";}
			y=y+subx[subx.length-1];
			return y;
		}
		else
		return x;
	} 

	
	
	public static String search(String I,String T, String A){
		
		String result=""; 
		Connection conn=null;
		T=modify(T);
		A=modify(A);
		ResultSet rs=null;
		try{
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			if((I.equals(""))&&(T.equals(""))&&(A.equals("")))
			{result="Please Enter At Least One Information Column Above!";
			return result;}
			if((!I.equals(""))&&(T.equals(""))&&(A.equals("")))
			rs = stmt.executeQuery("SELECT B.Isbn, B.Title, B.Authors, L.Branch_id, L.Branch_name, C.No_of_copies, C.No_available FROM BOOK B, library_branch L, book_copies C where (B.Isbn like '%"+I+"%' ) and B.Isbn=C.Book_id and C.Branch_id=L.Branch_id;");
			else if ((I.equals(""))&&(!T.equals(""))&&(A.equals("")))
			rs = stmt.executeQuery("SELECT B.Isbn, B.Title, B.Authors, L.Branch_id, L.Branch_name, C.No_of_copies, C.No_available FROM BOOK B, library_branch L, book_copies C where (B.Title like '%"+T+"%' ) and B.Isbn=C.Book_id and C.Branch_id=L.Branch_id;");
			else if((I.equals(""))&&(T.equals(""))&&(!A.equals("")))
			rs = stmt.executeQuery("SELECT B.Isbn, B.Title, B.Authors, L.Branch_id, L.Branch_name, C.No_of_copies, C.No_available FROM BOOK B, library_branch L, book_copies C where (B.Authors like '%"+A+"%' ) and B.Isbn=C.Book_id and C.Branch_id=L.Branch_id;");
			else if((!I.equals(""))&&(!T.equals(""))&&(A.equals("")))
			rs = stmt.executeQuery("SELECT B.Isbn, B.Title, B.Authors, L.Branch_id, L.Branch_name, C.No_of_copies, C.No_available FROM BOOK B, library_branch L, book_copies C where (B.Isbn like '%"+I+"%' ) and (B.Title like '%"+T+"%' )  and B.Isbn=C.Book_id and C.Branch_id=L.Branch_id;");
			else if((I.equals(""))&&(!T.equals(""))&&(!A.equals("")))
			rs = stmt.executeQuery("SELECT B.Isbn, B.Title, B.Authors, L.Branch_id, L.Branch_name, C.No_of_copies, C.No_available FROM BOOK B, library_branch L, book_copies C where (B.Authors like '%"+A+"%' ) and (B.Title like '%"+T+"%' )  and B.Isbn=C.Book_id and C.Branch_id=L.Branch_id;");
			else if((!I.equals(""))&&(T.equals(""))&&(!A.equals("")))
			rs = stmt.executeQuery("SELECT B.Isbn, B.Title, B.Authors, L.Branch_id, L.Branch_name, C.No_of_copies, C.No_available FROM BOOK B, library_branch L, book_copies C where (B.Isbn like '%"+I+"%' ) and (B.Authors like '%"+A+"%' )  and B.Isbn=C.Book_id and C.Branch_id=L.Branch_id;");
			else if((!I.equals(""))&&(!T.equals(""))&&(!A.equals("")))
			rs = stmt.executeQuery("SELECT B.Isbn, B.Title, B.Authors, L.Branch_id, L.Branch_name, C.No_of_copies, C.No_available FROM BOOK B, library_branch L, book_copies C where (B.Isbn like '%"+I+"%' ) and (B.Title like '%"+T+"%' ) and (B.Authors like '%"+A+"%' ) and B.Isbn=C.Book_id and C.Branch_id=L.Branch_id;");
			
			while(rs.next())
			{

				String ISBN=rs.getString("B.Isbn");
				String title=rs.getString("B.Title");
				String authors=rs.getString("B.Authors");
				String branchid=rs.getString("L.Branch_id");
				String branchname=rs.getString("L.Branch_name");
				String numco=rs.getString("C.No_of_copies");
				String numav=rs.getString("C.No_available");
				result=result+branchid+"	"+branchname+"	"+numco+"	"+numav+"	"+ISBN+"	"+authors+"	"+title+"\n";
				
			}
			rs.close();
			conn.close();
		}
		catch(SQLException e)
			{System.out.println("Error in connextion attempt:"+e.getMessage()); }
	
		
		
	return result;

	
	
}

}
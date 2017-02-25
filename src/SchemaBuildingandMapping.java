import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SchemaBuildingandMapping {

//used to modify book titles that contains "'" that may violate insert operations
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

//used to generate author_id in format like: AID0000101
public static String formatString(Integer i){
	String x=String.format("%010d", i);
	return "AID"+x;
}

	static Connection conn=null;
	
	public static void main(String[] args){
		
		try {
			
			//create a connection to mysql
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","651023");
			
			//create a statement
			Statement stmt = conn.createStatement();
			stmt.execute("use LIBRARY;");
			
/*			//create a schema
			String CreateSchema="create schema LIBRARY";
			stmt.execute(CreateSchema);
*/			
			//create tables
/*			String CreateTable_BOOK="create table BOOK(Isbn CHAR(10) NOT NULL, Title VARCHAR(500), Authors VARCHAR(300), primary key(Isbn));";
			String CreateTable_BOOK_AUTHORS="create table BOOK_AUTHORS(Isbn CHAR(10) NOT NULL, Author_id CHAR(13) NOT NULL, primary key(Isbn, AUTHOR_id));";
			String CreateTable_AUTHORS="create table AUTHORS(Author_id CHAR(13) NOT NULL, Title VARCHAR(30), Fname VARCHAR(50), Mname VARCHAR(50), Lname VARCHAR(50), Full_name VARCHAR(150), Suffix VARCHAR(50), primary key(Author_id));";
			String CreateTable_BOOK_COPIES="create table BOOK_COPIES(Book_id CHAR(10) NOT NULL, Branch_id VARCHAR(5) NOT NULL, No_of_copies INT, No_out INT, No_available INT, primary key(Book_id, Branch_id));";
			String CreateTable_LIBRARY_BRANCH="create table LIBRARY_BRANCH(Branch_id VARCHAR(5) NOT NULL, Branch_name VARCHAR(50), Address VARCHAR(100), primary key(Branch_id));";
			String CreateTable_BORROWER="create table BORROWER(Card_no VARCHAR(20) NOT NULL, Ssn VARCHAR(20) NOT NULL, Fname VARCHAR(20) NOT NULL, Lname VARCHAR(20) NOT NULL, Address VARCHAR(100) NOT NULL, Phone VARCHAR(20), N_Book_Borrowed INT, primary key(Card_no));";
			String CreateTable_BOOK_LOANS="create table BOOK_LOANS(Loan_id VARCHAR(20) NOT NULL, Isbn CHAR(10), Branch_id VARCHAR(5), Card_no VARCHAR(20), Date_out DATE NOT NULL, Due_Date DATE, Date_in DATE, primary key(Loan_id));";
			String CreateTable_FINES="create table FINES(Loan_id VARCHAR(20) NOT NULL, fine_amt  DECIMAL(6,2), PAID BOOLEAN NOT NULL, primary key(Loan_id));";
			
//stmt.execute("use LIBRARY")			
			
			stmt.execute(CreateTable_BOOK);
			stmt.execute(CreateTable_BOOK_AUTHORS);
			stmt.execute(CreateTable_AUTHORS);
			stmt.execute(CreateTable_BOOK_COPIES);
			stmt.execute(CreateTable_LIBRARY_BRANCH);
			stmt.execute(CreateTable_BORROWER);
			stmt.execute(CreateTable_BOOK_LOANS);
			stmt.execute(CreateTable_FINES);
*/			
/*			//import data
			List<String[]> BookCopies = DataImport.getData("book_copies.csv");
			List<String[]> BookLoans = DataImport.getData("book_loans.csv");
			List<String[]> Books = DataImport.getData("books.csv");
			List<String[]> Borrowers = DataImport.getData2("borrowers.csv");
			List<String[]> LibraryBranch = DataImport.getData("library_branch.csv");
*/			
			//map data
			//1__**************Mapping BOOK******************
/*			for(int i=1; i<Books.size(); i++){
				String bookTitle=modify(Books.get(i)[2]);
				String authors=modify(Books.get(i)[3]);
				String sql1="'"+Books.get(i)[0]+"'"+","+"'"+bookTitle+"'"+","+"'"+authors+"'";
			stmt.execute("insert into BOOK(Isbn,Title,Authors) values("+sql1+");");
			}
			
			//2__**************Mapping BOOK_COPIES************
			for(int i=1; i<BookCopies.size(); i++){
				String sql2="'"+BookCopies.get(i)[0] +"'"+","+"'"+BookCopies.get(i)[1] +"'"+","+"'"+ BookCopies.get(i)[2]+"'";
				stmt.execute("insert into BOOK_COPIES(Book_id,Branch_id,No_of_copies) values("+sql2+");");
				}
			
			//3__**************Mapping LIBRARY_BRANCH*********
			for(int i=1; i<LibraryBranch.size(); i++){
				String sql3="'"+LibraryBranch.get(i)[0]+"'"+","+"'"+LibraryBranch.get(i)[1]+"'"+","+"'"+LibraryBranch.get(i)[2]+"'";
				stmt.execute("insert into LIBRARY_BRANCH(Branch_id,Branch_name,Address) values("+sql3+");");
				}
			
			//4__**************Mapping BORROWER***************
			for(int i=1; i<Borrowers.size(); i++){
				String sql4="'"+Borrowers.get(i)[0]+"'"+","+"'"+Borrowers.get(i)[1]+"'"+","+"'"+Borrowers.get(i)[2]+"'"+","+"'"+Borrowers.get(i)[3]+"'"+","+"'"+Borrowers.get(i)[5]+","+Borrowers.get(i)[6]+","+Borrowers.get(i)[7]+"'"+","+"'"+Borrowers.get(i)[8]+"'";
				stmt.execute("insert into BORROWER(Card_no,Ssn,Fname,Lname,Address, Phone) values("+sql4+");");
				}
			System.out.println("Borrowers mapped successfully!");
			
			//5__**************Mapping BOOK_LOANS*************
			for(int i=0; i<BookLoans.size(); i++){
				String sql5="'"+BookLoans.get(i)[0]+"'"+","+"'"+BookLoans.get(i)[1]+"'"+","+"'"+BookLoans.get(i)[2]+"'"+","+"'"+BookLoans.get(i)[3]+"'"+","+"'"+BookLoans.get(i)[4]+"'"+","+"'"+BookLoans.get(i)[5]+"'"+","+"'"+BookLoans.get(i)[6]+"'";
				stmt.execute("insert into BOOK_LOANS(Loan_id,Isbn,Branch_id,Card_no,Date_out,Due_date,Date_in) values("+sql5+");");
				}
			System.out.println("BookLoans mapped successfully!");
			
			//6__***************Mapping FINES******************
			for(int i=0; i<BookLoans.size(); i++){
				String sql6="'"+BookLoans.get(i)[0]+"'"+","+"FALSE";
				stmt.execute("insert into Fines(Loan_id,PAID) values("+sql6+");");
				}
			System.out.println("Fines mapped successfully!");
			
			
			//7_*****************Mapping BOOK_AUTHORS and AUTHORS**************lol*****
			Map<String, String> nameMap=new HashMap<>();//nameMap<Name,ID>,using name as key
			Integer id_generatorI=0;
			
			for(int i = 1; i<Books.size(); i++)
			{
				//split author names into individual strings
				String[] namestemp=Books.get(i)[3].split("\\,");
				List<String> names=new ArrayList<>();
				String Isbn=Books.get(i)[0];
				
				for(int k=0;k<namestemp.length;k++)
				{
					if(!names.contains(namestemp[k]))
					{
						names.add(namestemp[k]);
					}
				}
				
				for(int j=0; j<names.size();j++){
					
					if(nameMap.containsKey(names.get(j)))//if the author name and id has been mapped, then mapping the author id and new Isbn directly
					{
						String author_id=nameMap.get(names.get(j));
						
						String sql7="'"+Isbn+"'"+","+"'"+author_id+"'";
						
						stmt.execute("insert into BOOK_AUTHORS(Isbn,Author_id) value("+sql7+");");
						
					}
					else//if the author name has not been shown before: 1) map name into nameMap, 2)Insert name into AUTHORS table, 3) Insert Isbn and author_id into BOOK_AUTHORS
					{
						//(1)
						String id_generatorS=formatString(id_generatorI);
						nameMap.put(names.get(j), id_generatorS);
						id_generatorI++;
						
						//(2)
						String fullName=names.get(j);
						List<String> nameComponents=NameSplit.nameSplit(fullName);
						String title=modify(nameComponents.get(0));
						String fname=modify(nameComponents.get(1));
						String mname=modify(nameComponents.get(2));
						String lname=modify(nameComponents.get(3));
						String suffix=modify(nameComponents.get(4));
						String fullname2=modify(fullName);
						
						
						String sql7="'"+id_generatorS+"'"+","+"'"+title+"'"+","+"'"+fname+"'"+","+"'"+mname+"'"+","+"'"+lname+"'"+","+"'"+fullname2+"'"+","+"'"+suffix+"'";
						String sql71="'"+Isbn+"'"+","+"'"+id_generatorS+"'";
						System.out.println(title+"  "+fname+"  "+mname+"  "+lname+" "+suffix);
						stmt.execute("insert into AUTHORS(Author_id,Title,Fname,Mname,Lname,Full_name,Suffix) value ("+sql7+");");
						stmt.execute("insert into BOOK_AUTHORS(Isbn,Author_id) value("+sql71+");");
					}
				}
			}
*/			
			
			
/*			//add constraints
			stmt.execute("alter table BOOK_AUTHORS add foreign key(Isbn) references BOOK(Isbn), add foreign key(Author_id) references AUTHORS(Author_id);");
			stmt.execute("alter table BOOK_COPIES add foreign key(Branch_id) references LIBRARY_BRANCH(Branch_id), add foreign key(Book_id) references BOOK(Isbn) ;");
			stmt.execute("alter table BOOK_LOANS add foreign key(Isbn) references BOOK(Isbn), add foreign key(Branch_id) references LIBRARY_BRANCH(Branch_id), add foreign key(Card_no) references BORROWER(Card_no) ;");
			stmt.execute("alter table FINES add foreign key(Loan_id) references BOOK_LOANS(Loan_id);");
*/			
/*			//update fine amount
			stmt.execute("update fines F, book_loans L set F.fine_amt = 0.25*datediff(L.date_in,L.Due_Date) where F.loan_id=L.Loan_id and L.due_date<L.date_in;");
			stmt.execute("update fines F, book_loans L set F.fine_amt = 0 where F.loan_id=L.Loan_id and L.due_date>=L.date_in;");
*/			
			
/*	Can also be used to update BORROWER		//map the number of books borrowed into borrower
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String date = format1.format(cal.getTime());
			
			stmt.execute("update BORROWER B set N_Book_Borrowed = 0;");
			System.out.println("1success");
			stmt.execute("update BORROWER B inner join ( select L.Card_no,L.Date_in, count(*) as num from  BOOK_LOANS L where L.Date_in>"+"'"+date+"'"+" group by L.Card_no ) J ON B.Card_no=J.Card_no set N_Book_Borrowed = num;");
*/			
			
/*			//map the number of books borrowed out of a library branch
			stmt.execute("update BOOK_COPIES set No_out=0;");
			System.out.println("0 mapped success");
			stmt.execute("update BOOK_COPIES C inner join ( select L.Isbn, L.Branch_id,count(*) as num from book_loans L where L.Date_in>'2016-03-17' group by L.Isbn, L.branch_id) J on (C.book_id=J.Isbn and C.Branch_id=J.Branch_id) set C.No_out=num;");
			System.out.println("No_out mapped success");
			stmt.execute("update BOOK_COPIES C set No_available=(No_of_copies - No_out);");
*/			
			
/*			List<String[]> Books = DataImport.getData("books.csv");
			//stmt.execute("alter table BOOK ADD COLUMN Authors VARCHAR(300) after Title;");
			for(int i=1; i<Books.size(); i++){
				String Isbn=modify(Books.get(i)[0]);
				String authors=modify(Books.get(i)[3]);
				String sql1="'"+authors+"'";
			stmt.execute("update BOOK set Authors="+sql1+"where Isbn="+"'"+Isbn+"'"+";");
			}
			conn.close();
			System.out.println("Success!");
*/			
			
		}
		catch(SQLException ex){System.out.println("Error in connextion attempt:"+ex.getMessage());}
	}
	
}

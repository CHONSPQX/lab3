package Action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Book;


public class BookDatabase {
	 	String drivename="com.mysql.jdbc.Driver";
	    String url="jdbc:mysql://localhost/test?useSSL=false";
	    String user="root";
	    String password="126459";
	    String insql;
	    String upsql;
	    String delsql;
	    String sql="select * from book";
	    Connection conn;
	    ResultSet rs=null;
	 
	    public Connection ConnectMysql()
	    {
			 try{
				Class.forName(drivename);
				conn = (Connection) DriverManager.getConnection(url, user, password);
				if (!conn.isClosed()) {
				    System.out.println("Succeeded connecting to the Database!");
				}else {
				    System.out.println("Falled connecting to the Database!");
				}
			}catch(Exception ex){
				    ex.printStackTrace();
				}
			 return conn;
	    }
	 
	    public void CutConnection(Connection conn) throws SQLException
	    {
	         try{
	            if(rs!=null);
	            if(conn!=null);
	         }catch(Exception e){
	         e.printStackTrace();
	         }finally{
	        rs.close();
	        conn.close();
	         }
	    }
	 
	 
	        

	         public boolean InsertSql(Book book){
	             try{
	 
	                  insql="insert into book(ISBN,Title,AuthorID,Publisher,PublishDate,Price) values(?,?,?,?,?,?)";
	                
	                 PreparedStatement ps=conn.prepareStatement(insql);
	                
	                 ps.setString(1, book.getIsbn());
	                 ps.setString(2, book.getTitle());
	                 ps.setString(3, book.getAuthorid());
	                 ps.setString(4, book.getPublisher());
	                 ps.setString(5, book.getPublishdate());
	                 ps.setFloat(6, book.getPrice());
	                 System.out.println(ps);
	                 int result=ps.executeUpdate();
	                 if(result>0)
	                     return true;
	             }catch(Exception e){
	                 e.printStackTrace();
	             }
	             return false;
	         }
	 
	 
	        
	         public ArrayList<Book> SelectSql(String sql){
	        	 	ArrayList<Book> books=new ArrayList<Book>();
	             try{
	                 Statement statement=conn.createStatement();
	                 rs=statement.executeQuery(sql);
	                 while(rs.next()){
	                	 String isbn=rs.getString("ISBN");
	                     String title=rs.getString("Title");
	                     String authorid=rs.getString("AuthorID");
	                     String publisher=rs.getString("Publisher");
	                     String publishdate=rs.getString("PublishDate");
	                     float price=rs.getFloat("Price");
	                     books.add(new Book(isbn,title,authorid,publisher,publishdate,price));
	                     System.out.println(isbn+" "+title+" "+authorid+" "+publisher+" "+publishdate+" "+price);
	                  }
	             }catch(Exception e){
	                 e.printStackTrace();
	             }
	             return books;
	         }
	 
	         public boolean UpdateSql(String upsql){
	        try {
	            PreparedStatement ps = conn.prepareStatement(upsql);
	            int result=ps.executeUpdate();
	            if(result>0)
	                return true;
	        } catch (SQLException ex) {
	            Logger.getLogger(BookDatabase.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return false;
	         }
	 
	         public boolean DeleteSql(String delsql){
	 
	        	 try {
	     			Statement ps = conn.createStatement();
	     			int result = ps.executeUpdate(delsql);
	     			if (result > 0)
	     				return true;
	     		} catch (SQLException ex) {
	     			Logger.getLogger(AuthorDatabase.class.getName()).log(Level.SEVERE, null, ex);
	     		}
	     		return false;
	         }
}

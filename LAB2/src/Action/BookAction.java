package Action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import Model.Author;
import Model.Book;

public class BookAction extends ActionSupport
{
	
	private Book book;
	static BookDatabase bookdatabase= new BookDatabase();
	private Map request;
	private Map session;
	private Map application;
	
	public BookAction()
	{
		request=(Map)ActionContext.getContext().get("request");
		session=(Map)ActionContext.getContext().getSession();
		application=(Map)ActionContext.getContext().getApplication();
	}
	public String Book_create() throws Exception 
	{
		System.out.println(book.isbn+" "+book.authorid+" "+book.title+" "+
	book.publisher+" "+book.publishdate+" "+book.price);
		bookdatabase.ConnectMysql();
		boolean flag=bookdatabase.InsertSql(book);
		request.put("book", book);
		if(flag)
		{
			this.addFieldError("book_create_message", "book_create_success");
			return "book_create_success";
		}
		else
		{
			this.addFieldError("book_create_message", "book_create_error ");
			return "book_create_error";
		}
    }
	
	public String Book_retrieve() throws Exception {
		System.out.println(book.isbn+" "+book.authorid+" "+book.title+" "+book.publisher+" "+book.publishdate+" "+book.price);
		bookdatabase.ConnectMysql();
	
		String sqlSearchItem = "select * from test.book where ISBN='"+book.isbn+"' and Title='"+book.title
				+"' and AuthorID='"+book.authorid+"' and Publisher='"+book.publisher
				+"' and PublishDate='"+book.publishdate+"' and Price='"+book.price+"'";
		ArrayList<Book> result=bookdatabase.SelectSql(sqlSearchItem);
		request.put("result", result);
		if(!result.isEmpty())
		{
			this.addFieldError("book_retrieve_message", "book_retrieve_success");
			return "book_retrieve_success";
		}
		else
		{
			this.addFieldError("book_retrieve_message", "book_retrieve_error");
			return "book_retrieve_error";
		}
    }
	
	
	public String Book_update() throws Exception {
		System.out.println(book.isbn+" "+book.authorid+" "+book.title+" "+book.publisher+" "
	+book.publishdate+" "+book.price);
		
		bookdatabase.ConnectMysql();
		String sqlUpdateItem = "update test.book set Title='"+book.title+"',AuthorID='"+book.authorid+
				"',Publisher='"+book.publisher+"',PublishDate='"+book.publishdate+
				"',Price='"+book.price+"' where ISBN='"+book.isbn+"'";
		boolean flag=bookdatabase.UpdateSql(sqlUpdateItem);
		if(flag)
		{
			this.addFieldError("book_update_message", "book_update_success");
			return "book_update_success";
		}
		else
		{
			this.addFieldError("book_update_message", "book_update_error");
			return "book_update_error";
		}
    }
	
	public String Book_delete() throws Exception {
		System.out.println(book.isbn+" "+book.authorid+" "+book.title+" "+
	book.publisher+" "+book.publishdate+" "+book.price);
		bookdatabase.ConnectMysql();
		String sqlDeleteItem = "delete from test.book where ISBN='"
		+book.isbn+"' and Title='"+book.title
		+"' and AuthorID='"+book.authorid+"' and Publisher='"+book.publisher
		+"' and PublishDate='"+book.publishdate+"' and Price='"+book.price+"'";
		boolean flag=bookdatabase.DeleteSql(sqlDeleteItem);
		if(flag)
		{
			this.addFieldError("book_delete_message", "book_delete_success");
			return "book_delete_success";
		}
		else
		{
			this.addFieldError("book_delete_message", "book_delete_error");
			return "book_delete_error";
		}
    }
		
	public String execute() throws Exception {
		System.out.println(book.isbn+" "+book.authorid+" "+book.title+" "+book.publisher+" "+book.publishdate+" "+book.price);
		BookDatabase bd=new BookDatabase();
		bd.ConnectMysql();
		boolean flag=bd.InsertSql(book);
		if(flag)
			return SUCCESS;
		else
			return ERROR;
    }
	public void setBook(Book book)
	{
		this.book=book;
	}
	public Book getBook()
	{
		return book;
	}
	
}
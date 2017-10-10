package Action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import Model.Author;
import Model.Book;
import tool.Tool;

public class ListAction extends ActionSupport
{
	
	private String authorname;
	private String isbn;
	private String authorid;
	static BookDatabase bookdatabase= new BookDatabase();
	static AuthorDatabase authordatabase=new AuthorDatabase();
	private Map request;
	private Map session;
	private Map application;
	
	public ListAction()
	{
		request=(Map)ActionContext.getContext().get("request");
		session=(Map)ActionContext.getContext().getSession();
		application=(Map)ActionContext.getContext().getApplication();		
	}
	
	public String List_retrieve() throws Exception {
		bookdatabase.ConnectMysql();
		authordatabase.ConnectMysql();
		String sqlSearchItem = "select * from test.author where Name='"+authorname+"'";
		ArrayList<Author> authorresult=authordatabase.SelectSql(sqlSearchItem);
		ArrayList<Book>  bookresult=new ArrayList<>();
		if(!authorresult.isEmpty())
		{
			String temp=null;
			ArrayList<Book> templist=null;
			for(Author au:authorresult)
			{
				temp="select * from test.book where AuthorID='"+au.authorid+"'";
				templist=bookdatabase.SelectSql(temp);
				bookresult.addAll(templist);
			}
		}
		request.put("authorresult", authorresult);
		request.put("bookresult", bookresult);
		if(authorresult.isEmpty()||bookresult.isEmpty())
		{
			this.addFieldError("list_retrieve_message", "list_retrieve_error");
			return "list_retrieve_error";	
		}
		else
		{
			this.addFieldError("list_retrieve_message", "list_retrieve_success");
			return "list_retrieve_success";
		}
    }
	
	public String Info_retrieve() throws Exception {
		bookdatabase.ConnectMysql();
		authordatabase.ConnectMysql();
		String authorSearchItem = "select * from test.author where AuthorID='"+authorid+"'";
		ArrayList<Author> authorresult=authordatabase.SelectSql(authorSearchItem);
		String bookSearchItem = "select * from test.book where ISBN='"+isbn+"'";
		ArrayList<Book> bookresult=bookdatabase.SelectSql(bookSearchItem);
		request.put("authorresult", authorresult);
		request.put("bookresult", bookresult);
		if(authorresult.isEmpty()||bookresult.isEmpty())
		{
			this.addFieldError("info_retrieve_message", "info_retrieve_error");
			return "info_retrieve_error";	
		}
		else
		{
			this.addFieldError("info_retrieve_message", "info_retrieve_success");
			return "info_retrieve_success";
		}
    }
	
	
		
	public void setAuthorname(String authorname)
	{
		//authorname=Tool.Toutf(authorname);
		this.authorname=authorname;
	}
	public String getAuthorname()
	{
		return this.authorname;
	}
	public void setIsbn(String isbn)
	{
		//authorname=Tool.Toutf(authorname);
		this.isbn=isbn;
	}
	public String getIsbn()
	{
		return this.isbn;
	}
	public void setAuthorid(String authorid)
	{
		//authorname=Tool.Toutf(authorname);
		this.authorid=authorid;
	}
	public String getAuthorid()
	{
		return this.authorid;
	}
	
}
package Action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import Model.Author;


public class AuthorAction extends ActionSupport
{
	static AuthorDatabase authordatabase= new AuthorDatabase();
	private Author author;
	private Map request;
	private Map session;
	private Map application;
	
	public AuthorAction()
	{
		request=(Map)ActionContext.getContext().get("request");
		session=(Map)ActionContext.getContext().getSession();
		application=(Map)ActionContext.getContext().getApplication();
	}
	public String Author_create() throws Exception 
	{
		System.out.println(author.authorid+" "+author.name+" "+author.age+" "+author.country);
		authordatabase.ConnectMysql();
		boolean flag=authordatabase.InsertSql(author);
		request.put("author", author);
		if(flag)
		{
			this.addFieldError("author_create_message", "author_create_success");
			return "author_create_success";
		}
		else
		{
			this.addFieldError("author_create_message", "author_create_error ");
			return "author_create_error";
		}
    }
	
	public String Author_retrieve() throws Exception {
		System.out.println(author.authorid+" "+author.name+" "+author.age+" "+author.country);
		authordatabase.ConnectMysql();
	
		String sqlSearchItem = "select * from test.author where AuthorID='"+author.authorid+"' and Name='"+author.name
				+"' and Age='"+author.age+"' and Country='"+author.country
				+"'";
		ArrayList<Author> result=authordatabase.SelectSql(sqlSearchItem);
		request.put("result", result);
		if(!result.isEmpty())
		{
			this.addFieldError("author_retrieve_message", "author_retrieve_success");
			return "author_retrieve_success";
		}
		else
		{
			this.addFieldError("author_retrieve_message", "author_retrieve_error");
			return "author_retrieve_error";
		}
    }
	
	
	public String Author_update() throws Exception {
		System.out.println(author.authorid+" "+author.name+" "+author.age+" "+author.country);
		authordatabase.ConnectMysql();
		String sqlUpdateItem = "update test.author set Name='"+author.name+"',Age='"+author.age+
				"',Country='"+author.country+"' where AuthorID='"+author.authorid+"'";
		boolean flag=authordatabase.UpdateSql(sqlUpdateItem);
		if(flag)
		{
			this.addFieldError("author_update_message", "author_update_success");
			return "author_update_success";
		}
		else
		{
			this.addFieldError("author_update_message", "author_update_error");
			return "author_update_error";
		}
    }
	
	public String Author_delete() throws Exception {
		System.out.println(author.authorid+" "+author.name+" "+author.age+" "+author.country);
		authordatabase.ConnectMysql();
		String sqlDeleteItem = "delete from test.author where AuthorID='"+author.authorid+
				"' and Name='"+author.name+"' and Age='"+author.age+"' and Country='"+author.country+"'";
		boolean flag=authordatabase.DeleteSql(sqlDeleteItem);
		if(flag)
		{
			this.addFieldError("author_delete_message", "author_delete_success");
			return "author_delete_success";
		}
		else
		{
			this.addFieldError("author_delete_message", "author_delete_error");
			return "author_delete_error";
		}
    }
		
	
	public String execute() throws Exception {
		System.out.println(author.authorid+" "+author.name+" "+author.age+" "+author.country);
		authordatabase.ConnectMysql();
		boolean flag=authordatabase.InsertSql(author);
		if(flag)
			return SUCCESS;
		else
			return ERROR;
    }
	
	public void setAuthor(Author author)
	{
		this.author=author;
	}
	public Author getAuthor()
	{
		return author;
	}
}
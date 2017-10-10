package Action;

import java.util.Map;
import tool.Tool;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AjaxCheck extends ActionSupport{
    private String result;
    private String isbn;
    public void setIsbn(String isbn)
    {
    	isbn=Tool.Toutf(isbn);
    	this.isbn=isbn;
    }
    public String getIsbn()
    {
    	return this.isbn;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String Check_isbn(){
        BookDatabase bd=new BookDatabase();
        bd.ConnectMysql();
        HttpServletRequest requests = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);  
        String isbn=(String)requests.getParameter("book.isbn");
        isbn=Tool.Toutf(isbn);
        System.out.println(isbn);
        String sql="select * from test.book where ISBN=\""+isbn+"\"";
        if(bd.SelectSql(sql).isEmpty()){
        	result="0";
        	return SUCCESS;
        }
        else{
            result="1";
            return SUCCESS;
        }
    }
   
    public String Check_authorid(){
        AuthorDatabase ad=new AuthorDatabase();
        ad.ConnectMysql();
        HttpServletRequest requests = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);  
        String authorids=(String)requests.getParameter("book.authorid");
        authorids=Tool.Toutf(authorids);
        System.out.println(authorids);
        if(authorids==null)
        {
        	result="0";
        	return SUCCESS;
        }
        String sql="select * from test.author where AuthorID=\""+authorids+"\"";
        if(ad.SelectSql(sql).isEmpty()){
        	result="0";
        	return SUCCESS;
        }
        else{
            result="1";
            return SUCCESS;
        }
    }
    public String Check_authorids(){
        AuthorDatabase ad=new AuthorDatabase();
        ad.ConnectMysql();
        HttpServletRequest requests = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);  
        String authorid=(String)requests.getParameter("author.authorid");
        authorid=Tool.Toutf(authorid);
        System.out.println(authorid);
        if(authorid==null||authorid.equals(""))
        {
        	result="0";
        	return SUCCESS;
        }
        String sql="select * from test.author where AuthorID=\""+authorid+"\"";
        System.out.println(sql);
        if(ad.SelectSql(sql).isEmpty()){
        	result="1";
        	return SUCCESS;
        }
        else{
            result="0";
            return SUCCESS;
        }
    }
    
    public String Delete_isbn(){
        BookDatabase bd=new BookDatabase();
        bd.ConnectMysql();
        HttpServletRequest requests = (HttpServletRequest)ActionContext.
        		getContext().get(ServletActionContext.HTTP_REQUEST);  
        System.out.println(isbn);
        String sql="delete from test.book where ISBN=\""+this.isbn+"\"";
        if(bd.DeleteSql(sql)){
        	result="1";
        	return SUCCESS;
        }
        else{
            result="0";
            return SUCCESS;
        }
    }
    
    public String execute(){     
       	return SUCCESS;
    }
}
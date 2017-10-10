package Action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import Model.Admin;


public class AdminAction extends ActionSupport
{
	static AdminDatabase admindatabase= new AdminDatabase();
	private Admin admin;

	private Map request;
	private Map session;
	private Map application;
	
	public AdminAction()
	{
		request=(Map)ActionContext.getContext().get("request");
		session=(Map)ActionContext.getContext().getSession();
		application=(Map)ActionContext.getContext().getApplication();
	}
	public String Admin_create() throws Exception 
	{
		admindatabase.ConnectMysql();
		boolean flag=admindatabase.InsertSql(admin);
		if(flag)
		{
			this.addFieldError("admin_create_message", "admin_create_success");
			return "admin_create_success";
		}
		else
		{
			this.addFieldError("admin_create_message", "admin_create_error ");
			return "admin_create_error";
		}
    }
	
	public String Admin_check() throws Exception 
	{
		admindatabase.ConnectMysql();
		String sqlSearchItem = "select * from test.admin where name='"+admin.username+"' and pwd='"+admin.userpwd+"'";
		System.out.println(sqlSearchItem);
		boolean flag=admindatabase.SelectSql(sqlSearchItem);
		System.out.println(flag);
		if(flag)
		{
			this.addFieldError("admin_check_message", "admin_check_success");
			request.put("admin_check_message", "admin_check_success");
			return "admin_check_success";
		}
		else
		{
			this.addFieldError("admin_check_message", "admin_check_error ");
			request.put("admin_check_message", "admin_check_error");
			return "admin_check_error";
		}
    }
	
	
	
	public String Admin_update() throws Exception {
		admindatabase.ConnectMysql();
		String sqlUpdateItem = "update test.admin set pwd='"+admin.userpwd+"' where name='"+admin.username+"'";
		boolean flag=admindatabase.UpdateSql(sqlUpdateItem);
		if(flag)
		{
			this.addFieldError("admin_update_message", "admin_update_success");
			return "admin_update_success";
		}
		else
		{
			this.addFieldError("admin_update_message", "admin_update_error");
			return "admin_update_error";
		}
    }
	
	public String Admin_delete() throws Exception {
		admindatabase.ConnectMysql();
		String sqlDeleteItem = "delete from test.admin where pwd='"+admin.userpwd+"' and name='"+admin.username+"'";
		boolean flag=admindatabase.DeleteSql(sqlDeleteItem);
		if(flag)
		{
			this.addFieldError("admin_delete_message", "admin_delete_success");
			return "admin_delete_success";
		}
		else
		{
			this.addFieldError("admin_delete_message", "admin_delete_error");
			return "admin_delete_error";
		}
    }
		
	public void setAdmin(Admin admin)
	{
		this.admin=admin;
	}
	public Admin getAdmin()
	{
		return admin;
	}
}
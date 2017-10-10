package Model;

public class Admin {

	public String username;
	public String userpwd;
	
	public Admin()
	{
		
	}
	public Admin(String username, String userpwd)
	{
		this.username=username;
		this.userpwd=userpwd;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getUserpwd()
	{
		return this.userpwd;
	}
	
	public void setUsername(String username)
	{
		this.username=username;
	}
	public void setUserpwd(String userpwd) 
	{
		this.userpwd=userpwd;
	}
	
}

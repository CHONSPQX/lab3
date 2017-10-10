package Model;

import tool.Tool;

public class Author{
    public String authorid;
    public String name;
    public int age;
    public String country;

   
    
    public Author(String authorid,String name,int age,String country) {
    	this.authorid=authorid;
    	this.name=name;
    	this.age=age;
    	this.country=country;
		// TODO Auto-generated constructor stub
	}
    public Author()
    {
    	
    }
    public void setAuthorid(String authorid){
    	//authorid=Tool.Toutf(authorid);
        this.authorid=authorid;
    }
    public void setName(String name) {
    	//name=Tool.Toutf(name);
        this.name=name;
    }
    public void setCountry(String country){
    	//country=Tool.Toutf(country);
        this.country=country;
    }
    public void setAge(int age){
        this.age=age;
    }
    public Integer getAge(){
        return this.age;
    }
    public String getName(){
        return this.name;
    }
    public String getCountry(){
        return this.country;
    }
    public String getAuthorid(){
        return this.authorid;
    }

}
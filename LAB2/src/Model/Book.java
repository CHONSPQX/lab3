package Model;
import tool.Tool;
public class Book{
    public String isbn;
    public String title;
    public String authorid;
    public String publisher;
    public String publishdate;
    public float price;
    
    public Book()
    {
    	
    }
    public Book(String ISBN, String Title, String AuthorID,String Publisher,String PublishDate,float Price)
    {
    	//ISBN=Tool.Toutf(ISBN);
    	//Title=Tool.Toutf(Title);
    	//AuthorID=Tool.Toutf(AuthorID);
    	//Publisher=Tool.Toutf(Publisher);
    	//PublishDate=Tool.Toutf(PublishDate);
    	this.isbn=ISBN;
    	this.title=Title;
    	this.authorid=AuthorID;
    	this.publisher=Publisher;
    	this.publishdate=PublishDate;
    	this.price=Price;
    }
   
    public void setIsbn(String isbn){
    	//isbn=Tool.Toutf(isbn);
        this.isbn=isbn;
    }
    public void setTitle(String title){
    	//title=Tool.Toutf(title);
        this.title=title;
    }
    public void setAuthorid(String authorid){
    	//authorid=Tool.Toutf(authorid);
        this.authorid=authorid;
    }
    public void setPublisher(String publisher){
    	//publisher=Tool.Toutf(publisher);
        this.publisher=publisher;
    }
    public void setPublishdate(String publishdate){
    	//publishdate=Tool.Toutf(publishdate);
        this.publishdate=publishdate;
    }
    public void setPrice(float price){
        this.price=price;
    }
    
    public String getIsbn(){
        return this.isbn;
    }
    public String getTitle(){
   	 return this.title;
    }
    public String getAuthorid(){
   	 return this.authorid;
    }
    public String getPublisher(){
   	 return this.publisher;
    }
    public String getPublishdate(){
   	 return this.publishdate;
    }
    public float getPrice(){
   	 return this.price;
    }
    
}

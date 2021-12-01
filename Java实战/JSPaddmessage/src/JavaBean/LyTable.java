package JavaBean;
import java.sql.Date;

public class LyTable {
	private Integer id;
    private Integer userID;
    private Date date;
    private String title;
    private String content;
    //get set ID
    public Integer getID(){
    	return this.id;
    }
    public void setId(Integer id){
    	this.id=id;
    }
    //get set userID
    public Integer getUserID(){
    	return this.userID;
    }
    public void setUserID(Integer userID){
    	this.userID=userID;
    }
    //get set date
    public Date getDate(){
    	return this.date;
    }
    public void setDate(Date date){
    	this.date=date;
    }
  //get set title
    public String getTitle(){
    	return this.title;
    }
    public void setTitle(String title){
    	this.title=title;
    }
  //get set content
    public String getContent(){
    	return this.content;
    }
    public void setContent(String content){
    	this.content=content;
    }
}

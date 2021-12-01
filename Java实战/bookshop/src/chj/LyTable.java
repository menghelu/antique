package chj;
import java.sql.Date;
public class LyTable {

	private String username;
    private String  date;
    private String title;
    private String content;
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
    public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle(){
    	return this.title;
    }
    public void setTitle(String title){
    	this.title=title;
    }

    public String getContent(){
    	return this.content;
    }
    public void setContent(String content){
    	this.content=content;
    }
}


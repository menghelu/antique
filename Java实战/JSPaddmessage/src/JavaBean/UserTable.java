package JavaBean;

public class UserTable {
    private Integer id;
    private String username;
    private String password;
    //get set ID
    public Integer getID(){
    	return this.id;
    }
    public void setId(Integer id){
    	this.id=id;
    }
    //get set username
    public String getUsername(){
    	return this.username;
    }
    public void setUsername(String username){
    	this.username=username;
    }
    //get set password
    public String getPassword(){
    	return this.password;
    }
    public void setPassword(String password){
    	this.password=password;
    }
}

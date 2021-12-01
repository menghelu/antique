package yanshuo;

public class UserDataBean {
    private String username;
    private String password;
    private String realname;
    private String sex;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public UserDataBean(String u,String p,String r,String s ){
		username=u;
		password=p;
		realname=r;
		sex=s;
	}
	public UserDataBean(){}
}

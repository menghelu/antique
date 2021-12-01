package yanshuo;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class loginAction {

	private String username;
	private String password;
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
	public String execute(){
		Map<String,Object> application = ActionContext.getContext().getApplication();
		UserManagerBean userManager = (UserManagerBean)application.get("userManager");
		if(userManager==null){
			userManager = new UserManagerBean();
			application.put("userManager", userManager);
		}
		
		UserBean userbean = userManager.Login(username, password);
		if(userbean!=null){
			Map<String,Object> session = ActionContext.getContext().getSession();
			session.put("user",userbean);
			return "success";
		}else{
			return "error";
		}
			
	}
}

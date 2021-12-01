package yanshuo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UserManagerBean {
	private List<UserDataBean> userList;

	public List<UserDataBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDataBean> userList) {
		this.userList = userList;
	}
	
	public UserManagerBean(){
		userList = new LinkedList<UserDataBean>();
		userList.add(new UserDataBean("sa","sa","С��","��","����Ա"));
		userList.add(new UserDataBean("test","abc123","С��","Ů","�û�"));
		userList.add(new UserDataBean("guest","guest","С��","��","�û�"));
	}
	
	public UserBean login(String username,String password){
		UserBean userBean = null;
		Iterator<UserDataBean> iter = userList.iterator();
		while(iter.hasNext()){
			UserDataBean user = iter.next();
			if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
				userBean = new UserBean();
				userBean.setUsername(username);
				userBean.setRealname(user.getRealname());
				userBean.setSex(user.getSex());
				userBean.setRole(user.getRole());
				break;
			}
		}
		return userBean;
	}
}

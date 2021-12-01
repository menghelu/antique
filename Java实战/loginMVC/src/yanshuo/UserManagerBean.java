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
		userList=new LinkedList<UserDataBean>();
	    userList.add(new UserDataBean("sa","sa","管理员","男"));
	    userList.add(new UserDataBean("test","abc123","测试员","女"));
	}
	
	public UserBean Login(String username,String password){
		UserBean userBean =null;
		Iterator<UserDataBean> iter=userList.iterator();
		while(iter.hasNext()){
			UserDataBean user=iter.next();
			if(user.getUsername().equals(username)){
				if(user.getPassword().equals(password)){
					userBean=new UserBean();
					userBean.setUsername(username);
					userBean.setRealname(user.getRealname());
					userBean.setSex(user.getSex());
				}
				break;
			}
		}
		return userBean;
	}
}

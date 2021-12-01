package wjy;

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
	public UserManagerBean()
	{
		userList=new LinkedList<UserDataBean>();
		userList.add(new UserDataBean("sa","sa","小明","男","管理员"));
		userList.add(new UserDataBean("test","abc123","小红","女","用户"));
		userList.add(new UserDataBean("guest","guest","小强","男","用户"));
	}
	
	public UserBean login(String username,String password)
	{
		UserBean userBean=null;
		Iterator<UserDataBean> iter=userList.iterator();
		while(iter.hasNext())
		{
			UserDataBean user=iter.next();
			if(user.getUsername().equals(username) )
			{
					if(user.getPassword().equals(password))
					{
				userBean=new UserBean();
				userBean.setUsername(username);
				userBean.setRealname(user.getRealname());
				userBean.setSex(user.getSex());
				userBean.setRole(user.getRole());
				
					}
					break;
			}
		}return userBean;
	}
	
	public void delete(int id)
	{
		Iterator<UserDataBean> iter=userList.iterator();
		while(iter.hasNext())
		{
			UserDataBean user = iter.next();
			if(user.getId()==id)
			{
				iter.remove();
				break;
			}
		}
	}
	public boolean isUsernameExist(String username)
	{
		Iterator<UserDataBean> iter = userList.iterator();
		while(iter.hasNext())
		{
			UserDataBean user=iter.next();
			if(user.getUsername().equals(username)) return true;
		}
		return false;
	}
	public boolean add(UserDataBean user)
	{
		if(isUsernameExist(user.getUsername())) return false;
		userList.add(user);
		return true;
	}
	
	public UserDataBean get(int id)
	{
		Iterator<UserDataBean> iter = userList.iterator();
		while(iter.hasNext())
		{
			UserDataBean user=iter.next();
			if(user.getId()==id)
			{return user;}
		}
		return null;
		
	}
	
	public UserDataBean get(String username)
	{
		Iterator<UserDataBean> iter = userList.iterator();
		while(iter.hasNext())
		{
			UserDataBean user=iter.next();
			if(user.getUsername().equals(username))
			{return user;}
		}
		return null;
		
	}
	public void modify(UserDataBean data)
	{
		UserDataBean user=get(data.getUsername());
		user.setPassword(data.getPassword());
		user.setRealname(data.getRealname());
		user.setSex(data.getSex());
		user.setRole(data.getRole());
	}
	
	public List<UserDataBean> query(QueryCondition cond)
	{
		List<UserDataBean> result = new LinkedList<UserDataBean>();
		
		Iterator<UserDataBean> iter = userList.iterator();
		while(iter.hasNext())
		{
			UserDataBean user = iter.next();
			if(cond.satisfy(user))result.add(user);
		}
		
		return result;
	}
	
}

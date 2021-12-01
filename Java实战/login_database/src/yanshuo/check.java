package yanshuo;

public class check {

	public boolean checkuser(String username,String password){
		index in=new index();
		user users=in.indexbyname(username);
		if(users!=null&&password.equals(users.getPassword()))
		  return true;
		else
		  return false;
}
	public boolean checkuser_bool(String username){
		index in=new index();
		user users=in.indexbyname(username);
		String pp=users.getPassword();
		if(pp==null)
		  return true;
		else
		  return false;
}
}

package yanshuo;

public class check {

	public boolean checkuser(String id,String password){
		index in=new index();
		user users=in.indexbyname(id);
		if(users!=null&&password.equals(users.getPassword()))
		  return true;
		else
		  return false;
}
	public boolean checkuser_bool(String id){
		index in=new index();
		user users=in.indexbyname(id);
		String pp=users.getPassword();
		if(pp==null)
		  return true;
		else
		  return false;
}
}

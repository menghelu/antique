package yanshuo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class UserManager {
      List<User>userList;
   
      public UserManager(){
    	  userList=new LinkedList<User>();
    	  userList.add(new User("test","abc123"));
    	  userList.add(new User("guest","abcde"));
    	  userList.add(new User("sa","sa"));
      }
      public int login(String username,String password){
    	  Iterator<User> iter=userList.iterator();	
    	  while(iter.hasNext()){
    		  User user=iter.next();
    		  if(user.username.equals(username)){
    			  if(user.password.equals(password))
    				  return 0;
    			  else
    				  return 1;
    		  }
    	  }
    	  return 2;
      }
      public List<User> getUserList(){
    	  return userList;
      }
      }


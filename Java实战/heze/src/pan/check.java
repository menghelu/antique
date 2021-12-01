package pan;

import java.sql.*;

public class check {
	
	public boolean checkuser(String username,String password){
		String sql="select * from users where username=?";
		Connection conn=database.get_connection();
		ResultSet rs=null;
		userbean users=new userbean();
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			rs=ps.executeQuery();
			if(rs.next()){
				users.setUsername(rs.getString(1));
				users.setPassword(rs.getString(2));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn,null,rs);
		}
		if(users!=null&&password.equals(users.getPassword()))
		  return true;
		else
		  return false;
}
	public boolean checkuser_bool(String username){
		String sql="select * from users where username=?";
		Connection conn=database.get_connection();
		ResultSet rs=null;
		userbean users=new userbean();
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			rs=ps.executeQuery();
			if(rs.next()){
				users.setUsername(rs.getString(1));
				users.setPassword(rs.getString(2));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn,null,rs);
		}
		String pp=users.getPassword();
		if(pp==null)
		  return true;
		else
		  return false;
}
}

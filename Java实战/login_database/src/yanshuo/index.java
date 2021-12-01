package yanshuo;

import java.sql.*;

public class index {

	public user indexbyname(String username){
		String sql="select * from users where username=?";
		//编写sql语句
		Connection conn=database.get_connection();
		//获得连接
		ResultSet rs=null;
		user users=new user();
		//System.out.println(username);
		//这里还OK
		try{
			//System.out.println(username);
			//这里还OK
			PreparedStatement ps=conn.prepareStatement(sql);
			//用来发送sql语句
			//System.out.println(username);
			//这里就不行了
			ps.setString(1,username);
			//设置要传入的参数
			//System.out.println(username);
			//这里不行
			rs=ps.executeQuery();
			//执行sql语句
			if(rs.next()){
				//如果能找到结果，就把找到的结果传到user对象中
				users.setUsername(rs.getString(1));
				users.setPassword(rs.getString(2));
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getString(2));
		    // 这里有bug,ok啦
			}
  	  //	    else{
      //		System.out.println(username);
	  //	}
	  //   这里也不行
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn,null,rs);
			//关闭连接
		}
		return users;
	}
	/*public static void main(String[] args){
		index in=new index();
		user stu=in.indexbyname("tianzhimin");
		System.out.println(stu.getPassword());
	}*/
}

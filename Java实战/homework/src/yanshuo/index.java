package yanshuo;

import java.sql.*;

public class index {

	public user indexbyname(String id){
		String sql="select * from users where id=?";
		Connection conn=database.get_connection();
		ResultSet rs=null;
		user users=new user();
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			rs=ps.executeQuery();
			if(rs.next()){
				users.setId(rs.getString(1));
				users.setPassword(rs.getString(3));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn,null,rs);
		}
		return users;
	}
}

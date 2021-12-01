package yanshuo;

import java.sql.*;

public class register_sql {
	public void aha(String username,String password){
		String sql="insert into users(username,password) values(?,?)";
		Connection conn=database.get_connection();
		ResultSet rs=null;
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,password);
			rs=ps.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn,null,rs);
		}
	}
}

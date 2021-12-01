package yanshuo;

import java.sql.*;

public class database {

	private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//加载jdbc驱动
	private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=library";
	//数据库连接地址
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "yan1691829128";
	public static Connection get_connection(){
		//Connection是sql包里的一个类型，和int类似的那种
		try{
			 Class.forName(DRIVER_NAME);
			//加载sql的驱动类
			// System.out.println("Connection Successful!"); 
			return DriverManager.getConnection(URL,USERNAME,PASSWORD);
			//获取数据库连接
		}catch (Exception e){
			e.printStackTrace();
			return null;
	}
}
	 public static void closeConn(Connection conn,Statement stm , ResultSet rs ){
		 //关闭jdbc资源，都这样写
	        if(stm!=null){
	            try {
	                stm.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	 
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if(rs!=null){
	            try{
	                rs.close();
	            }catch(SQLException e){
	                e.printStackTrace();
	            }
	        }
	    }
	 //public static void main(String[] args){
	//        System.out.println(get_connection());
	//   }
}
package yanshuo;

import java.sql.*;

public class database {

	private static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//����jdbc����
	private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=library";
	//���ݿ����ӵ�ַ
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "yan1691829128";
	public static Connection get_connection(){
		//Connection��sql�����һ�����ͣ���int���Ƶ�����
		try{
			 Class.forName(DRIVER_NAME);
			//����sql��������
			// System.out.println("Connection Successful!"); 
			return DriverManager.getConnection(URL,USERNAME,PASSWORD);
			//��ȡ���ݿ�����
		}catch (Exception e){
			e.printStackTrace();
			return null;
	}
}
	 public static void closeConn(Connection conn,Statement stm , ResultSet rs ){
		 //�ر�jdbc��Դ��������д
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
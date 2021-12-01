package JDBC1;
import java.sql.*;

public class SqlSrvDBConn {
    private Statement stmt;
    private Connection conn;
    ResultSet rs;
    public SqlSrvDBConn(){
       stmt=null;
 	   try{
 		   /**���ز�ע��SQL Server2008��JDBC����*/
 		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 		   /**��д�����ַ�������ȡ��������*/
 		   conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TEST","sa","ngcong1212");
 	   }catch(Exception e){
 		   e.printStackTrace();
 	   }
 	   rs=null;
    }
    //��ȡ���ݿ�����
    public Connection getConn(){
    	return this.conn;
    }
    //ִ�в�ѯ���SQL��䣬�з��ؼ�
    public ResultSet executeQuery(String sql){
    	try{
 		   stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
 		   rs=stmt.executeQuery(sql);      //ִ�в�ѯ���
 	   }catch(Exception e){
 		   System.err.println("Data.executeQuery:"+e.getMessage());
 	   }
 	   return rs;                          //���ؽ����
    }
    //�رն���
    public void closeStmt(){
    	try{
 		   stmt.close();
 	   }catch(SQLException e){
 		   System.err.println("Data.executeQuery:"+e.getMessage());
 	   }
    }
    public void closeConn(){
    	try{
 		   conn.close();
 	   }catch(SQLException e){
 		   System.err.println("Data.executeQuery:"+e.getMessage());
 	   }
    }
}

package JDBC1;
import java.sql.*;

public class SqlSrvDBConn {
    private Statement stmt;
    private Connection conn;
    ResultSet rs;
    public SqlSrvDBConn(){
       stmt=null;
 	   try{
 		   /**加载并注册SQL Server2008的JDBC驱动*/
 		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 		   /**编写连续字符串，获取创建连接*/
 		   conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TEST","sa","ngcong1212");
 	   }catch(Exception e){
 		   e.printStackTrace();
 	   }
 	   rs=null;
    }
    //获取数据库连接
    public Connection getConn(){
    	return this.conn;
    }
    //执行查询类的SQL语句，有返回集
    public ResultSet executeQuery(String sql){
    	try{
 		   stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
 		   rs=stmt.executeQuery(sql);      //执行查询语句
 	   }catch(Exception e){
 		   System.err.println("Data.executeQuery:"+e.getMessage());
 	   }
 	   return rs;                          //返回结果集
    }
    //关闭对象
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

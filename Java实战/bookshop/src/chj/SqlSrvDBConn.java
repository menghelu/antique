 package chj;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SqlSrvDBConn {
	   private Statement stmt;
    private Connection conn;
    ResultSet rs;
    public SqlSrvDBConn(){
 	   stmt=null;
 	   try{
 		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 		   conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=bookshop","sa","123456");
 	   }catch(Exception e){
 		   e.printStackTrace();
 	   }
    rs=null;
    }
    //获取数据连接
    public Connection getConn(){
 	   return this.conn;
    }
    public ResultSet executeQuery(String sql)
    {
 	   try{
 		   stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
 		   rs=stmt.executeQuery(sql);
 	   }catch(SQLException e){
 		   System.err.println("Data.executeQuery:"+e.getMessage());
 	   }
 	   return rs;
    }
   public void closeStmt()
   {
 	  try{
 		  stmt.close();
 	  }catch(SQLException e){
 		  System.err.println("Data.executeQuery:"+e.getMessage());
 	  }
   }
   public void closeConn()
   {
 	  try{
 		  conn.close();
 	  }catch(SQLException e){
 		  System.err.println("Data.executeQuery:"+e.getMessage());
 	  }
   }
}
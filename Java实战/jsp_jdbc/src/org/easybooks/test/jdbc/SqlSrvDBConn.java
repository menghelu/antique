package org.easybooks.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlSrvDBConn {
	private Statement stmt;
	private Connection conn;
	ResultSet rs;
	public SqlSrvDBConn(){
		stmt=null;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;databaseName=TEST","sa","123456");
		}catch(Exception e){
			e.printStackTrace();
		}
		rs=null;
	}
	public ResultSet executeQuery(String sql)
	{
		try
		{
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE
					,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(sql);
		}catch(SQLException e){
			System.err.println("Data.executeQuery:"+e.getMessage());
		}
	return rs;
	}
//πÿ±’∂‘œÛ
	public void closeStmt()
	{
		try
		{
			stmt.close();
		}catch(SQLException e){
			System.err.println("Data.executeQuery:"+e.getMessage());
		}
	}
	public void closeConn()
	{
		try
		{
			conn.close();
		}catch(SQLException e){
			System.err.println("Data.executeQuery:"+e.getMessage());
		}
	}
}

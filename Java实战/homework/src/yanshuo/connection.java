package yanshuo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class connection {
     
	private List<BookDataBean> booklist;
	private List<RecordDataBean> recordlist;
	public List<BookDataBean> manager_book(){
		booklist = new LinkedList<BookDataBean>();
		String sql = "select * from book";
		Connection conn = database.get_connection();
		ResultSet rs = null;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				booklist.add(new BookDataBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn, null, rs);
		}
		return booklist;
	}
	public List<BookDataBean> index_book(String keyword,int n){
		booklist = new LinkedList<BookDataBean>();
		String sql = "select * from book";
		Connection conn = database.get_connection();
		ResultSet rs = null;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				if(n==0&&(rs.getString("book_name").trim().contains(keyword)||rs.getString("author_name").trim().contains(keyword)||rs.getString("book_type").trim().contains(keyword))){
					booklist.add(new BookDataBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				}else if(n==1&&rs.getString("book_name").trim().contains(keyword)){
					booklist.add(new BookDataBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				}else if(n==2&&rs.getString("author_name").trim().contains(keyword)){
					booklist.add(new BookDataBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				}else if(n==3&&rs.getString("book_type").trim().contains(keyword)){
					booklist.add(new BookDataBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn, null, rs);
			//System.out.println("Close Successful!");
		}
		return booklist;
	}
	public List<RecordDataBean> index_record(String id,String password){
		recordlist = new LinkedList<RecordDataBean>();
		String sql;
			 sql = "select * from record where users_id = ? and password = ?";
		Connection conn = database.get_connection();
		ResultSet rs = null;
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1,id);
				ps.setString(2, password);
				rs = ps.executeQuery();
				while(rs.next()){
					recordlist.add(new RecordDataBean(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				database.closeConn(conn, null, rs);
			}
			
			return recordlist;
	}
}

package yanshuo;

import java.sql.*;

public class index {

	public user indexbyname(String username){
		String sql="select * from users where username=?";
		//��дsql���
		Connection conn=database.get_connection();
		//�������
		ResultSet rs=null;
		user users=new user();
		//System.out.println(username);
		//���ﻹOK
		try{
			//System.out.println(username);
			//���ﻹOK
			PreparedStatement ps=conn.prepareStatement(sql);
			//��������sql���
			//System.out.println(username);
			//����Ͳ�����
			ps.setString(1,username);
			//����Ҫ����Ĳ���
			//System.out.println(username);
			//���ﲻ��
			rs=ps.executeQuery();
			//ִ��sql���
			if(rs.next()){
				//������ҵ�������Ͱ��ҵ��Ľ������user������
				users.setUsername(rs.getString(1));
				users.setPassword(rs.getString(2));
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getString(2));
		    // ������bug,ok��
			}
  	  //	    else{
      //		System.out.println(username);
	  //	}
	  //   ����Ҳ����
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			database.closeConn(conn,null,rs);
			//�ر�����
		}
		return users;
	}
	/*public static void main(String[] args){
		index in=new index();
		user stu=in.indexbyname("tianzhimin");
		System.out.println(stu.getPassword());
	}*/
}

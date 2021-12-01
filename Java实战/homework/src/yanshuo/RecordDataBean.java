package yanshuo;

public class RecordDataBean {
     private String users_id;
     private int book_id;
     private String username;
     private String bookname;
     private String borrow_date;
     private String return_date;
     public RecordDataBean(String a,int b,String c,String d,String e,String f){
    	  users_id = a;
          book_id = b;
          username = c;
          bookname = d;
          borrow_date = e;
          return_date = f;
 	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(String borrow_date) {
		this.borrow_date = borrow_date;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
}

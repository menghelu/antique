package yanshuo;

public class BookDataBean {
	private int id;
	private String book_name;
	private String author_name;
	private String book_type;
	public BookDataBean(int i,String b,String a,String t){
		id = i;
		book_name = b;
		author_name = a;
		book_type = t;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getBook_type() {
		return book_type;
	}
	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}
}

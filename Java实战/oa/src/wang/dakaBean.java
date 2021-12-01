package wang;

public class dakaBean {

	private String username;
	private String begin_time;
	private String end_time;
	public dakaBean(String a,String b,String c){
		username = a;
		begin_time = b;
		end_time = c;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	
}

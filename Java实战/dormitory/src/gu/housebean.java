package gu;

public class housebean {

	private String stu_id;
	private String house_id;
	private String username;
	private String college;
	private String classn;
	public housebean(String a,String b,String c,String d,String e){
		stu_id=a;
		house_id=b;
		username=c;
		college=d;
		classn=e;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getHouse_id() {
		return house_id;
	}
	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getClassn() {
		return classn;
	}
	public void setClassn(String classn) {
		this.classn = classn;
	}
}

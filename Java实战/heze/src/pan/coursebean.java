package pan;

public class coursebean {
	private String username;
	private String class_name;
	private String teacher_name;
	private String grade;
	private String rankn;
	public coursebean(String a,String b,String c,String s,String d){
		username=a;
		class_name=b;
		teacher_name=c;
		grade=s;
		rankn=d;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getRankn() {
		return rankn;
	}
	public void setRankn(String rankn) {
		this.rankn = rankn;
	}
}

package yanshuo;

public class helloAction {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String execute(){
		setMessage("hello");
		return "success";
	}
}

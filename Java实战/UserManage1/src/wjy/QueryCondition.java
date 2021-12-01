package wjy;
public class QueryCondition {
	private int queryField=1;
	private String queryValue="";
	
	public int getQueryField() {
		return queryField;
	}
	public void setQueryField(int queryField) {
		this.queryField = queryField;
	}
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}	
	public boolean satisfy(UserDataBean user)
	{
		switch(queryField)
		{
		case 1:return true;
		case 2:return user.getUsername().contains(queryValue);
		case 3:return user.getPassword().contains(queryValue);
		case 4:return user.getRealname().contains(queryValue);
		case 5:return user.getSex().contains(queryValue);
		case 6:return user.getRole().contains(queryValue);
		
		}
		return false;}	
}

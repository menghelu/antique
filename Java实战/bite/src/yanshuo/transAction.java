package yanshuo;

import java.math.*;
import java.util.Arrays;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class transAction {

	private String input;
	private String index;
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	public String execute(){
		   resultBean rb = new resultBean();
		   int len = input.length();
		   if(len>18)
			   return "error";
		   char[] c = input.toCharArray();
		   long num = 0;
		   for(int i=0;i<len;i++){
			  num+=(c[i]-'0')*Math.pow(10,len-i-1);
		   }
		   String res = "";
		if(index.equals("2")){
			while(num>0){
				long temp = num%2;
				num/=2;
				res+=Integer.toString((int)temp);
			}
			StringBuilder b = new StringBuilder(res);
			res = b.reverse().toString();
			rb.setInit(input);
			rb.setRes(res);
			Map<String,Object> session = ActionContext.getContext().getSession();
			session.put("result",rb);
			return "success";
		}else if(index.equals("8")){
			while(num>0){
				long temp = num%8;
				num/=8;
				res+=Integer.toString((int)temp);
			}
			StringBuilder b = new StringBuilder(res);
			res = b.reverse().toString();
			rb.setInit(input);
			rb.setRes(res);
			Map<String,Object> session = ActionContext.getContext().getSession();
			session.put("result",rb);
			return "success";
		}else{
			while(num>0){
				long temp = num%16;
				num/=16;
				if(temp==10){
					res+="a";
				}else if(temp==11){
					res+="b";
				}else if(temp==12){
					res+="c";
				}else if(temp==13){
					res+="d";
				}else if(temp==14){
					res+="e";
				}else if(temp==15){
					res+="f";
				}else{
					res+=Integer.toString((int)temp);
				}
			}
			StringBuilder b = new StringBuilder(res);
			res = b.reverse().toString();
			rb.setInit(input);
			rb.setRes(res);
			Map<String,Object> session = ActionContext.getContext().getSession();
			session.put("result",rb);
			return "success";
		}
	}
}

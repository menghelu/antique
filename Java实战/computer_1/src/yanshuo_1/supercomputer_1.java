package yanshuo_1;

import java.math.BigDecimal;

public class supercomputer_1 {
	private String firstNum="0";
	private String operator="+";
	private String secondNum="0";
	private String result;
	
	public String getFirstNum(){
       return firstNum;
	}
	public void setFirstNum(String firstNum){
		this.firstNum=firstNum;
	}
	public String getOperator(){
		return operator;
	}
	public void setOperator(String operator){
		this.operator=operator;
	}
	public String getSecondNum(){
		return secondNum;
	}
	public void setSecondNum(String secondNum){
		this.secondNum=secondNum;
	}
    public String getResult(){
    	return result;
    }
    public void setResult(String result){
    	this.result=result;
    }
    
    public void yan_computer(){
    	BigDecimal first=new BigDecimal(firstNum);
    	BigDecimal second=new BigDecimal(secondNum);
    	
    	switch(this.operator){
    	case "+":{
    		this.result=first.add(second).toString();
    		break;
    	}
    	case "-":{
    		this.result=first.subtract(second).toString();
    		break;
    	}
    	case "*":{
    		this.result=first.multiply(second).toString();
    		break;
    	}
    	case "/":{
    		this.result=first.divide(second).toString();
    		break;
    	}
    	}
    }
}
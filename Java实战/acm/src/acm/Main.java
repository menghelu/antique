package acm;

import java.math.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	  Scanner cin=new Scanner(System.in);
	  BigInteger fib[]=new BigInteger[10005];
	  int i;
	  fib[1]=fib[2]=fib[3]=fib[4]=BigInteger.ONE;
	  for(i=5;i<10005;i++){
		  fib[i]=fib[i-1].add(fib[i-2].add(fib[i-3].add(fib[i-4])));
	  }
	  while(cin.hasNext()){
		  int n=cin.nextInt();
		  System.out.println(fib[n]);
	  }
	  
   }

}


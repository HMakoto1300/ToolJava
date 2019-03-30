package Todo;

import java.util.List;

public class Util {
	
	
	static public void print(String str) {
		System.out.println(str);
	}
	static public void print(List<String> str) {
		String oo = null ;
		for(String ss : str) {
			oo = oo + ss;
		}
		print(oo);
		
	}
	
	static public void print(String[] str) {
		String oo = null ;
		for(String ss : str) {
			oo = oo + ss;
		}
		print(oo);
		
	}



}

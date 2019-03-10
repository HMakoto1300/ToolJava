package ForTest;

import MyTool.Clip;
import MyTool.Hiduke;
import MyTool.StringCompress;
import Temp.Input;

public class TestMain {
	public static void main(String[] args) {
		String encoded = StringCompress.compress(Clip.getClipboardString());
		System.out.println(encoded);
		System.out.println(StringCompress.decompress(encoded));
	
		
		Hiduke f=new Hiduke("20190101");
		System.out.printf("%s\r\n",f.getHiduke());
		f.addBuDay(6);
		Hiduke g=new Hiduke(f.getHiduke());

		System.out.printf("%s\r\n",g.getHiduke());

		
		
		
		
	
	}
}

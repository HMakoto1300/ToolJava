package ForTest;

import MyTool.Clip;
import MyTool.StringCompress;
import Temp.Input;

public class TestMain {
	public static void main(String[] args) {
		String encoded = StringCompress.compress(Clip.getClipboardString());
		System.out.println(encoded);
		System.out.println(StringCompress.decompress(encoded));
	
		String koko = Input.input();
		System.out.printf(koko);
	}
	
}

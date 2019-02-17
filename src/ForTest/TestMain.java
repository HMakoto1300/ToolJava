package ForTest;

import MyTool.Clip;
import MyTool.Input;
import MyTool.StringCompress;

public class TestMain {

	public static void main(String[] args) {
		String encoded = StringCompress.compress(Clip.getClipboardString());
		System.out.println(encoded);
		System.out.println(StringCompress.decompress(encoded));
		Input.input();
		System.out.printf(Input.input());
	
	}
	
}

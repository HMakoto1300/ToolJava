package Temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
	public static String input() {
		String str = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			str = br.readLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return str;
	}
}

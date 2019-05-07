package Cmd.Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CmdControl {
	private static final String exitCommand = "exit";
	private static final String prompt = ">";
	
	public CmdControl(CmdExecute e) throws IOException {
		Cmd cmd = new Cmd();
		
		while (true) {
				System.out.print(prompt);
				String str = null;
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				str = br.readLine();
				if (str.equals(exitCommand)) {
					break;
				}
				
				if (cmd.input(str)) {
					e.exe(cmd);
					cmd.show();
				}
					
		}
	}
}			

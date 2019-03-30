package Todo.Cmd;

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

import Todo.Individual.komando;

public class CmdControl {
	private static final String exitCommand = "exit";
	private CmdLine cmd;

	public CmdControl(String prompt) throws IOException {
		komando komando = new komando();
		this.cmd = new CmdLine(komando);

		while (true) {
			try {
				System.out.print(prompt);
				String str = null;
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				str = br.readLine();
				if (str.equals(exitCommand)) {
					break;
				}
				if (this.cmd.set(str)) {
					this.cmd.exe();
				}
			} catch (CommandErr e) {

			}
		}

	}
}

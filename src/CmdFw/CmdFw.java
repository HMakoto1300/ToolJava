package CmdFw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class CmdFw {

	/*
	 * inner class .
	 */
	public class CmdStream {
		private static final String regex = "\"[^\"]+\"|[^ ]+";
		private final Pattern p = Pattern.compile(regex);
		private boolean parse;
		private String orgCmd;
		private String cmd;
		private String[] args;
		private List<String> out;

		public CmdStream(String orgCmd) {
			// initialzie
			this.parse = false;
			this.orgCmd = orgCmd;
			this.cmd = "";
			this.args = null;
			this.out = new ArrayList<String>();

			// parse and set
			List<String> tmp = new ArrayList<String>();
			Matcher m = p.matcher(orgCmd);
			while (m.find()) {
				tmp.add(m.group().replaceAll("\"", ""));
			}
			if (!Objects.isNull(tmp) & tmp.size() > 0) {
				String command = tmp.get(0);
				List<String> list = tmp.subList(1, tmp.size());
				String[] args = list.toArray(new String[list.size()]);
				if (!Objects.isNull(command) && !command.equals("")) {
					this.cmd = command;
					this.args = args;
					this.parse = true;
				}
			}
		}

		public String getCmd() {
			return this.cmd;
		}

		public String[] getArgs() {
			return this.args;
		}

		public void setOut(List<String> out) {
			out.stream().forEach(e -> this.setOut(e));
		}

		public void setOut(String out) {
			this.out.add(out);
		}

		public List<String> getOut() {
			return this.out;
		}
	}


	
	
	private static final String exitCommand = "exit";
	private static final String prompt = ">";
	public CmdFw(){
		
	}
	public void lunch() throws IOException {
		while (true) {
			System.out.print(prompt);
			String str = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			str = br.readLine();
			if (str.equals(exitCommand)) {
				break;
			}

			CmdStream cmdStream = new CmdStream(str);
			if (cmdStream.parse) {
				this.exe(cmdStream);
				this.representation(cmdStream.getOut());
			}
		}
		
	}
	/*
	 * To representation 
	 */
	private void representation(List<String> str) {
		str.stream().forEach(e -> System.out.println(e));
	}
		



	private void exe(CmdStream cmdStream) {
		Object[] dd = { cmdStream };
		Method n = null;
		try {
			n = this.getClass().getMethod(cmdStream.getCmd(), cmdStream.getClass());
			Object r = n.invoke(this, dd);
		} catch (IllegalAccessException e) {
			cmdStream.setOut("UnKnow err occured.1");
		} catch (IllegalArgumentException e) {
			cmdStream.setOut("UnKnow err occured.2");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			cmdStream.setOut(e.toString());
		} catch (NoSuchMethodException e) {
			cmdStream.setOut("No such method.");
		} catch (SecurityException e) {
			cmdStream.setOut("UnKnow err occured.4");
		}
	}

	public void cmd(CmdStream cmdStream) {
		Arrays.stream(this.getClass().getMethods()).filter(m -> m.toString().contains("CmdStream"))
				.forEach(m -> cmdStream.setOut(m.getName()));
	}

}

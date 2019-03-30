package Todo.Cmd;

import Todo.Util;

public class CommandErr extends Exception{
	private String msg;
	public CommandErr(String msg) {
		super(msg);
		this.msg=msg;
		System.out.println(this.msg);
	}
}


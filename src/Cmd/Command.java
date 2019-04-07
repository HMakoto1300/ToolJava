package Cmd;

import Cmd.Core.CmdExecute;
import Cmd.Core.CmdStream;

public class Command extends CmdExecute{
	public Command() {
		super();
	}
	

	public void test(CmdStream cmd) {
		for (String ss : cmd.get().args) {
			cmd.get().out.add(ss); 
		}
	}
}

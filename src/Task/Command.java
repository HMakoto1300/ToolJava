package Task;

import Cmd.Core.CmdControl;
import Cmd.Core.CmdExecute;
import Cmd.Core.CmdStream;

public class Command extends CmdExecute{
	private TaskControl tc;
	public Command() {
		super();
		tc = new TaskControl();
	}
	

	public void test(CmdStream cmd) {
		for (String ss : cmd.get().args) {
			cmd.get().out.add(ss); 
		}
	}
	
	public void a(CmdStream cmd) {
		// System.out.print(cmd.get().args[0]);
		this.tc.a(cmd.get().args);
	}
	
	public void showall(CmdStream cmd) {
		this.tc.showall().stream().forEach(e->cmd.get().out.add(e));
	}
	
	public void tree(CmdStream cmd) {
		this.tc.tree().stream().forEach(e->cmd.get().out.add(e));
	}
	public void ls(CmdStream cmd) {
		this.tc.ls().stream().forEach(e->cmd.get().out.add(e));
	}
		
	public void pwd(CmdStream cmd) {
		this.tc.pwd().stream().forEach(e->cmd.get().out.add(e));
	}	
	
	public void cd(CmdStream cmd) {
		if(cmd.get().args[0].equals("..")) {
			this.tc.paremtd();
		}else {
			this.tc.cd(Integer.parseInt(cmd.get().args[0]));
		}
	}	
}

package Task;

import Cmd.Core.CmdControl;
import Cmd.Core.CmdExecute;
import Cmd.Core.Cmd;

public class Command extends CmdExecute{
	private TaskControl tc;
	public Command() {
		super();
		tc = new TaskControl();
	}
	

	public void test(Cmd cmd) {
		for (String ss : cmd.get().args) {
			cmd.get().out.add(ss); 
		}
	}
	
	public void a(Cmd cmd) {
		// System.out.print(cmd.get().args[0]);
		this.tc.a(cmd.get().args);
	}
	
	public void showall(Cmd cmd) {
		this.tc.showall().stream().forEach(e->cmd.get().out.add(e));
	}
	
	public void tree(Cmd cmd) {
		this.tc.tree().stream().forEach(e->cmd.get().out.add(e));
	}
	public void ls(Cmd cmd) {
		this.tc.ls().stream().forEach(e->cmd.get().out.add(e));
	}
		
	public void pwd(Cmd cmd) {
		this.tc.pwd().stream().forEach(e->cmd.get().out.add(e));
	}	
	
	public void cd(Cmd cmd) {
		if(cmd.get().args[0].equals("..")) {
			this.tc.paremtd();
		}else {
			this.tc.cd(Integer.parseInt(cmd.get().args[0]));
		}
	}	
}

package Cmd.Core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

import Cmd.Core.CmdExecute.CmdErr;
import Cmd.Core.Util.CmdHistory;


abstract public class CmdExecute {
	
	public class CmdErr extends Exception {
		public CmdErr(String msg) {
			super(msg);
		}
	}
	
		
	public void exe(CmdStream cmd) {
		Object[] dd = { cmd };
		Method n = null;
		//System.out.print(cmd.get().command);
		try {
			n = this.getClass().getMethod(cmd.get().command ,cmd.getClass());
			Object r = n.invoke(this, dd);
		} catch (IllegalAccessException e) {
			cmd.get().out.add("UnKnow err occured.");
		} catch (IllegalArgumentException e) {
			cmd.get().out.add("UnKnow err occured.");
		} catch (InvocationTargetException e) {
			cmd.get().out.add("UnKnow err occured.");
		} catch (NoSuchMethodException e) {
			cmd.get().out.add("No such method.");
		} catch (SecurityException e) {
			cmd.get().out.add("UnKnow err occured.");
		}
	}


	public void cmd(CmdStream cmd) {
		Arrays.stream(this.getClass().getMethods())
		.filter(m -> m.toString().contains("CmdStream"))
		.forEach(m -> cmd.get().out.add(m.getName()));
		
		//.forEach(m -> cmd.get().out.add(m.toString().contains("CmdStream")));
		//.forEach(m -> Arrays.stream(m.getParameters()).forEach(a -> cmd.get().out.add(a.getClass().getName())));
		

		//.filter(m -> m.getParameters()[0].getName() == "CmdStream")
		//.
		
		/*for(Method m : this.getClass().getMethods()) {
			Parameter p = m.getParameters()
			cmd.get().out.add(m.getName());
		 *///}
	}

	
	private CmdHistory his;
	public void l(CmdStream cmd){
		if(Objects.isNull(this.his)) {
			this.his = new CmdHistory();
		}
		
		for(String str : this.his.set(cmd)) {
			cmd.get().out.add(str);
		}
	}
	public void d(CmdStream cmd){		
		
		if(Objects.isNull(this.his)) {
			this.his = new CmdHistory();
		}
		
		String originalcommand =	this.his.get(Integer.parseInt(cmd.get().args[0]));
		cmd.get().out.add(originalcommand);
		if(cmd.input(originalcommand)) {
			exe(cmd);
			
		}
		
		
		
		
	}

	
	
}
		



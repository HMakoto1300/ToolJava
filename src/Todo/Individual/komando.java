package Todo.Individual;

import Todo.Util;
import Todo.Cmd.Command;

public class komando extends Command{
	
	public void test(String[] args) {
		for (String ss : args) {
			Util.print(ss);
		}
	}


}

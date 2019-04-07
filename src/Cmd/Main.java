package Cmd;

import java.io.IOException;

import Cmd.Core.CmdControl;


public class Main {

	public static void main(String[] args) throws IOException {
		Command command = new Command();
		CmdControl cmd = new CmdControl(command);
		
	}

}

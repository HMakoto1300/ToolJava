package MyTool;

import java.util.Scanner;

abstract public class CommandLineFw {

	public CommandLineFw() {
	}
	
	public void exe() {
		Scanner scanner = new Scanner(System.in);
		while(true) {

			System.out.print("Input > ");
			
			String com = scanner.nextLine();
			String[] input = com.split(" ");
			
//			System.out.println(input);
			
			
			if(input[0].equals("exit")) {
				System.out.println("bye");
				break;
			}else {
				try {
					ifthen(input);
				}catch(Exception e) {
					System.out.println(e);
				}
			}
				
				
			
		}
		
		scanner.close();
		
	}
	
	abstract protected void ifthen(String[] input) ;

	
}


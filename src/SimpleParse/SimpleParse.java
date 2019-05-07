package SimpleParse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import MyTool.Clip;
import MyTool.P;

public class SimpleParse {
	public static void main(String args[]) {
		List<String> doc = new ArrayList<String>();
		for(String str : Clip.getClipboardString().split("\n")) {
			doc.add(str);
		}
		SimpleParse sp = new SimpleParse(doc);
	}
	
	static final String forSpace = "^[ Å@\t]*$";
	static final Pattern spaceP = Pattern.compile(forSpace);

	static final String forTabStart = "^(\t*{";
	static final String forTabEnd = "})(.*)$";
	
//	static final Pattern tabP = Pattern.compile(forTab); 

	
	public SimpleParse(List<String> doc) {
		List<String> docc = new ArrayList<String>();
		
		/*
		 * To delete row such that consists of space ,tab , etc .
		 */
		doc.stream().forEach(e -> {
			Matcher m = spaceP.matcher(e);
			if(!m.find()) {
				docc.add(e);
			}
		});
		
		int currentlayer = 0;
		Node<String> root = new Node<String>("root");

		Map<Integer,Node<String>> map = new HashMap<Integer,Node<String>>();
		map.put(currentlayer, root);

		for(String row : docc) {
			for(int i = currentlayer + 1 ; i >= 0 ; i=i-1 ) {
				Matcher m = parse(i).matcher(row);
				if(m.find()) {
					
					break;
				}
				
				if(i<=currentlayer) {
					map.remove(i);
				}
					
				
			}
			
			
			
			
			
			
		}
		
	}
	private Pattern parse(int indent) {
		String regex;
		regex = forTabStart + Integer.toString(indent) + forTabEnd;
		return Pattern.compile(regex);
	}
		
		

}

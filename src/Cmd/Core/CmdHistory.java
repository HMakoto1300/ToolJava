package Cmd.Core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class CmdHistory {
	static private int numberOfhistory=10;
	private static final List<String> nonCountCommandList = Arrays.asList("d", "l");

	private Map<Integer,String> his;
	public CmdHistory() {
		
	}
	public List<String> set(CmdStream cmd) {
		int i = cmd.getIndex();
		Integer j = 0;
		this.his = new HashMap<Integer,String>();
		while(i>0) {
			if(!nonCountCommandList.contains(cmd.get(i).command)) {
				this.his.put(j,cmd.get(i).originalCmd);
				j=j+1;
			}
				
			if(j>=numberOfhistory) {
				break;
			}
			i = i -1;
		}
		return show();
	}

	private List<String> show() {
		List<String> str = new ArrayList<String>();
		if(!Objects.isNull(this.his)) {
			for(Entry<Integer , String> e : this.his.entrySet()) {
				str.add("["+e.getKey()+"]"+e.getValue());
			}
			Collections.reverse(str);
		}
		return str;
	}
	
	public String get(int index) {
		
		String str = this.his.get(index);
		return str;
		
	}
	
}
			


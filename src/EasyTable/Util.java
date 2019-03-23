package EasyTable;

import java.util.ArrayList;
import java.util.List;

public class Util {
	static public List<Record> sum(List<Record> list_1,List<Record> list_2){
		for(Object lst : list_2) {
			if(!list_1.contains(lst)) {
				list_1.add((Record) lst);
			}
		}
		return list_1;
	}
	static public List<Record> union(List<Record> list_1,List<Record> list_2){
		list_1.addAll(list_2);
		return list_1;
	}
	static public List<Record> intersection(List<Record> list_1,List<Record> list_2){
		List<Record> list = new ArrayList<Record>();
		for(Object lst : list_1) {
			if(list_2.contains(lst)) {
				list.add((Record) lst);
			}
		}
		return list_1;
	}		
}
	
	
	
	
	

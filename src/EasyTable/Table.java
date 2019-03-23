package EasyTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Table extends HashMap<Integer,Record>{
		
	//private Integer key;

	
	public Table() {
		super();
		//this.key = 0;
	}
	
	public void insert(Record ...value) {
		for(Record r:value) {
			//super.put(this.key, r);
			//this.key += 1;
			super.put(r.hashCode(), r);
		}
	}

	
	public List<Record> get(List<Integer> keys){
		List<Record> rs = new ArrayList<Record>();
		for(Integer key:keys) {
			rs.add((Record) this.get(key));
		}
		return rs;
	}
			

	public void delete(List<Record> rcd){
		for(Record r : rcd) {
			this.remove(r.hashCode());
		}
	}

	public void delete(Record ...rcd){
		for(Record r : rcd) {
			this.remove(r.hashCode());
		}
	}	
	
	
	public List<Record> select(String col,Object value){
		List<Record> rcds = new ArrayList<Record>();
		this.entrySet().stream().filter(e -> e.getValue().match(col, value))
		.forEach(e -> rcds.add(e.getValue()));
		return rcds;
	}

	public List<Record> selectLessThan(String col,Object value){
		List<Record> rcds = new ArrayList<Record>();
		this.entrySet().stream()
		.filter(e -> e.getValue().compareTo(col, value)==-1)
		.forEach(e -> rcds.add(e.getValue()));
		return rcds;
	}
	public List<Record> selectLargerThan(String col,Object value){
		List<Record> rcds = new ArrayList<Record>();
		this.entrySet().stream()
		.filter(e -> e.getValue().compareTo(col, value)==1)
		.forEach(e -> rcds.add(e.getValue()));
		return rcds;
	}
	
	public List<Record> select(){
		List<Record> rcds = new ArrayList<Record>();
		this.entrySet().stream().filter(e -> true)
		.forEach(e -> rcds.add(e.getValue()));
		return rcds;
	}
	
	

}

package ForTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import EasyTable.Record;
import EasyTable.Table;
import Hiduke.Hiduke;
import MyUtil.MyUtil;

public class Test02 {

	public static void main(String[] args){
		Table t = new Table();
		MyUtil u = new MyUtil();
		Hiduke dd = new Hiduke("20190101");
		for(int i= 0;i<10;i++) {
			dd.addDay(i);
			R k = new R("tt",i,dd.toString());
			t.insert(k);
		}
		R k1= new R("th",3,"20190101");
		t.insert(k1);
		
		u.log("----------");
//		t.select("a", "tt").forEach(e -> u.log(e.toString()));
		u.log("----------");

		t.select().forEach(e -> u.log(e.toString()));
		u.log("----------");

//		t.lessThan("i", 5).forEach(e -> u.log(e.toString()));
		t.selectLargerThan("d", new Hiduke("20190202")).forEach(e -> u.log(e.toString()));
		u.log("----------");

		t.selectLargerThan("i", 3).forEach(e -> u.log(e.toString()));
		
		
				
	}

}

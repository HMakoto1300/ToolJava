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
			dd.addBuDay(i);
			R k = new R("tt",i,dd.toString());
			t.insert(k);
		}
		
		
		R k1= new R("th",3,"20190101");
		t.insert(k1);
		k1.i=4;
		t.insert(k1);
		k1.i=5;
		t.select("a", "tt").forEach(e -> u.log(e.toString()));
		List<Record> aaa = t.select("a", "tt");
		t.delete(aaa);
		u.log("----------");
		t.select("a", "th").forEach(e -> u.log(e.toString()));
		
		//t.get(t.search("a", "tt")).forEach(e -> u.log(e.toString()));
		
		Map<String,String> p = new HashMap<String,String>();
		p.put("1","a");
		p.put("2","b");
		u.log(p.get("2").toString());
		
		/*
		 * //R(String a,Integer i,Hiduke d) R r_1 = new R("test",4,"20200101"); R r_2 =
		 * new R("test",4,"20200101"); R r_3 = new R("test",4,"20200101"); R r_4 = new
		 * R("test2",0,"20190101");
		 * 
		 * t.put(r_1,r_2,r_3,r_4); u.log("kok");
		 * 
		 * t.get(t.search("a", "test")).forEach(e -> u.log(e.toString()));
		 * 
		 * R h = (R) t.get(t.search("a", "test")).get(0); h.i=10; u.log("------");
		 */


		
		//		t.get(t.search("d", new Hiduke("20200101"))).forEach(e -> u.log(e.toString()));
		
		// TODO Auto-generated method stub

		
	}

}

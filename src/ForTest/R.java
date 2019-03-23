package ForTest;

import java.util.Date;

import EasyTable.Record;
import Hiduke.Hiduke;

public class R extends Record{
	public String a;
	public Integer i;
	public Hiduke d;
	
	public R(String a,Integer i,String d) {
		this.a=a;
		this.i=i;
		this.d=new Hiduke(d);
	}

}

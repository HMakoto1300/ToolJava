package Wbs;

import java.util.ArrayList;
import java.util.List;

public class Task {
	private Integer tasknum;
	private List<Integer> pretasknum;
	private Double work;
	
	public Task(Integer tasknum,List<Integer> pretasknum,Double work) {
		this.tasknum=tasknum;
		this.pretasknum=pretasknum;
		this.work=work;
	}
	
	public Integer gettasknum() {
		return this.tasknum;
	}
	public List<Integer> getpretasknum(){
		return this.pretasknum;
	}
	public Double getwork() {
		return this.work;
	}
	
	
	

}

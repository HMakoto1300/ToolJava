package Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Task.Task.TaskErr;

public class TaskControl {
	static private final String route="route";
	private Task current;
	private Map<Integer, Task> taskMap;

	public TaskControl() {
		String[] aa = {route};
		this.taskMap = new HashMap<Integer,Task>();
		this.current=new TestTask();
		this.current.set(aa);
		this.taskMap.put(current.hashCode(),current);
		
	}
		
	
	public void a(String[] args) {
		Task task = new TestTask();
		task.set(args);
		try {
			task.move(this.current);
		} catch (TaskErr e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.taskMap.put(task.hashCode(),task);
	}
		
		
	
	public List<String> showall() {
		List<String> str = new ArrayList<String>();
		this.taskMap.entrySet().stream().forEach(e -> str.add(e.getValue().show()));
		return str;
	}
	public List<String> tree() {
		List<String> str = new ArrayList<String>();
		this.current.tree().stream().forEach(e->str.add(e));
		return str;
	}
	
	public List<String> ls() {
		List<String> str = new ArrayList<String>();
		this.current.ls().stream().forEach(e->str.add(e));
		return str;
	}
	public List<String> pwd() {
		List<String> str = new ArrayList<String>();
		this.current.pwd(true).stream().forEach(e->str.add(e));
		return str;
	}
	public void cd(int i) {
		this.current=this.taskMap.get(i);
	}
	public void paremtd() {
		this.current=this.current.getParent();
	}
}

package Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import Task.Task.TaskErr;

/**
 * this package may contain bugs now.
 * @author makot
 *
 */
public abstract class Task {
	public class TaskErr extends Exception {
		private static final long serialVersionUID = -1923768390577777254L;
		public TaskErr(String msg) {
			super(msg);
		}
	}

	private Date registrationDate;
	private List<Task> childList;
	private Task parent;

	
	public Task() {
		this.childList = new ArrayList<Task>();
		this.registrationDate = new Date();
	}
	
	abstract public String show();
	abstract public void set(String[] args);
	
	public List<String> ls() {
		List<String> str = new ArrayList<String>();
		this.childList.stream().forEach(e -> str.add(e.show()));
		return str;
	}
	static private String route="route";
	static private String indent="\t";
	public List<String> tree() {
		List<String> str = new ArrayList<String>();
		str.add(this.show());
		for (Task task : this.childList) {
			str.add(task.show());
			if(task.getChildList().size()>0) {
				task.tree().stream().forEach(e -> str.add(indent + e));
			}
		}
		return str;
	}
	public List<String> pwd(boolean start){
		List<String> str = new ArrayList<String>();
		if(start) {
			String indents="";
			for(String ss : this.pwd(false)) {
				str.add(indents + ss);
				indents = indents + indent;
			}
			return str;
		}else {
			if(Objects.isNull(this.parent)) {
//				str.add(this.show());
//				return str;
			}else {
				this.parent.pwd(false).stream().forEach(e-> str.add(e));
			}
			str.add(this.show());
			return str;
		}
	}
			
			
		


			
	public void move(Task toTask) throws TaskErr {
			Task tmp = this.parent;
			if(!Objects.isNull(this.parent)) {
			this.parent.relase(this);
		}	
		try {
			toTask.has(this);
		} catch (TaskErr e) {
			tmp.has(this);
			e.printStackTrace();
		}
	}
	


	private void has(Task childTask) throws TaskErr {
		if(this.checkProgeny(childTask)) {
			throw new TaskErr("inconsist in progeny.");
		}else {
			if(this.childList.stream().filter(e -> e == childTask).count()>0) {
				throw new TaskErr("inconsist in brothers.");
			}else {
				this.childList.add(childTask);
				childTask.setParent(this);
			}
		}
	}
				
	private void relase(Task childTask) {
		this.childList.remove(childTask);
		childTask.delParent(this);
	}
	private void delParent(Task parentTask) {
		this.parent=null;
	}
		
//	private void belong(Task parentTask) throws TaskErr {
//		parentTask.has(this);
//	}
				
//	private void leave(Task parentTask) {
//		parentTask.relase(this);
//	}
	

	
	
	private List<Task> getChildList(){
		return this.childList;
	}
	
	public Task getParent(){
		return this.parent;
	}
		
	private void setParent(Task parentTask) {
		this.parent=parentTask;
	}
		
	//To check if target'progeny has this task which is going to have the target.
	private boolean checkProgeny(Task childTask) {
		if (this == childTask) {

			return true;
		} else if (childTask.getChildList().size() == 0) {
			return false;
		} else {
			if (childTask.getChildList().stream().filter(e -> this.checkProgeny(e) == true).count() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
}

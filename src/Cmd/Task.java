package Cmd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
	private String contents;
	private Date registrationDate;
	public List<Task> childList;

	public Task() {
		this.contents = "";
		this.childList = new ArrayList<Task>();
		this.registrationDate = new Date();
	}

	public void has(Task chiledTask) {
		

	}

	private boolean checkProgeny(Task childTask) {
		if (this == childTask) {
			return true;
		} else if (childTask.childList.size() == 0) {
			return false;
		} else {
			if (childTask.childList.stream().filter(e -> this.checkProgeny(e) == true).count() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}




}

package Task;

public class TestTask extends Task{
	private String comments;

	public TestTask() {
		super();
	}
	
	@Override
	public String show() {
		return this.comments+"-"+this.hashCode();
	}

	@Override
	public void set(String[] args) {
		this.comments=args[0];
	}
}
		



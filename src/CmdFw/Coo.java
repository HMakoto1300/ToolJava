package CmdFw;

import java.io.IOException;
import java.util.Objects;

import Cmd.Core.CmdControl;
import SimpleParse.CannotOperateNode;
import SimpleParse.Node;
import Task.Command;

public class Coo extends CmdFw{
	private Node<String> root;
	private Node<String> current;
	
	public static void main(String[] args) throws IOException {
		Coo coo = new Coo();
		coo.lunch();
	}
	
	public Coo() throws IOException {
		super();
		this.root = new Node<String>("root");
		this.current = this.root;
	}
	
	public void add(CmdStream cmd) throws CannotOperateNode {
		
		
		Node<String> child = new Node<String>(cmd.getArgs()[0]);
		this.current.add(child);
//		this.current.getChildren().stream().forEach(e -> cmd.setOut(e.toString()));
	}
	public void cd(CmdStream cmd) {
		if(cmd.getArgs()[0].equals("..")) {
			if(!Objects.isNull(this.current.getParent())) {
				this.current=this.current.getParent();
			}
		}
		for(Node n : this.current.getChildren()) {
			if(n.getData().equals(cmd.getArgs()[0])) {
				this.current=n;
			}
		}
//		cmd.setOut(Node.toData(this.current.getAcientList()));
	}
	public void ls(CmdStream cmd) {
		cmd.setOut(Node.toData(this.current.getChildren()));
	}
	public void pwd(CmdStream cmd) {
		StringBuffer strbuf = new StringBuffer("");
		Node.toData(this.current.getAcientList()).stream().forEach(e -> strbuf.append(e.toString()).append("/"));
		cmd.setOut(strbuf.toString());
	}
	public void tree(CmdStream cmd) {
		cmd.setOut(this.current.treeShow());
	}
			
		

	
	
	
	
	
	

}

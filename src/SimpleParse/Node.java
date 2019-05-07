package SimpleParse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<O> {
	private O data;
	private Node<O> parent;
	private List<Node<O>> children;


	
	public Node(O data) {
		this.data = data;
		this.parent = null;
		this.children = new ArrayList<Node<O>>();
	}
	
	static public <O> List<O> toData(List<Node<O>> nodes){
		List<O> list = new ArrayList<O>();
		nodes.stream().forEach(e -> list.add(e.getData()));
		return list;
	}
	
	/*
	 * Return a data .
	 */
	public O getData() {
		return this.data;
	}

	/*
	 * To set the parent. Basically this method is used by called from add Method.
	 */
	private void setParent(Node<O> parent) throws CannotOperateNode {
		if (!Objects.isNull(this.parent)) {
			throw new CannotOperateNode("fatal error . this node has already the parent ."
					+ "It is fatal when it was occured at this timing .");
		}
		this.parent = parent;
	}

	public void leaveFromParent() {
		if (!Objects.isNull(this.parent)) {
			this.parent.children.remove(this);
			this.parent = null;
		}
	}

	/*
	 * Check the node if it is last node . If this node has nothing of children ,
	 * then return true. i.e. This is last node
	 */
	private boolean checkEnd() {
		if (this.children.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * To add child.
	 */
	public void add(Node<O> child) throws CannotOperateNode {
		/*
		 * To check if the child node added is in ancient list.
		 */
		if (this.getAcientList().contains(child)) {
			throw new CannotOperateNode("this child added exist in ancient list .");
		}
		/*
		 * To check if the child node has already the some parent .
		 */
		if (!Objects.isNull(child.getParent())) {
			throw new CannotOperateNode("this node has already the parent .");
		}

		this.children.add(child);
		child.setParent(this);
	}

	public Node<O> getParent() {
		return this.parent;
	}

	/*
	 * Return the list ancients including this self.
	 */
	public List<Node<O>> getAcientList() {
		List<Node<O>> acientList = new ArrayList<Node<O>>();
		if (Objects.isNull(this.parent)) {
			acientList.add(this);
			return acientList;
		} else {
			acientList = this.parent.getAcientList();
			acientList.add(this);
			return acientList;
		}
	}

	public List<Node<O>> getChildren() {
		return this.children;
	}
	



	/*
	 * This is representation of Node by implementation to toString .
	 */
	public String toString() {
		return this.data.toString();
	}

	/*
	 * This is representation of Nodes as tree construction .
	 */
	static final String tab = "\t";
	public List<String> treeShow() {
		List<String> tree = new ArrayList<String>();
		this.children.stream().forEach(e -> {
			tree.add(e.toString());
			if (!e.checkEnd()) {
				e.treeShow().stream().forEach(f -> tree.add(tab + f));
			}
		});
		return tree;
	}

}

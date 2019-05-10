package Test;

import java.util.ArrayList;
import java.util.List;

import MyTool.Clip;
import MyTool.P;
import Node.CannotOperateNode;
import Node.Node;

public class testMain {
	public static void main(String args[]) throws CannotOperateNode {
		List<dummy> list = new ArrayList();

		dummy a = new dummy("a");
		dummy b = new dummy("b");
		dummy c = new dummy("c");
		dummy cc = new dummy("c");
		dummy d = new dummy("d");
		
		list.add(a);
		list.add(b);
		list.add(c);
//		list.add(cc);
		list.add(d);
		
		list.stream().forEach(e -> P.p(e.toString()));
		if(list.contains(cc)) {
			P.p("contains");
		}
		if(c.equals(cc)) {
			P.p("= as contain");
		}
		
		Node<dummy> na = new Node<dummy>(a);
		Node<dummy> nb = new Node<dummy>(b);
		Node<dummy> nc = new Node<dummy>(c);
//		Node<dummy> ncc = new Node<dummy>(c);
		Node<dummy> nd = new Node<dummy>(d);
		
		na.have(nb);
		nb.have(nc);
		nc.have(nd);


//		na.treeShow().stream().forEach(e -> P.p(e));

//		nc.leaveFromParent();
//		nc.have(nb);
//		
//		na.getTreeString().stream().forEach(e -> P.p(e));
//		nc.getTreeString().stream().forEach(e -> P.p(e));

	a.vvv();
		
		
		
	}
}

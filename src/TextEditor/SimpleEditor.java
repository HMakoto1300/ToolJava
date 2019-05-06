package TextEditor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

import java.awt.Container;
import java.awt.Font;
import java.awt.BorderLayout;

class SimpleEditor extends JFrame {
	public static void main(String args[]) {
		SimpleEditor frame = new SimpleEditor("ƒ^ƒCƒgƒ‹");
		frame.setVisible(true);
	}

	public SimpleEditor(String title) {
		super(title);
		JTextArea t = new JTextArea();
		t.setFont(new Font("Dialog", Font.PLAIN, 12));
		t.setTabSize(4);
		// EditorMenu em = new EditorMenu(ta);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		// c.add(em, BorderLayout.NORTH);
		JScrollPane sp = new JScrollPane(t);
		c.add(sp, BorderLayout.CENTER);
		this.setSize(800, 600);
//		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//	    UndoHelper helper = new UndoHelper(t);
		
	}
}
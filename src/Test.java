import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
import javax.swing.JFrame;
 
public class Test{
	public static void main(String[] args) {
		TestWindow gw = new TestWindow("テストウィンドウ",400,300);
		gw.setVisible(true);
	}
}
class TestWindow extends JFrame implements KeyListener{
	public TestWindow(String title, int width, int height) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
 
		//キー入力の有効化
		addKeyListener(this);
	}
 
	@Override
	public void keyTyped(KeyEvent e) {
		//使用しないので空にしておきます。
	}
 
	@Override
	public void keyPressed(KeyEvent e) {
		switch ( e.getKeyCode() ) {
		case KeyEvent.VK_UP:
			//上キー
			System.out.println("上が押されました");
			break;
		case KeyEvent.VK_SPACE:
			//スペースキー
			System.out.println("スペースが押されました");
			break;
		case KeyEvent.VK_ENTER:
			//エンターキー
			System.out.println("Enterが押されました");
			break;
		}
	}
 
	@Override
	public void keyReleased(KeyEvent e) {
		switch ( e.getKeyCode() ) {
		case KeyEvent.VK_UP:
			//上キー
			System.out.println("上が離されました");
			break;
		case KeyEvent.VK_SPACE:
			//スペースキー
			System.out.println("スペースが離されました");
			break;
		case KeyEvent.VK_ENTER:
			//エンターキー
			System.out.println("Enterが離されました");
			break;
		}
	}
}
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
import javax.swing.JFrame;
 
public class Test{
	public static void main(String[] args) {
		TestWindow gw = new TestWindow("�e�X�g�E�B���h�E",400,300);
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
 
		//�L�[���̗͂L����
		addKeyListener(this);
	}
 
	@Override
	public void keyTyped(KeyEvent e) {
		//�g�p���Ȃ��̂ŋ�ɂ��Ă����܂��B
	}
 
	@Override
	public void keyPressed(KeyEvent e) {
		switch ( e.getKeyCode() ) {
		case KeyEvent.VK_UP:
			//��L�[
			System.out.println("�オ������܂���");
			break;
		case KeyEvent.VK_SPACE:
			//�X�y�[�X�L�[
			System.out.println("�X�y�[�X��������܂���");
			break;
		case KeyEvent.VK_ENTER:
			//�G���^�[�L�[
			System.out.println("Enter��������܂���");
			break;
		}
	}
 
	@Override
	public void keyReleased(KeyEvent e) {
		switch ( e.getKeyCode() ) {
		case KeyEvent.VK_UP:
			//��L�[
			System.out.println("�オ������܂���");
			break;
		case KeyEvent.VK_SPACE:
			//�X�y�[�X�L�[
			System.out.println("�X�y�[�X��������܂���");
			break;
		case KeyEvent.VK_ENTER:
			//�G���^�[�L�[
			System.out.println("Enter��������܂���");
			break;
		}
	}
}
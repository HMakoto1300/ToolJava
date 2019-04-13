package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �\�P�b�g�ʐM(�T�[�o�[��)
 */
public class SimpleSocket {

	void runSample() {

		ServerSocket sSocket = null;
		Socket socket = null;
		BufferedReader reader = null;
		PrintWriter writer = null;

		try {
			// IP�A�h���X�ƃ|�[�g�ԍ����w�肵�ăT�[�o�[���̃\�P�b�g���쐬
			sSocket = new ServerSocket();
			sSocket.bind(new InetSocketAddress("127.0.0.1", 10001));

			System.out.println("�N���C�A���g����̓��͑҂����");

			// �N���C�A���g����̗v����҂������܂�
			socket = sSocket.accept();

			// �N���C�A���g����̎��p
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// �T�[�o�[����N���C�A���g�ւ̑��M�p
			writer = new PrintWriter(socket.getOutputStream(), true);

			// �������[�v bye�̓��͂Ń��[�v�𔲂���
			String line = null;
			int num;
			while (true) {

				line = reader.readLine();

				if (line.equals("bye")) {
					break;
				}

				try {
					num = Integer.parseInt(line);

					if (num % 2 == 0) {
						// ���M�p�̕����𑗐M
						writer.println("OK");
					} else {
						// ���M�p�̕����𑗐M
						writer.println("NG");
					}
				} catch (NumberFormatException e) {
					// ���M�p�̕����𑗐M
					writer.println("���l����͂��ĉ�����");
				}

				System.out.println("�N���C�A���g�œ��͂��ꂽ������" + line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (socket != null) {
					socket.close();
				}
				if (sSocket != null) {
					sSocket.close();
				}
				System.out.println("�T�[�o�[���I���ł�");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

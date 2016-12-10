package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//�ͻ����߼���
class PokedexClient {

	ClientInterface interface1;

	BufferedReader is;
	PrintWriter os;
	Socket server;

	public PokedexClient(ClientInterface c) {
		interface1 = c;
	}

	// ���ӷ���������
	public void connectServer() {
		try {
			server = new Socket(InetAddress.getByName(null), 1025);
			is = new BufferedReader(new InputStreamReader(server.getInputStream()));
			os = new PrintWriter(server.getOutputStream());
			interface1.outputArea.setText("���ӷ������ɹ�" + '\n');
			String str = is.readLine();
			while (!str.equals("end")) {
				interface1.outputArea.append(str + '\n');
				str = is.readLine();
			}
			interface1.outputArea.append("\n");
			interface1.connect.setText("�Ͽ�����");
			interface1.inputField.setEditable(true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			interface1.outputArea.setText("���ӷ�����ʧ��");
		} catch (IOException e) {
			e.printStackTrace();
			interface1.outputArea.setText("���ӷ�����ʧ��");
		}
	}

	// �Ͽ����ӷ���
	public void disconnect() {
		if (server != null) {
			try {
				os.println("quit");
				os.flush();
				is.close();
				os.close();
				server.close();
				interface1.inputField.setEditable(false);
				interface1.outputArea.append("�Ͽ����ӳɹ�" + '\n');
				interface1.connect.setText("���ӷ�����");
				interface1.inputField.setEditable(false);
			} catch (IOException e) {
				e.printStackTrace();
				interface1.outputArea.setText("�Ͽ�����ʧ��" + '\n');
			}
		}

	}

	// ��ѯͼ������
	public void search() {
		if (interface1.connect.getActionCommand() == "���ӷ�����")
			interface1.outputArea.setText("�������ӷ�����" + '\n');
		else {
			String inputString = interface1.inputField.getText();
			if (inputString.equals(""))
				interface1.outputArea.setText("����������Ҫ���ҵ�Pokemon����" + '\n');
			else {

				os.println(inputString);
				os.flush();
				try {
					String str = is.readLine();
					interface1.outputArea.setText("");
					while (!str.equals("end")) {
						interface1.outputArea.append(str + '\n');
						str = is.readLine();
					}
					interface1.outputArea.append("\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

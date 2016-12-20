package socket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//������߼���
class PokedexServer {

	ServerInterface interface1;

	PokedexManager pokedex; // ����ͼ������

	ServerSocket server;
	Socket client;
	private static int num = 1; // ���Ҵ�������

	public PokedexServer(ServerInterface s) {
		interface1 = s;
		scan();
		startServer();
	}

	// ��ȡͼ���ļ��ķ���������PokedexManager�����
	public void scan() {
		try {
			pokedex = new PokedexManager(new FileScanner().start());
			interface1.outputArea.setText("ͼ���ļ���ȡ�ɹ�" + '\n');
		} catch (FileNotFoundException e) {
			interface1.outputArea.setText("�Ҳ���ͼ���ļ�" + '\n');
		}
	}

	// ��������������������ķ���
	public void startServer() {
		while (true) {
			try {
				server = new ServerSocket(1025);
			} catch (IOException e) {
				System.out.println("����������ʧ��");
				System.exit(-1);
			}

			try {
				client = server.accept();
				new ServerThread(client).start();
			} catch (IOException e) {
				System.out.println("��������ʧ��");
			}

			try {
				server.close();
			} catch (IOException e) {
				System.out.println("����������ʧ��");
			}
		}
	}

	// ���������߳���
	private class ServerThread extends Thread {
		private Socket client;
		private BufferedReader is;
		private PrintWriter os;
		private String inputString;
		private String clientIP;

		public ServerThread(Socket cli) throws IOException {
			client = cli;
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			os = new PrintWriter(client.getOutputStream());
			// ����ͻ�������������˵�ͼ����Ϣ
			os.println("�����ͼ���ְ���ȫ��ͼ��#XXX-XXX");
			os.println("��ͼ����֧�ֹٷ�����������������");
			os.println("end"); // ��������ı�ǣ���ͬ
			os.flush();
		}
		
		//�߳�����������
		public void run() {
			clientIP = client.getInetAddress().toString();
			try {
				inputString = is.readLine();
				// �ڽ��ܵ���quit�����ǰ��������
				while (!inputString.equals("quit")) {
					search(inputString);
					inputString = is.readLine();
				}
				is.close();
				os.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// �������Ҫ�������ݵķ���
		public void search(String str) {
			Pokemon tmp = new Pokemon();
			boolean found = false; // ��ʾ���ҳɹ���ʧ��
			// ��Ը�ʽ��#+��š�
			if (str.charAt(0) == '#' && str.length() > 1 && Character.isDigit(str.charAt(1))) {
				Integer id = Integer.parseInt(str.substring(1));
				found = pokedex.search(id, tmp);
			}
			// �������pmȫ��ͼ�����
			else if (Character.isDigit(str.charAt(0))) {
				Integer id = Integer.parseInt(str);
				found = pokedex.search(id, tmp);
			}
			// �������pm����
			else
				found = pokedex.search(str, tmp);

			print(found, tmp);
		}

		// ���������Ϣ
		public void print(boolean found, Pokemon pm) {
			// ���ҵ���pm
			if (found) {
				os.println("ȫ��ͼ����ţ�" + pm.getID());
				os.println(pm.getName());
				os.println("���ԣ�" + pm.getType());
				if (pm.getEvoluteWay().equals("0"))
					os.println(pm.getEvolution());
				else
					os.println(pm.getEvolution() + " " + pm.getEvoluteWay());
				if (!pm.getNextEvolution().equals("��"))
					os.println("�ɽ���Ϊ" + pm.getNextEvolution());
				os.println("end");
				os.flush();
				// �ڷ�����������������ҽ��
				interface1.outputArea
						.append(new Integer(num++).toString() + ".IP:" + clientIP + " ����" + inputString + " �ɹ�" + '\n');
			}
			// δ���ҵ���pm
			else {
				os.println("ͼ�������޸�pm");
				os.println("end");
				os.flush();
				interface1.outputArea
						.append(new Integer(num++).toString() + ".IP:" + clientIP + " ����" + inputString + " ʧ��" + '\n');
			}
		}
	}
}

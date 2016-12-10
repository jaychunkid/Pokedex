package socket;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;

//����˽���
public class ServerInterface extends JFrame {

	private static final long serialVersionUID = -1812082811419894631L;

	JTextArea outputArea; // Ψһһ��������������������Ϣ
	PokedexServer server;

	public ServerInterface() {
		// ���캯���жԽ���������ã�������Operation���з�����ʼ���з�����
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setBackground(Color.BLACK);
		outputArea.setFont(new Font("����", Font.PLAIN, 13));
		outputArea.setForeground(Color.PINK);
		outputArea.setLineWrap(true);

		// �����ദ���ڹر��¼�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		add(outputArea);
		setTitle("�������ն�");
		setBounds(800, 200, 400, 400);
		setVisible(true);

		server = new PokedexServer(this);
	}
	
	public static void main(String[] args){
		new ServerInterface();
	}
}

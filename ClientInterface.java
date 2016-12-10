package socket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//�ͻ��˽���
public class ClientInterface extends JFrame {

	private static final long serialVersionUID = 8180953184848213451L;
	
	JSplitPane sp;
	JPanel controlPanel, searchPanel;
	BorderLayout bl;
	FlowLayout fl;
	JTextArea outputArea; // �����Ϣ�����
	JTextField inputField; // ���������Ϣ�����
	JButton connect, search;
	JLabel jb; // ����װ����

	PokedexClient client;

	public ClientInterface() {
		// ���캯���жԽ����������
		sp = new JSplitPane();
		sp.setOrientation(JSplitPane.VERTICAL_SPLIT);
		sp.setDividerLocation(300);
		add(sp);

		outputArea = new JTextArea();
		outputArea.setColumns(15);
		outputArea.setRows(19);
		outputArea.setLineWrap(true);
		outputArea.setEditable(false);
		outputArea.setFont(new Font("����", Font.PLAIN, 13));
		outputArea.setForeground(Color.WHITE);
		outputArea.setBackground(Color.GRAY);
		sp.setLeftComponent(outputArea);

		controlPanel = new JPanel();
		controlPanel.setBackground(Color.BLACK);
		sp.setRightComponent(controlPanel);

		bl = new BorderLayout();
		bl.setVgap(8);
		controlPanel.setLayout(bl);

		connect = new JButton("���ӷ�����");
		connect.setFont(new Font("����", Font.BOLD, 14));
		connect.setBorderPainted(false);
		connect.setBackground(Color.RED);
		connect.setForeground(Color.WHITE);
		connect.addActionListener(new ConnectButtonListener());

		searchPanel = new JPanel();
		searchPanel.setBackground(Color.BLACK);

		fl = new FlowLayout();
		fl.setHgap(10);
		searchPanel.setLayout(fl);

		inputField = new JTextField();
		inputField.setColumns(13);
		inputField.setEditable(false); // �����ӷ�����ǰ��������

		search = new JButton("��ѯ");
		search.setFont(new Font("����", Font.BOLD, 14));
		search.setBorderPainted(false);
		search.setBackground(Color.RED);
		search.setForeground(Color.WHITE);
		search.addActionListener(new SearchButtonListener());

		searchPanel.add(inputField);
		searchPanel.add(search);

		jb = new JLabel("Pokedex");
		jb.setForeground(Color.white);
		jb.setHorizontalAlignment(JLabel.CENTER);

		controlPanel.add(connect, BorderLayout.NORTH);
		controlPanel.add(searchPanel, BorderLayout.CENTER);
		controlPanel.add(jb, BorderLayout.SOUTH);
		
		// �����ദ���ڹر��¼�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// ��������ӷ����������ȶϿ�����
				client.disconnect();
				System.exit(0);
			}
		});

		setBounds(400, 200, 250, 450);
		setTitle("�ڴ�ͼ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		client = new PokedexClient(this);
	}

	// �������Ӱ�ť�ĵ���¼�
	private class ConnectButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (connect.getActionCommand() == "���ӷ�����")
				client.connectServer();
			else
				client.disconnect();
		}
	}

	// ������Ұ�ť�ĵ���¼�
	private class SearchButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			client.search();
		}
	}
	
	public static void main(String[] args){
		new ClientInterface();
	}
}

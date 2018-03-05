package socket;

//���ÿ��pm����Ϣ
class Pokemon {

	private String id;  //pmȫ��ͼ�����
	private String name;  //pm��������
	private String type;  //pm����
	private String evolution;  //pm�Ľ����׶�
	private String evoluteWay;  //��������pm�ķ�ʽ�����pmδ������Ϊ��0��
	private String nextEvolution;  //��������һ�׶εķ�ʽ�����û����һ�׶���Ϊ���ޡ�

	public Pokemon() {
		id = null;  
		name = null;
		type = null;
		evolution = null;
		evoluteWay = null;
		nextEvolution = null;
	}

	public Pokemon(String num, String nam, String typ, String evo, String evw, String nev) {
		set(num, nam, typ, evo, evw, nev);
	}

	public void set(String num, String nam, String typ, String evo, String evw, String nev) {
		id = num;
		name = nam;
		type = typ;
		evolution = evo;
		evoluteWay = evw;
		nextEvolution = nev;
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getEvolution() {
		return evolution;
	}

	public String getEvoluteWay() {
		return evoluteWay;
	}

	public String getNextEvolution() {
		return nextEvolution;
	}
}

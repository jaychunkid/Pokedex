package socket;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.Map;

//�����ͼ���Ĺ���֧����������
class PokedexManager {

	ArrayList<Pokemon> pokemonList;
	Map<String, Pokemon> nameIndex;
	Map<Integer, Pokemon> idIndex;

	public PokedexManager(ArrayList<Pokemon> list) {
		pokemonList = list;
		nameIndex = new Hashtable<>();
		idIndex = new Hashtable<>();
		ListIterator<Pokemon> iter = pokemonList.listIterator();
		// ��ȡPokemon���ArrayList������name-Pokemon��Map��Ϊ��������
		while (iter.hasNext()) {
			Pokemon tmp = iter.next();
			nameIndex.put(tmp.getName(), tmp);
			idIndex.put(Integer.parseInt(tmp.getID().substring(1)), tmp);
		}
	}

	// ���pm���ֵ�����
	synchronized public boolean search(String name, Pokemon pm) {
		if (nameIndex.containsKey(name)) {
			Pokemon tmp = nameIndex.get(name);
			// ����ҵ�pm��Դ����pok���󸳸�pm����Ϣ
			pm.set(tmp.getID(), tmp.getName(), tmp.getType(), tmp.getEvolution(), tmp.getEvoluteWay(),
					tmp.getNextEvolution());
			return true;
		} else
			return false;
	}

	// ���pmȫ��ͼ����ŵ�����
	synchronized public boolean search(Integer id, Pokemon pm) {
		if (idIndex.containsKey(id)) {
			Pokemon tmp = idIndex.get(id);
			// ����ҵ�pm��Դ����pok���󸳸�pm����Ϣ
			pm.set(tmp.getID(), tmp.getName(), tmp.getType(), tmp.getEvolution(), tmp.getEvoluteWay(),
					tmp.getNextEvolution());
			return true;
		} else
			return false;
	}
}

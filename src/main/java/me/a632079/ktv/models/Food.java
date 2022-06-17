package me.a632079.ktv.models;

import java.util.HashMap;
import java.util.Map;

import javax.management.ValueExp;

public class Food {
	static String[] food = { "��������", "��ʦ������ţ���棨Ͱװ��", "����", "����", "ˮ��ƴ��" };
	static Map<String, Integer> foodFee;
	static {
		foodFee = new HashMap<String, Integer>();
		foodFee.put("��������", 2);
		foodFee.put("��ʦ������ţ���棨Ͱװ��", 5);
		foodFee.put("����", 4);
		foodFee.put("����", 4);
		foodFee.put("ˮ��ƴ��", 20);
	}

	/**
	 * ͨ��ʳƷ���ƻ�ȡ����
	 * 
	 * @param key
	 * @return
	 */
	public static int getFee(String key) {
		return foodFee.get(key);
	}

	/**
	 * ͨ��ʳƷ�����������ȡ����
	 * 
	 * @param key
	 * @return
	 */
	public static int getFee(int key) {
		return foodFee.get(food[key]);
	}

	public static String[] toComboBoxList() {
		return food.clone();
	}

	public static String getFeesDescription() {
		StringBuffer buff = new StringBuffer();
		for (String v : food) {
			buff.append(String.format("%s��%d Ԫ\n", v, getFee(v)));
		}
		return buff.toString();
	}
}

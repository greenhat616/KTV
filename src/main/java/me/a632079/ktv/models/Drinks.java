package me.a632079.ktv.models;

import java.util.HashMap;

public class Drinks {
	static String[] drinks = { "����ơ�ƣ�ƿװ��", "����ơ�ƣ�ƿװ��", "��˹��ơ��ƿװ��", "�ɺ����Ѿ�", "����ͷ", "�����ȣ�Сƿ��", "�ɿڿ��֣���װ��", "���¿��֣���װ��" };
	static HashMap<String, Integer> drinksFee;
	static {
		drinksFee = new HashMap<String, Integer>();
		drinksFee.put("����ơ�ƣ�ƿװ��", 20);
		drinksFee.put("����ơ�ƣ�ƿװ��", 20);
		drinksFee.put("��˹��ơ��ƿװ��", 15);
		drinksFee.put("�ɺ����Ѿ�", 80);
		drinksFee.put("����ͷ", 40);
		drinksFee.put("�����ȣ�Сƿ��", 5);
		drinksFee.put("�ɿڿ��֣���װ��", 5);
		drinksFee.put("���¿��֣���װ��", 5);
	}

	/**
	 * ͨ���������ƻ�ȡ����
	 * 
	 * @param key
	 * @return
	 */
	public static int getFee(String key) {
		return drinksFee.get(key);
	}

	/**
	 * ͨ�����������������ȡ����
	 * 
	 * @param key
	 * @return
	 */
	public static int getFee(int key) {
		return drinksFee.get(drinks[key]);
	}

	public static String[] toComboBoxList() {
		return drinks.clone();
	}

	public static String getFeesDescription() {
		StringBuffer buff = new StringBuffer();
		for (String v : drinks) {
			buff.append(String.format("%s��%d Ԫ\n", v, getFee(v)));
		}
		return buff.toString();
	}
}

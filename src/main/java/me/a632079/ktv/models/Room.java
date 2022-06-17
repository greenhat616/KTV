package me.a632079.ktv.models;

import java.util.HashMap;

public class Room {
	static String[] roomList = { "�����", "С��", "�а�", "���", "�����", "��ͳ��" };
	static HashMap<String, Integer> roomFee;
	static {
		// ��������: ÿСʱ���ѣ�����һСʱ��һСʱ���㣩
		roomFee = new HashMap<String, Integer>();
		roomFee.put("�����", 30);
		roomFee.put("С��", 50);
		roomFee.put("�а�", 60);
		roomFee.put("���", 70);
		roomFee.put("�����", 100);
		roomFee.put("��ͳ��", 150);
	};

	/**
	 * ͨ���������ƻ�ȡ����
	 * 
	 * @param key
	 * @return
	 */
	public static int getFee(String key) {
		return roomFee.get(key);
	}

	/**
	 * ͨ�����������������ȡ����
	 * 
	 * @param key
	 * @return
	 */
	public static int getFee(int key) {
		return roomFee.get(roomList[key]);
	}

	public static String[] toComboBoxList() {
		return roomList.clone();
	}

	public static String getFeesDescription() {
		StringBuffer buff = new StringBuffer();
		for (String v : roomList) {
			buff.append(String.format("%s��%d Ԫ/ʱ\n", v, getFee(v)));
		}
		return buff.toString();
	}
}
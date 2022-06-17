package me.a632079.ktv.models;

import java.util.HashMap;

public class Room {
	static String[] roomList = { "玲珑包", "小包", "中包", "大包", "商务包", "总统包" };
	static HashMap<String, Integer> roomFee;
	static {
		// 包间类型: 每小时付费（不足一小时按一小时计算）
		roomFee = new HashMap<String, Integer>();
		roomFee.put("玲珑包", 30);
		roomFee.put("小包", 50);
		roomFee.put("中包", 60);
		roomFee.put("大包", 70);
		roomFee.put("商务包", 100);
		roomFee.put("总统包", 150);
	};

	/**
	 * 通过包厢名称获取费用
	 * 
	 * @param key
	 * @return
	 */
	public static int getFee(String key) {
		return roomFee.get(key);
	}

	/**
	 * 通过包厢数组的索引获取费用
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
			buff.append(String.format("%s：%d 元/时\n", v, getFee(v)));
		}
		return buff.toString();
	}
}
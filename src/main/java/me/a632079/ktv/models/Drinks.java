package me.a632079.ktv.models;

import java.util.HashMap;

public class Drinks {
	static String[] drinks = { "百威啤酒（瓶装）", "蓝马啤酒（瓶装）", "汉斯干啤（瓶装）", "干红葡萄酒", "二锅头", "果粒橙（小瓶）", "可口可乐（听装）", "百事可乐（听装）" };
	static HashMap<String, Integer> drinksFee;
	static {
		drinksFee = new HashMap<String, Integer>();
		drinksFee.put("百威啤酒（瓶装）", 20);
		drinksFee.put("蓝马啤酒（瓶装）", 20);
		drinksFee.put("汉斯干啤（瓶装）", 15);
		drinksFee.put("干红葡萄酒", 80);
		drinksFee.put("二锅头", 40);
		drinksFee.put("果粒橙（小瓶）", 5);
		drinksFee.put("可口可乐（听装）", 5);
		drinksFee.put("百事可乐（听装）", 5);
	}

	/**
	 * 通过包厢名称获取费用
	 * 
	 * @param key
	 * @return
	 */
	public static int getFee(String key) {
		return drinksFee.get(key);
	}

	/**
	 * 通过包厢数组的索引获取费用
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
			buff.append(String.format("%s：%d 元\n", v, getFee(v)));
		}
		return buff.toString();
	}
}

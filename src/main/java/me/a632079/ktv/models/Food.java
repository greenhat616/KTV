package me.a632079.ktv.models;

import java.util.HashMap;
import java.util.Map;

import javax.management.ValueExp;

public class Food {
	static String[] food = { "卫龙辣条", "康师傅红烧牛肉面（桶装）", "瓜子", "花生", "水果拼盘" };
	static Map<String, Integer> foodFee;
	static {
		foodFee = new HashMap<String, Integer>();
		foodFee.put("卫龙辣条", 2);
		foodFee.put("康师傅红烧牛肉面（桶装）", 5);
		foodFee.put("瓜子", 4);
		foodFee.put("花生", 4);
		foodFee.put("水果拼盘", 20);
	}

	/**
	 * 通过食品名称获取费用
	 * 
	 * @param key
	 * @return
	 */
	public static int getFee(String key) {
		return foodFee.get(key);
	}

	/**
	 * 通过食品数组的索引获取费用
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
			buff.append(String.format("%s：%d 元\n", v, getFee(v)));
		}
		return buff.toString();
	}
}

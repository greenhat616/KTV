package me.a632079.ktv.models;

import java.time.LocalDateTime;

// 全局共享状态
public class State {
	public static LocalDateTime startDate = LocalDateTime.of(2022, 5, 22, 10, 13); // 默认值，用于测试
	public static String room = "玲珑包"; // 默认值，用于测试
	public static Player player = null; // 默认
}

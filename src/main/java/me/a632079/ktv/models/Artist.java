package me.a632079.ktv.models;

import java.util.Map;

// 歌手类
public class Artist {
	private int id;
	private String name;
	private String region; // 歌手地区
	private String shortName;
	private String avatar; // 海报、头像

	public Artist() {
		id = 0;
		name = "未知";
		region = "未知";
		shortName = "WZ";
		avatar = "";
	}

	public Artist(Map<String, Object> map) {
		id = (int) map.get("id");
		name = (String) map.get("name");
		region = (String) map.get("region");
		shortName = (String) map.get("short_name");
		avatar = (String) map.get("avatar");
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getRegion() {
		return region;
	}
}

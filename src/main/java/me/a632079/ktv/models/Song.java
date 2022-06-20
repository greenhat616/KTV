package me.a632079.ktv.models;

import java.util.Map;

// 歌曲类
public class Song {
    private int id;
    private String name;
    private Artist artist; // Artist 类
    private String language;
    private String shortName;
    private String style; // 音乐风格
    private String path; // 播放文件绝对路径

    public int getId() {
        return id;
    }

    Song(Map<String, Object> map) {
        id = (int) map.get("id");
        artist = (Artist) map.get("artist"); // 这里需要做一层转换
        language = (String) map.get("language");
        shortName = (String) map.get("short_name");
        style = (String) map.get("style");
        path = (String) map.get("path");
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getLanguage() {
        return language;
    }

    public String getShortName() {
        return shortName;
    }

    public String getStyle() {
        return style;
    }

    public String getPath() {
        return path;
    }
}

package me.a632079.ktv.models;

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

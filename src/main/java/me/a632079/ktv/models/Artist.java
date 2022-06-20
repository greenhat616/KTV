package me.a632079.ktv.models;

// 歌手类
public class Artist {
    private int id;
    private String name;
    private String region; // 歌手地区
    private String shorName;
    private String avatar;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shorName;
    }

    public String getAvatar() {
        return avatar;
    }
}

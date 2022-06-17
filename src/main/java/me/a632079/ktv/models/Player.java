package me.a632079.ktv.models;

import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

// 播放器类
public class Player {
	// 播放列表
	private ArrayList<Song> list = new ArrayList<>();
	private Song playingSong = null;
	private Clip instance = null;

	// 播放状态
	public int STATUS_WAITING = 0; // 空列表, 无播放任务
	public int STATUS_PLAYING = 1;
	public int STATUS_PASUED = 2;

	Player() throws LineUnavailableException {
		instance = AudioSystem.getClip(); // 实例化播放器类时，创造播放切片
	}

	public void play() {
	}

	public void pause() {
	}
}

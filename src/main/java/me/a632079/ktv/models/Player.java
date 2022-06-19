package me.a632079.ktv.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import cn.hutool.core.io.FileUtil;

// 播放器类
public class Player {
	// 播放列表
	private List<Song> list = new ArrayList<>();
	private List<Song> playedList = new ArrayList<>();
	private List<File> songs = new ArrayList<>(); // 测试用途
	private List<File> playedSongs = new ArrayList<>(); // 测试用途
	private Song playingSong = null;
	private Clip instance = null;
	private int pos = 0; // 播放位置
	// 播放状态
	public int STATUS_WAITING = 0; // 空列表, 无播放任务
	public int STATUS_PLAYING = 1;
	public int STATUS_PASUED = 2;

	public class ListEmptyException extends Exception {
		private static final long serialVersionUID = 8808545237357526747L;
	}

	// 是否暂停
	private int currentStatus = STATUS_WAITING; // 默认赋值为等待状态，当用户播放或者列表为空时也应该进入此状态

	public Player() throws LineUnavailableException {
		songs.addAll(FileUtil.loopFiles(new File("./workdir/songs/"))); // 测试目的

		instance = AudioSystem.getClip(); // 默认支持 wav，所以不做转换
	}

	public int getStatus() {
		return currentStatus;
	}

	public void play() throws ListEmptyException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (currentStatus == STATUS_PLAYING)
			return;
		if (currentStatus == STATUS_PASUED || pos != 0) {
			instance.setFramePosition(pos);
			pos = 0; // 重置状态
		} else if (!instance.isOpen()) { // 没有初始化音频流，需要先初始化
			System.out.print("12321312312");
			if (songs.isEmpty()) {
				throw new ListEmptyException();
			}
			AudioInputStream ais = AudioSystem.getAudioInputStream(songs.get(0)); // 获取播放列表中的第一首歌
			// AudioFormat format = ais.getFormat(); // 获得格式信息
			// DataLine.Info info = new DataLine.Info(Clip.class, format);
			// instance = (Clip) AudioSystem.getLine(info);
			instance.open(ais);
			ais.close();
		}
		System.out.println(instance.getFramePosition());
		instance.start();
		currentStatus = STATUS_PLAYING;
	}

	public void pause() {
		if (currentStatus != STATUS_PLAYING) {
			return;
		} else if (instance.isRunning()) {
			pos = instance.getFramePosition();
			instance.stop();
			currentStatus = STATUS_PASUED;
		}

	}

	/**
	 * 重播
	 */
	public void replay() {
		if (currentStatus == STATUS_WAITING)
			return; // 未初始化，不响应事件
		if (currentStatus == STATUS_PLAYING) {
			instance.stop();
		}
		instance.setFramePosition(0); // 设置到最初位置
		instance.start();
		currentStatus = STATUS_PLAYING;
	}

	/**
	 * 下一首
	 * 
	 * @throws ListEmptyException
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public void next() throws ListEmptyException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (songs.isEmpty() || songs.size() == 1) {
			throw new ListEmptyException(); // 空列表应该抛出错误
		} else if (currentStatus != STATUS_WAITING) {
			pause(); // 此方法内部已经处理了未开始播放的逻辑
			instance.close(); // 销毁播放流
			pos = 0; // pause 方法会修改 pos，这里需要重置
			currentStatus = STATUS_WAITING;
		}
		// System.out.print("111111111111111111");
		File songFile = songs.get(0);
		songs.remove(0); // 移出元素
		playedSongs.add(songFile); // 将之前的歌曲丢到已播列表
		play(); // 调用播放方法
	}

}

package me.a632079.ktv.models;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.*;

// 播放器类
public class Player {
	// 播放列表
	private List<Song> list        = new ArrayList<>();
	private BlockingQueue<File> songs = new ArrayBlockingQueue<>(50);
	private Song       playingSong = null;
	private Clip 	   instance = null;

	// 播放状态
	public int STATUS_WAITING = 0; // 空列表, 无播放任务
	public int STATUS_PLAYING = 1;
	public int STATUS_PASUED = 2;

	// 是否暂停
	private AtomicBoolean isPause = new AtomicBoolean(true);

	// 播放线程
	private Runnable musicThread;

	public Player() {
		songs.addAll(FileUtil.loopFiles(new File("./workdir/songs/")));

		musicThread = () -> {
			while(true) {
				AudioInputStream as;
				try {
					File song = songs.take();
					as = AudioSystem.getAudioInputStream(song);

					AudioFormat format = as.getFormat();

					SourceDataLine sdl = null;
					DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

					sdl = (SourceDataLine) AudioSystem.getLine(info);
					sdl.open(format);
					sdl.start();

					int nBytesRead = 0;
					byte[] abData = new byte[512];
					while (nBytesRead != -1) {
						if (!isPause.get()) {
							nBytesRead = as.read(abData, 0, abData.length);
							if (nBytesRead >= 0) {
								sdl.write(abData, 0, nBytesRead);
							}
						}
					}

					//关闭SourceDataLine
					sdl.drain();
					sdl.close();
				}catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		new Thread(musicThread).start();
	}

	public void play() {
		isPause.set(false);
	}

	public void pause() {
		isPause.set(true);
	}

	public void next() {
	}

}

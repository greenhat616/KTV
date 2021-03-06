package me.a632079.ktv.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// 播放器类
public class Player {
	// 播放列表
	private List<Song> list = new ArrayList<>();
	private List<Song> playedList = new ArrayList<>();
	private Map<String, Integer> songsStatistic = new HashMap<>(); // 歌曲播放信息统计表
	private Song playingSong = null; // 当前播放的歌曲对象
	private Clip instance = null; // Clip 实例
	private int pos = 0; // 播放位置, 仅用于暂停保存状态
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
		instance = AudioSystem.getClip(); // 默认支持 wav，所以不做转换
	}

	public void addSong(Song song) {
		list.add(song);
		songsStatistic.put(song.getName(), 0); // 统计表，初始化为 0
	}

	public Song getPlayingSong() {
		return playingSong;
	}

	public int getStatus() {
		return currentStatus;
	}

	public void play() throws ListEmptyException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (currentStatus != STATUS_WAITING && instance.getFramePosition() >= instance.getFrameLength()) {
			replay(); // 直接调用重播逻辑
			return;
		}
		if (currentStatus == STATUS_PLAYING)
			return;
		if (currentStatus == STATUS_PASUED || pos != 0) {
			instance.setFramePosition(pos);
			pos = 0; // 重置状态
		} else if (!instance.isOpen()) { // 没有初始化音频流，需要先初始化
			// System.out.print("12321312312");
			if (list.isEmpty()) { // 列表为空
				throw new ListEmptyException();
			}
			Song song = list.get(0); // 获取播放列表中的第一首歌
			File file = new File(song.getPath());
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			// AudioFormat format = ais.getFormat(); // 获得格式信息
			// DataLine.Info info = new DataLine.Info(Clip.class, format);
			instance = null; // 销毁之前的实例；如果不销毁的话，直接 open 会出现多个 `start`、`stop` 等事件。
			instance = (Clip) AudioSystem.getClip(); // 直接获得 Clip
			instance.open(ais);
			ais.close();
			instance.addLineListener(new LineListener() {

				@Override
				public void update(LineEvent event) {
					System.out.println("[Player] 触发事件：" + event.getType());
					System.out.println("当前进度：" + instance.getFramePosition());
					System.out.println("总进度：" + instance.getFrameLength());

					// 当事件为停止时，且位置为最后位置，自动播放下一首
					if (event.getType() == LineEvent.Type.STOP
							&& instance.getFramePosition() >= instance.getFrameLength()) {
						try {
							System.out.println("开始播放下一首...");
							next();
						} catch (ListEmptyException e) {
							// 不做处理，忽略
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			playingSong = song; // 当前播放的歌曲信息
			int count = songsStatistic.get(song.getName());
			songsStatistic.put(song.getName(), count + 1); // 增加播放次数
		}
		// System.out.println(instance.getFramePosition());
		System.out.println("正在播放：" + playingSong.getName());
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
		int count = songsStatistic.get(playingSong.getName());
		songsStatistic.put(playingSong.getName(), count + 1); // 增加播放次数
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
		if (list.isEmpty() || list.size() == 1) {
			throw new ListEmptyException(); // 空列表应该抛出错误
		} else if (currentStatus != STATUS_WAITING) {
			System.out.println("[next] 执行资源释放逻辑...");
			pause(); // 此方法内部已经处理了未开始播放的逻辑
			instance.close(); // 销毁播放流
			pos = 0; // pause 方法会修改 pos，这里需要重置
			currentStatus = STATUS_WAITING;
		}
		// System.out.print("111111111111111111");
		Song song = list.get(0);
		list.remove(0); // 移出元素
		playedList.add(song); // 将之前的歌曲丢到已播列表
		play(); // 调用播放方法
	}

	// Map 转 List 以便进行排序，网上找的算法。
	public List<String> getRankingStatisticMap() {
		List<String> buffList = new ArrayList<>();
		// 将 Map 转为 List<单个元素>
		List<Map.Entry<String, Integer>> mapList = new ArrayList<Map.Entry<String, Integer>>(songsStatistic.entrySet());
		Collections.sort(mapList, new Comparator<Map.Entry<String, Integer>>() {
			// 降序排序
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		for (Map.Entry<String, Integer> value : mapList) {
			buffList.add(String.format("%s: %d 次", value.getKey(), value.getValue()));
		}
		return buffList;
	}

	public List<Song> getPlayList() {
		return list;
	}

	public List<Song> getPlayedList() {
		return playedList;
	}

	public void removeSong(Song song) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		System.out.println("[Player.remove] 开始删除歌曲：" + song.getName());
		int buffCurrentStatus = currentStatus;
		if (playingSong.getId() == song.getId()) {
			System.out.println("[Player.remove] 发现歌曲正常播放，执行资源释放逻辑。");
			pause();
			instance.close();
			pos = 0; // pause 方法会修改 pos，这里需要重置
		}

		list.removeIf(e -> e.getId() == song.getId());

		currentStatus = STATUS_WAITING;

		if (buffCurrentStatus == STATUS_WAITING || buffCurrentStatus == STATUS_PASUED) { // 未初始化或者 暂停 都不执行播放逻辑
			return;
		}

		try {
			play(); // 如果当前正常播放，Play 方法会跳过处理
		} catch (ListEmptyException e) {
			// 忽略该错误
		}

	}

	public void topSong(int id) {
		list.add(0, list.remove(id));
	}

	public void topSong(Song song) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		int buffCurrentStatus = currentStatus;
		if (currentStatus != STATUS_WAITING) {
			pause();
			pos = 0;
		}

		instance.close(); // 销毁实例
		list.remove(song);
		list.add(0, song);

		currentStatus = STATUS_WAITING; // 重置状态
		if (buffCurrentStatus != STATUS_PLAYING) {
			return;
		}
		try {
			play(); // 如果当前正常播放，Play 方法会跳过处理
		} catch (ListEmptyException e) {
			// 忽略
		}
	}
}

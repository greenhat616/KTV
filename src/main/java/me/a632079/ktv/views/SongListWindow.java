package me.a632079.ktv.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import me.a632079.ktv.models.Player;
import me.a632079.ktv.models.Song;
import me.a632079.ktv.models.State;

public class SongListWindow {

	private JFrame frame;

	private Song selectedSong; // 已经选中的歌曲

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SongListWindow window = new SongListWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SongListWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Player player = State.player;

		frame = new JFrame();
		frame.setTitle("我的歌曲");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// 分页组件
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frame.getContentPane().add(tabbedPane);

		// 以点歌曲分页
		JPanel orderedSongPanel = new JPanel();
		tabbedPane.addTab("已点歌曲", null, orderedSongPanel, null);
		orderedSongPanel.setLayout(null);

		// 已唱歌曲分页
		JPanel playedSongPanel = new JPanel();
		tabbedPane.addTab("已唱歌曲", null, playedSongPanel, null);
		playedSongPanel.setLayout(null);

		// 已点歌曲列表
		JList<String> orderedSongList = new JList<String>();
		orderedSongList.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		orderedSongList.setModel(listModel);
		orderedSongList.setSize(450, 290);
		orderedSongPanel.add(orderedSongList);

		JPopupMenu songControlMenu = new JPopupMenu();
		JMenuItem topItem = new JMenuItem("置顶");
		topItem.addActionListener(e -> {
			if (selectedSong != null) {
				try {
					player.topSong(selectedSong);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				refreshOrderedSongList(orderedSongList, player);
			}
		});

		JMenuItem deleteItem = new JMenuItem("删除");
		deleteItem.addActionListener(e -> {
			if (selectedSong != null) {
				try {
					player.removeSong(selectedSong);
				} catch (Exception exception) {
					exception.printStackTrace();
				}

				refreshOrderedSongList(orderedSongList, player);
			}
		});

		songControlMenu.add(topItem);
		songControlMenu.add(deleteItem);

		// 监听右键事件，并且赋值 selectedSong
		orderedSongList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 判断右键事件
				if (e.getButton() == MouseEvent.BUTTON3) {
					int itemIndex = orderedSongList.locationToIndex(e.getPoint());
					System.out.println(itemIndex);
					orderedSongList.setSelectedIndex(itemIndex);
					selectedSong = player.getPlayList().get(itemIndex);
					songControlMenu.show(orderedSongList, e.getX(), e.getY());
				}
			}
		});

		// 已唱歌曲列表
		JList<String> playedSongList = new JList<String>();
		playedSongList.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		DefaultListModel<String> playedSongListModel = new DefaultListModel<>();
		playedSongList.setModel(playedSongListModel);
		playedSongList.setSize(450, 290);
		playedSongPanel.add(playedSongList);

		playedSongList.setListData(convertListToArr(player.getPlayedList()));

		refreshOrderedSongList(orderedSongList, player); // 更新已点歌曲列表
	}

	// Song List 转为 Array
	public String[] convertListToArr(List<Song> songs) {
		List<String> buffList = new ArrayList<>();
		for (Song song : songs) {
			buffList.add(String.format("%s - %s - %s - %s", song.getName(), song.getArtist().getName(), song.getStyle(),
					song.getLanguage()));
		}
		return buffList.toArray(new String[buffList.size()]);
	}

	// 刷新已点歌曲列表
	public void refreshOrderedSongList(JList<String> list, Player player) {
		list.setListData(convertListToArr(player.getPlayList()));
	}

}

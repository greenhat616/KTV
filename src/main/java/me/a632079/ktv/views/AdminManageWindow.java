package me.a632079.ktv.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import me.a632079.ktv.exceptions.ListEmptyException;
import me.a632079.ktv.models.Artist;
import me.a632079.ktv.models.Song;
import me.a632079.ktv.utils.JdbcHelper;

public class AdminManageWindow {

	private JFrame frame;
	private JTextField searchTextField;
	private JButton btnAddSongButton;
	private JButton btnAddArtistButton;
	private JList list;
	private DefaultListModel listModel;

	private java.util.List<Song> songList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminManageWindow window = new AdminManageWindow();
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
	public AdminManageWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6B4C\u66F2\u7BA1\u7406");
		frame.setBounds(100, 100, 638, 448);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		searchTextField = new JTextField();
		searchTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		searchTextField.setBounds(138, 29, 284, 34);
		frame.getContentPane().add(searchTextField);
		searchTextField.setColumns(10);

		JButton btnSearchButton = new JButton("\u641C\u7D22");
		btnSearchButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnSearchButton.setBounds(452, 29, 97, 34);
		btnSearchButton.addActionListener(e -> {
			String searchString = searchTextField.getText();
			if (searchString.length() > 0) {

				doListUpdate("%" + searchString + "%");
			} else {
				doListUpdate();
			}
		});
		frame.getContentPane().add(btnSearchButton);

		list = new JList();
		list.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		listModel = new DefaultListModel();
		doListUpdate();
		list.setModel(listModel);
		list.setBounds(10, 80, 604, 262);
		frame.getContentPane().add(list);

		btnAddSongButton = new JButton("\u6DFB\u52A0\u6B4C\u66F2");
		btnAddSongButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnAddSongButton.setBounds(96, 367, 140, 34);
		btnAddSongButton.addActionListener(e -> {
			AddSongWindow.startAsChildWindow(this);
		});
		frame.getContentPane().add(btnAddSongButton);

		btnAddArtistButton = new JButton("\u6DFB\u52A0\u6B4C\u624B");
		btnAddArtistButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnAddArtistButton.setBounds(317, 367, 140, 34);
		btnAddArtistButton.addActionListener(e -> {
			AddArtistWindow.main(null);
		});
		frame.getContentPane().add(btnAddArtistButton);
	}

	// 更新歌曲列表
	public void doListUpdate() {
		try {
			java.util.List<Map<String, Object>> songsData = JdbcHelper.query("SELECT * FROM `songs`");
			songList.clear(); // 清除容器
			listModel.clear();
			for (Map<String, Object> element : songsData) {
				int id = (int) element.get("artist_id");
				java.util.List<Map<String, Object>> artist = JdbcHelper.query("SELECT * FROM `artists` WHERE `id` = ?",
						id);
				if (artist.isEmpty()) {
					throw new ListEmptyException();
				}
				element.put("artist", new Artist(artist.get(0)));
				songList.add(new Song(element)); // 添加歌曲到队列
				listModel.addElement(element.get("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doListUpdate(String name) {
		try {
			java.util.List<Map<String, Object>> songsData = JdbcHelper
					.query("SELECT * FROM `songs` WHERE `name` LIKE ? OR `short_name` LIKE ?", name, name);
			songList.clear(); // 清除容器
			listModel.clear();
			for (Map<String, Object> element : songsData) {
				int id = (int) element.get("artist_id");
				java.util.List<Map<String, Object>> artist = JdbcHelper.query("SELECT * FROM `artists` WHERE `id` = ?",
						id);
				if (artist.isEmpty()) {
					throw new ListEmptyException();
				}
				element.put("artist", new Artist(artist.get(0)));
				songList.add(new Song(element)); // 添加歌曲到队列
				listModel.addElement(element.get("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

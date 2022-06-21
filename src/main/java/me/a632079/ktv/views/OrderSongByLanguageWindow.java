package me.a632079.ktv.views;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import me.a632079.ktv.models.Artist;
import me.a632079.ktv.models.Song;
import me.a632079.ktv.models.State;
import me.a632079.ktv.utils.JdbcHelper;

public class OrderSongByLanguageWindow {

	private JFrame frame;
	private List<Song> songList = new ArrayList<>();
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSongByLanguageWindow window = new OrderSongByLanguageWindow();
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
	public OrderSongByLanguageWindow() {
		initialize();
	}

	private void updateSongLanguage() {
		try {
			List<Map<String, Object>> resultList = JdbcHelper.query("SELECT DISTINCT(`language`) FROM `songs` ");
			comboBoxModel.removeAllElements(); // 删除所有元素
			comboBoxModel.addElement("不限");
			for (Map<String, Object> v : resultList) {
				comboBoxModel.addElement((String) v.get("language"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateSongList(String language) {
		try {
			List<Map<String, Object>> resultList = null;
			if (language == null || language.length() == 0) {
				resultList = JdbcHelper.query("SELECT * FROM `songs`");
			} else {
				resultList = JdbcHelper.query("SELECT * FROM `songs` WHERE `language` = ?", language);
			}
			listModel.clear();
			songList.clear();
			for (Map<String, Object> v : resultList) {
				// System.out.println(v);
				List<Map<String, Object>> artist = JdbcHelper.query("SELECT * FROM `artists` WHERE `id` = ?",
						v.get("artist_id"));
				v.put("artist", new Artist(artist.get(0))); // 忽略歌手不存在的错误
				Song song = new Song(v);
				songList.add(song);
				listModel.addElement(String.format("%s - %s - %s - %s", song.getName(), song.getArtist().getName(),
						song.getStyle(), song.getLanguage()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("语别选歌");
		frame.setBounds(100, 100, 671, 469);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblSongLanguageLabel = new JLabel("歌曲语种");
		lblSongLanguageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblSongLanguageLabel.setBounds(10, 369, 86, 44);
		frame.getContentPane().add(lblSongLanguageLabel);

		JComboBox<String> songLanguageComboBox = new JComboBox<>();
		songLanguageComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		songLanguageComboBox.setBounds(115, 369, 231, 44);
		songLanguageComboBox.setModel(comboBoxModel);
		frame.getContentPane().add(songLanguageComboBox);
		songLanguageComboBox.addActionListener(e -> {
			String lang = (String) songLanguageComboBox.getSelectedItem();
			System.out.println(lang);
			if (lang != "不限") {
				updateSongList(lang);
			} else {
				updateSongList(null);
			}

		});

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 637, 332);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		JList<String> list = new JList<>();
		list.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		list.setModel(listModel);
		panel.add(list, "name_324702141293100");
		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // 双击事件
					int index = list.locationToIndex(e.getPoint());
					int op = JOptionPane.showConfirmDialog(frame,
							String.format("确定添加【%s】到列表吗", songList.get(index).getName()), "确认添加",
							JOptionPane.YES_NO_OPTION);
					if (op == JOptionPane.YES_OPTION) {
						State.player.addSong(songList.get(index));
						JOptionPane.showMessageDialog(frame, "已成功添加歌曲到队列。");
					}
				}
			}
		});

		JButton btnResetButton = new JButton("重置");
		btnResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				songLanguageComboBox.setSelectedIndex(0); // 不限风格
				updateSongList(null);
			}
		});
		btnResetButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnResetButton.setBounds(374, 369, 128, 44);
		frame.getContentPane().add(btnResetButton);

		JButton btnSearchButton = new JButton("搜索");
		btnSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItemString = (String) songLanguageComboBox.getSelectedItem();
				if (selectedItemString != "不限") {
					updateSongList(selectedItemString);
				} else {
					updateSongList(null);
				}
			}
		});
		btnSearchButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnSearchButton.setBounds(519, 369, 128, 44);
		frame.getContentPane().add(btnSearchButton);

		updateSongList(null);
		updateSongLanguage(); // 初始化音乐风格
	}
}

package me.a632079.ktv.views;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import me.a632079.ktv.models.Artist;
import me.a632079.ktv.models.Song;
import me.a632079.ktv.models.State;
import me.a632079.ktv.utils.JdbcHelper;
import me.a632079.ktv.views.components.ImagePanel;

public class OrderSongByArtistWindow {

	private JFrame frame;
	private JTextField textField;
	private ImagePanel imagePanel;
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private List<Song> songList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSongByArtistWindow window = new OrderSongByArtistWindow();
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
	public OrderSongByArtistWindow() {
		initialize();
	}

	/**
	 * updateSongList
	 * 
	 * @param artistShortName
	 */
	private void updateSongList(String artistShortName) {
		try {
			List<Map<String, Object>> resultList = null;
			if (artistShortName == null || artistShortName.length() <= 0) {
				resultList = JdbcHelper.query("SELECT * FROM `artists`");
			} else {
				resultList = JdbcHelper.query("SELECT * FROM `artists` WHERE `short_name` LIKE ?",
						"%" + artistShortName + "%");
			}
			// 清除列表
			listModel.clear();
			songList.clear();

			// 迭代更新列表
			for (Map<String, Object> v : resultList) {
				Artist artist = new Artist(v);
				// 拉歌
				List<Map<String, Object>> songs = JdbcHelper.query("SELECT * FROM `songs` WHERE `artist_id` = ?",
						artist.getId());
				for (Map<String, Object> songMap : songs) {
					songMap.put("artist", artist);
					Song song = new Song(songMap);
					songList.add(song);
					listModel.addElement(String.format("%s - %s - %s - %s", song.getName(), song.getArtist().getName(),
							song.getStyle(), song.getLanguage()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("歌手点歌");
		frame.setBounds(100, 100, 726, 493);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 412, 365);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		JList list = new JList();
		list.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		list.setModel(listModel);
		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = list.locationToIndex(e.getPoint());
				try {
					imagePanel.setImage(songList.get(index).getArtist().getAvatar()); // 更新图片
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (e.getClickCount() == 2) { // 双击事件
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
		panel.add(list, "name_320512412888200");
		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);

		try {
			imagePanel = new ImagePanel("./workdir/posters/群星.jpg", 270, 365);
		} catch (Exception e) {
			e.printStackTrace();
		}
		imagePanel.setBounds(432, 10, 270, 365);
		frame.getContentPane().add(imagePanel);

		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textField.setBounds(10, 400, 412, 46);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		PlainDocument document = new PlainDocument() {
			private static final long serialVersionUID = 8418938668032019522L;

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str.matches("^[a-zA-Z]*")) {
					super.insertString(offs, str.toUpperCase(), a);
				}
			}
		};
		textField.setDocument(document);
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				updateSongList(textField.getText());
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton btnResetButton = new JButton("清除");
		btnResetButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnResetButton.setBounds(435, 396, 126, 50);
		frame.getContentPane().add(btnResetButton);
		btnResetButton.addActionListener(e -> {
			textField.setText(null);
			updateSongList(null);
		});

		JButton btnSearchButton = new JButton("查询");
		btnSearchButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnSearchButton.setBounds(571, 396, 131, 50);
		frame.getContentPane().add(btnSearchButton);
		btnSearchButton.addActionListener(e -> {
			updateSongList(textField.getText());
		});

		updateSongList(null); // 初始化时拉取歌
	}
}

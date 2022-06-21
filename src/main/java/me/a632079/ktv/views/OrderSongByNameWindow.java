package me.a632079.ktv.views;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class OrderSongByNameWindow {

	private JFrame frame;
	private JTextField textField;
	@SuppressWarnings("rawtypes")
	private JList list;
	private List<Song> songList = new ArrayList<>();
	private DefaultListModel<String> listModel = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSongByNameWindow window = new OrderSongByNameWindow();
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
	public OrderSongByNameWindow() {
		initialize();
	}

	private void updateSongList(String shortName) {
		try {
			List<Map<String, Object>> resultList = null;
			if (shortName == null || shortName.length() == 0) {
				resultList = JdbcHelper.query("SELECT * FROM `songs`");
			} else {
				resultList = JdbcHelper.query("SELECT * FROM `songs` WHERE `short_name` LIKE ?", "%" + shortName + "%");
			}
			listModel.clear();
			songList.clear();
			for (Map<String, Object> v : resultList) {
				List<Map<String, Object>> artist = JdbcHelper.query("SELECT * FROM `artists` WHERE `id` = ?",
						v.get("artist_id"));
				v.put("artist", new Artist(artist.get(0))); // 忽略歌手不存在的错误
				Song song = new Song(v);
				songList.add(song);
				listModel.addElement(String.format("%s - %s - %s - %s", song.getName(), song.getArtist().getName(),
						song.getStyle(), song.getLanguage()));
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("歌名点歌");
		frame.setBounds(100, 100, 483, 625);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		textField = new JTextField();
		textField.setBounds(10, 538, 341, 40);
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

		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateSongList(textField.getText());
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnNewButton.setBounds(361, 538, 98, 40);
		frame.getContentPane().add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 449, 519);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		list = new JList();
		list.setModel(listModel);
		panel.add(list, "name_240330485565900");
		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
						JOptionPane.showMessageDialog(list, "已成功添加歌曲到队列。");
					}
				}
			}
		});
		updateSongList(textField.getText());
		panel.add(scrollPane);
	}
}

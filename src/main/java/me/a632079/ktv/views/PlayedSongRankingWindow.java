package me.a632079.ktv.views;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import me.a632079.ktv.models.State;

public class PlayedSongRankingWindow {

	private JFrame frame;
	private JTextArea textArea;

	/**
	 * Launch the application
	 *
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PlayedSongRankingWindow window = new PlayedSongRankingWindow();
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
	public PlayedSongRankingWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("播放排行");
		frame.setBounds(100, 100, 451, 574);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setLocationRelativeTo(null);

		textArea = new JTextArea();
		textArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textArea.setEditable(false);
		frame.getContentPane().add(textArea, "name_235029190988500");
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane, "name_2350291909850123");
		updateRanking();
	}

	private void updateRanking() {
		textArea.setText(null);
		StringBuffer buffer = new StringBuffer();
		List<String> list = State.player.getRankingStatisticMap();
		for (String line : list) {
			buffer.append(line);
			buffer.append('\n');
		}
		textArea.setText(buffer.toString());
	}
}

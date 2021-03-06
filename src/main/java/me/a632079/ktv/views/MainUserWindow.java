package me.a632079.ktv.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import me.a632079.ktv.models.Player;
import me.a632079.ktv.models.State;
import me.a632079.ktv.views.components.ImagePanel;

public class MainUserWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUserWindow window = new MainUserWindow();
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
	public MainUserWindow() throws LineUnavailableException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws LineUnavailableException {
		State.player = new Player();

		frame = new JFrame();
		frame.setTitle("\u7528\u6237\u754C\u9762");
		frame.setBounds(100, 100, 763, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		ImagePanel panel = null;
		try {
			panel = new ImagePanel("./workdir/resources/main_user_panel_sized.png", 729, 249);
		} catch (IOException e) {
			e.printStackTrace();
		}

		panel.setBounds(10, 10, 729, 249);
		frame.getContentPane().add(panel);

		// 分类选歌
		JButton btnOrderSongButton = new JButton("\u5206\u7C7B\u9009\u6B4C");
		btnOrderSongButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnOrderSongButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		btnOrderSongButton.setBounds(44, 296, 117, 45);
		frame.getContentPane().add(btnOrderSongButton);
		btnOrderSongButton.addActionListener(e -> {
			OrderSongMainWindow.main(null);
		});

		// 已点歌曲
		JButton btnSongQueueButton = new JButton("\u5DF2\u70B9\u6B4C\u66F2");
		btnSongQueueButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		btnSongQueueButton.setBounds(221, 296, 117, 45);
		frame.getContentPane().add(btnSongQueueButton);

		btnSongQueueButton.addActionListener(e -> SongListWindow.main(null));

		// 播放排行
		JButton btnPlayRankingButton = new JButton("\u64AD\u653E\u6392\u884C");
		btnPlayRankingButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		btnPlayRankingButton.setBounds(400, 296, 117, 45);
		frame.getContentPane().add(btnPlayRankingButton);
		btnPlayRankingButton.addActionListener(e -> {
			PlayedSongRankingWindow.main(null);
		});

		JButton btnDrinksServiceButton = new JButton("\u9152\u6C34\u670D\u52A1");
		btnDrinksServiceButton.addActionListener(new ActionListener() {
			// 酒水服务
			public void actionPerformed(ActionEvent e) {
				DrinksServiceWindow.main(null);
			}
		});
		btnDrinksServiceButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		btnDrinksServiceButton.setBounds(579, 296, 117, 45);
		frame.getContentPane().add(btnDrinksServiceButton);

		// 下一首
		JButton btnNextSongButton = new JButton("\u4E0B\u4E00\u9996");
		btnNextSongButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		btnNextSongButton.setBounds(44, 376, 117, 45);
		frame.getContentPane().add(btnNextSongButton);

		// 重播
		JButton btnReplayButton = new JButton("\u91CD\u64AD");
		btnReplayButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		btnReplayButton.setBounds(221, 376, 117, 45);
		frame.getContentPane().add(btnReplayButton);

		// 播放 & 暂停
		JButton btnPauseOrContinueButton = new JButton("播放");
		btnPauseOrContinueButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		btnPauseOrContinueButton.setBounds(400, 376, 117, 45);
		frame.getContentPane().add(btnPauseOrContinueButton);

		// 事件处理，丢这里是因为这个按钮定义在上方
		btnPauseOrContinueButton.addActionListener(e -> { // 播放暂停按钮
			if (State.player.getStatus() != State.player.STATUS_PLAYING) {
				try {
					State.player.play();
				} catch (Player.ListEmptyException ex) {
					JOptionPane.showMessageDialog(frame, "播放列表为空！");
					return;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				btnPauseOrContinueButton.setText("暂停");
			} else {
				State.player.pause();
				btnPauseOrContinueButton.setText("播放");
			}
		});

		btnReplayButton.addActionListener(e -> { // 重播按钮处理事件
			try {
				Thread.sleep(200); // 延迟 200 毫秒
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (State.player.getStatus() == State.player.STATUS_PASUED) {
				btnPauseOrContinueButton.setText("暂停");
			}
			State.player.replay();
		});

		btnNextSongButton.addActionListener(e -> {
			try {
				State.player.next();
				btnPauseOrContinueButton.setText("暂停");
			} catch (Player.ListEmptyException ex) {
				JOptionPane.showMessageDialog(frame, "播放列表为空！");
				return;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		// 列表
		JButton btnPlayListButton = new JButton("\u5217\u8868");
		btnPlayListButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
		btnPlayListButton.setBounds(579, 376, 117, 45);
		frame.getContentPane().add(btnPlayListButton);

		btnPlayListButton.addActionListener(e -> SongListWindow.main(null));
	}
}

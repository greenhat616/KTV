package me.a632079.ktv.views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

public class OrderSongMainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSongMainWindow window = new OrderSongMainWindow();
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
	public OrderSongMainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("分类点歌");
		frame.getContentPane().setFont(new Font("微软雅黑", Font.PLAIN, 15));
		frame.getContentPane().setLayout(null);

		JButton btnOrderByArtistButton = new JButton("歌手点歌");
		btnOrderByArtistButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnOrderByArtistButton.setBounds(113, 49, 176, 37);
		frame.getContentPane().add(btnOrderByArtistButton);

		JButton btnOrderByNameButton = new JButton("歌名点歌");
		btnOrderByNameButton.addActionListener(e -> {
			OrderSongByNameWindow.main(null);
		});
		btnOrderByNameButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnOrderByNameButton.setBounds(113, 96, 176, 37);
		frame.getContentPane().add(btnOrderByNameButton);

		JButton btnOrderByStyleButton = new JButton("风格点歌");
		btnOrderByStyleButton.addActionListener(e -> {

		});
		btnOrderByStyleButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnOrderByStyleButton.setBounds(113, 169, 176, 37);
		frame.getContentPane().add(btnOrderByStyleButton);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
}

package me.a632079.ktv.views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OrderSongByLanguageWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 671, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(0, 0, 657, 300);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("返回");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(542, 356, 105, 44);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("清除");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(429, 356, 105, 44);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("退格");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(314, 356, 105, 44);
		frame.getContentPane().add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("歌曲首字母");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 356, 105, 44);
		frame.getContentPane().add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(115, 356, 177, 44);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}

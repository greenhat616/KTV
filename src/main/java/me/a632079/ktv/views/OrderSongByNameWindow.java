package me.a632079.ktv.views;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

public class OrderSongByNameWindow {

	private JFrame frame;
	private JTextField textField;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("歌名点歌");
		frame.setBounds(100, 100, 483, 625);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JList list = new JList();
		list.setBounds(10, 10, 449, 507);
		frame.getContentPane().add(list);

		textField = new JTextField();
		textField.setBounds(10, 538, 341, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(361, 538, 98, 40);
		frame.getContentPane().add(btnNewButton);
	}
}

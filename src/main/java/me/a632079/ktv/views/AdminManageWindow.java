package me.a632079.ktv.views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

public class AdminManageWindow {

	private JFrame frame;
	private JTextField searchTextField;
	private JButton btnAddSongButton;
	private JButton btnAddArtistButton;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		searchTextField = new JTextField();
		searchTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		searchTextField.setBounds(138, 29, 284, 34);
		frame.getContentPane().add(searchTextField);
		searchTextField.setColumns(10);

		JButton btnSearchButton = new JButton("\u641C\u7D22");
		btnSearchButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnSearchButton.setBounds(452, 29, 97, 34);
		frame.getContentPane().add(btnSearchButton);

		JList list = new JList();
		list.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "1", "2", "3" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 80, 604, 262);
		frame.getContentPane().add(list);

		btnAddSongButton = new JButton("\u6DFB\u52A0\u6B4C\u66F2");
		btnAddSongButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnAddSongButton.setBounds(96, 367, 140, 34);
		frame.getContentPane().add(btnAddSongButton);

		btnAddArtistButton = new JButton("\u6DFB\u52A0\u6B4C\u624B");
		btnAddArtistButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnAddArtistButton.setBounds(317, 367, 140, 34);
		frame.getContentPane().add(btnAddArtistButton);
	}
}

package me.a632079.ktv.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddArtistWindow {

	private JFrame frame;
	private JTextField artistNameTextField;
	private JTextField artistRegionTextField;
	private JTextField artistShortNameTextField;
	private JTextField artistPosterTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddArtistWindow window = new AddArtistWindow();
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
	public AddArtistWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6DFB\u52A0\u6B4C\u624B");
		frame.getContentPane().setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		frame.setBounds(100, 100, 463, 481);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblArtistNameLabel = new JLabel("\u6B4C\u624B\u540D");
		lblArtistNameLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblArtistNameLabel.setBounds(59, 58, 79, 56);
		frame.getContentPane().add(lblArtistNameLabel);

		artistNameTextField = new JTextField();
		artistNameTextField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblArtistNameLabel.setLabelFor(artistNameTextField);
		artistNameTextField.setBounds(151, 58, 180, 56);
		frame.getContentPane().add(artistNameTextField);
		artistNameTextField.setColumns(10);

		artistRegionTextField = new JTextField();
		artistRegionTextField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		artistRegionTextField.setColumns(10);
		artistRegionTextField.setBounds(151, 125, 180, 56);
		frame.getContentPane().add(artistRegionTextField);

		JLabel lblArtistRegionLabel = new JLabel("\u6B4C\u624B\u5730\u533A");
		lblArtistRegionLabel.setLabelFor(artistRegionTextField);
		lblArtistRegionLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblArtistRegionLabel.setBounds(59, 125, 79, 56);
		frame.getContentPane().add(lblArtistRegionLabel);

		artistShortNameTextField = new JTextField();
		artistShortNameTextField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		artistShortNameTextField.setColumns(10);
		artistShortNameTextField.setBounds(151, 191, 180, 56);
		frame.getContentPane().add(artistShortNameTextField);

		JLabel lblArtistShortNameLabel = new JLabel("\u6B4C\u624B\u7F29\u5199");
		lblArtistShortNameLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblArtistShortNameLabel.setBounds(59, 191, 79, 56);
		frame.getContentPane().add(lblArtistShortNameLabel);

		artistPosterTextField = new JTextField();
		artistPosterTextField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		artistPosterTextField.setColumns(10);
		artistPosterTextField.setBounds(151, 257, 108, 56);
		frame.getContentPane().add(artistPosterTextField);

		JLabel lblArtistPosterLabel = new JLabel("\u6D77\u62A5");
		lblArtistPosterLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblArtistPosterLabel.setBounds(59, 257, 79, 56);
		frame.getContentPane().add(lblArtistPosterLabel);

		JButton btnFileSelectButton = new JButton("\u9009\u62E9");
		btnFileSelectButton.addActionListener(new ActionListener() {
			// Ñ¡ÔñÎÄ¼þ
			public void actionPerformed(ActionEvent e) {
				showFileSelectorDialog(frame, artistPosterTextField);
			}
		});
		btnFileSelectButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnFileSelectButton.setBounds(275, 262, 97, 51);
		frame.getContentPane().add(btnFileSelectButton);

		JButton btnConfirmButton = new JButton("\u6DFB\u52A0");
		btnConfirmButton.addActionListener(new ActionListener() {
			// Ìí¼Ó¸èÊÖ
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfirmButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnConfirmButton.setBounds(85, 360, 108, 51);
		frame.getContentPane().add(btnConfirmButton);

		JButton btnResetButton = new JButton("\u91CD\u7F6E");
		btnResetButton.addActionListener(new ActionListener() {
			// ÖØÖÃÄÚÈÝ
			public void actionPerformed(ActionEvent e) {
				artistNameTextField.setText(null);
				artistRegionTextField.setText(null);
				artistShortNameTextField.setText(null);
				artistPosterTextField.setText(null);
			}
		});
		btnResetButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnResetButton.setBounds(234, 360, 108, 51);
		frame.getContentPane().add(btnResetButton);
	}

	private void showFileSelectorDialog(JFrame frame, JTextField input) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setMultiSelectionEnabled(false); // Ö»ÄÜÑ¡ÔñÒ»¸öÎÄ¼þ
		fileChooser.setFileFilter(new FileNameExtensionFilter("Í¼Æ¬ÎÄ¼þ(*.png, *.jpg, *.jpeg)", "png", "jpg", "jpeg")); // ¿ÉÑ¡ÔñµÄÎÄ¼þ
		int result = fileChooser.showOpenDialog(frame); // ¶ÂÈû´°¿Ú

		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			input.setText(file.getAbsolutePath());
		}
	}
}

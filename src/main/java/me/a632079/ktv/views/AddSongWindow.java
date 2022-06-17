package me.a632079.ktv.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class AddSongWindow {

	private JFrame frame;
	private JTextField songNameTextField;
	private JTextField songArtistTextField;
	private JTextField songStyleTextField;
	private JTextField songLanguageTextField;
	private JTextField songShortNameTextField;
	private JTextField songPathTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSongWindow window = new AddSongWindow();
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
	public AddSongWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6DFB\u52A0\u6B4C\u66F2");
		frame.setBounds(100, 100, 492, 493);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblSongNameLabel = new JLabel("\u6B4C\u66F2\u540D");
		lblSongNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblSongNameLabel.setBounds(69, 23, 58, 34);
		frame.getContentPane().add(lblSongNameLabel);
		
		songNameTextField = new JTextField();
		lblSongNameLabel.setLabelFor(songNameTextField);
		songNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		songNameTextField.setBounds(150, 21, 218, 36);
		frame.getContentPane().add(songNameTextField);
		songNameTextField.setColumns(10);
		
		JLabel lblSongArtistLabel = new JLabel("\u6F14\u5531\u8005");
		lblSongArtistLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblSongArtistLabel.setBounds(69, 82, 58, 34);
		frame.getContentPane().add(lblSongArtistLabel);
		
		songArtistTextField = new JTextField();
		lblSongArtistLabel.setLabelFor(songArtistTextField);
		songArtistTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		songArtistTextField.setColumns(10);
		songArtistTextField.setBounds(150, 80, 218, 36);
		frame.getContentPane().add(songArtistTextField);
		
		JLabel lblSongStyleLabel = new JLabel("\u6B4C\u66F2\u98CE\u683C");
		lblSongStyleLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblSongStyleLabel.setBounds(66, 145, 61, 34);
		frame.getContentPane().add(lblSongStyleLabel);
		
		songStyleTextField = new JTextField();
		lblSongStyleLabel.setLabelFor(songStyleTextField);
		songStyleTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		songStyleTextField.setColumns(10);
		songStyleTextField.setBounds(150, 143, 218, 36);
		frame.getContentPane().add(songStyleTextField);
		
		JLabel lblSongLanguageLabel = new JLabel("\u6B4C\u66F2\u8BED\u8A00");
		lblSongLanguageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblSongLanguageLabel.setBounds(66, 209, 61, 34);
		frame.getContentPane().add(lblSongLanguageLabel);
		
		songLanguageTextField = new JTextField();
		lblSongLanguageLabel.setLabelFor(songLanguageTextField);
		songLanguageTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		songLanguageTextField.setColumns(10);
		songLanguageTextField.setBounds(150, 207, 218, 36);
		frame.getContentPane().add(songLanguageTextField);
		
		JLabel lblSongShortNameLabel = new JLabel("\u6B4C\u540D\u7F29\u5199");
		lblSongShortNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblSongShortNameLabel.setBounds(66, 267, 61, 34);
		frame.getContentPane().add(lblSongShortNameLabel);
		
		songShortNameTextField = new JTextField();
		lblSongShortNameLabel.setLabelFor(songShortNameTextField);
		songShortNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		songShortNameTextField.setColumns(10);
		songShortNameTextField.setBounds(150, 265, 218, 36);
		frame.getContentPane().add(songShortNameTextField);
		
		JLabel lblSongPathLabel = new JLabel("\u6B4C\u540D\u6587\u4EF6");
		lblSongPathLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblSongPathLabel.setBounds(66, 333, 72, 34);
		frame.getContentPane().add(lblSongPathLabel);
		
		songPathTextField = new JTextField();
		lblSongPathLabel.setLabelFor(songPathTextField);
		songPathTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		songPathTextField.setColumns(10);
		songPathTextField.setBounds(150, 331, 155, 36);
		frame.getContentPane().add(songPathTextField);
		
		JButton btnSelectFileButton = new JButton("\u9009\u62E9");
		btnSelectFileButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnSelectFileButton.addActionListener(new ActionListener() {
			// 触发文件选择对话框
			public void actionPerformed(ActionEvent e) { 
				showFileSelectorDialog(frame, songPathTextField);
			}
		});
		btnSelectFileButton.setBounds(336, 333, 78, 34);
		frame.getContentPane().add(btnSelectFileButton);
		
		JButton btnConfirmButton = new JButton("\u6DFB\u52A0");
		btnConfirmButton.addActionListener(new ActionListener() {
			// 添加歌曲
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnConfirmButton.setBounds(122, 399, 78, 34);
		frame.getContentPane().add(btnConfirmButton);
		
		JButton btnResetButton = new JButton("\u91CD\u7F6E");
		btnResetButton.addActionListener(new ActionListener() {
			// 重置
			public void actionPerformed(ActionEvent e) {
				songNameTextField.setText(null);
				songArtistTextField.setText(null);
				songLanguageTextField.setText(null);
				songStyleTextField.setText(null);
				songShortNameTextField.setText(null);
				songPathTextField.setText(null);
			}
		});
		btnResetButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnResetButton.setBounds(246, 399, 78, 34);
		frame.getContentPane().add(btnResetButton);
	}

	private void showFileSelectorDialog(JFrame frame, JTextField input) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false); // 只能选择一个文件
        fileChooser.setFileFilter(new FileNameExtensionFilter("音频文件(*.mp3, *.wav, *.ogg, *.ape, *.flac, *.acc)", "mp3", "wav", "ogg", "ape", "flac", "acc")); // 可选择的文件
        int result = fileChooser.showOpenDialog(frame); // 堵塞窗口

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            input.setText(file.getAbsolutePath());
        }
	}
}

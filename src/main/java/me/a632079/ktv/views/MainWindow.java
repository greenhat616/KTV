package me.a632079.ktv.views;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import me.a632079.ktv.models.Room;
import me.a632079.ktv.models.State;

public class MainWindow {

	private JFrame frmKtv;
	private JTextField accountTextField;
	private JTextField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmKtv.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 创建 MainWindow
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * 初始化窗口组件
	 */
	private void initialize() {
		// 定义窗口
		frmKtv = new JFrame();
		frmKtv.setTitle("KTV  \u70B9\u6B4C\u7CFB\u7EDF");
		frmKtv.setBounds(100, 100, 662, 497);
		frmKtv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKtv.getContentPane().setLayout(new CardLayout(0, 0));
		frmKtv.setLocationRelativeTo(null);

		// 分页组件
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frmKtv.getContentPane().add(tabbedPane, "name_69055496515499");

		// 消费者分页
		JPanel userPanel = new JPanel();
		tabbedPane.addTab("\u6D88\u8D39\u8005", null, userPanel, null);
		userPanel.setLayout(null);

		// 包厢选择标签
		JLabel lblConsumerRoomLabel = new JLabel("\u5305\u53A2\u9009\u62E9");
		lblConsumerRoomLabel.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		lblConsumerRoomLabel.setBounds(105, 69, 177, 75);
		userPanel.add(lblConsumerRoomLabel);

		// 包厢选择 选择器
		String[] roomList = Room.toComboBoxList();
		JComboBox roomSelectComboBox = new JComboBox(roomList);
		roomSelectComboBox.setSelectedIndex(0);
		lblConsumerRoomLabel.setLabelFor(roomSelectComboBox);
		roomSelectComboBox.setBounds(305, 82, 152, 48);
		userPanel.add(roomSelectComboBox);

		// 收费标准按钮
		JButton btnFeeScale = new JButton("\u6536\u8D39\u6807\u51C6");
		btnFeeScale.addActionListener(new ActionListener() {
			// 监听点击事件，打开收费标准窗口
			public void actionPerformed(ActionEvent e) {
				FeeScaleWindow.main(null);
			}
		});
		btnFeeScale.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnFeeScale.setBounds(236, 185, 152, 48);
		userPanel.add(btnFeeScale);

		// 开始按钮
		JButton btnStart = new JButton("\u5F00\u59CB\u5531\u6B4C");
		btnStart.addActionListener(new ActionListener() {
			// 开始点歌
			public void actionPerformed(ActionEvent e) {
				// 初始化共享状态，并且启动用户窗口
				State.room = (String) roomSelectComboBox.getSelectedItem();
				State.startDate = LocalDateTime.now();
				MainUserWindow.main(null);
				frmKtv.dispose(); // 销毁当前窗口
			}
		});
		btnStart.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnStart.setBounds(236, 263, 152, 48);
		userPanel.add(btnStart);

		// 退出按钮
		JButton btnExit = new JButton("\u9000\u51FA");
		btnExit.addActionListener(new ActionListener() {
			/**
			 * 触发退出按钮退出事件
			 */
			public void actionPerformed(ActionEvent e) {
				frmKtv.dispose();
			}
		});
		btnExit.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnExit.setBounds(236, 338, 152, 48);
		userPanel.add(btnExit);

		// 管理员分页
		JPanel adminPanel = new JPanel();
		tabbedPane.addTab("\u7BA1\u7406\u5458", null, adminPanel, null);
		adminPanel.setLayout(null);

		JLabel lblAdminLoginLabel = new JLabel("\u7BA1\u7406\u5458\u767B\u5F55");
		lblAdminLoginLabel.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		lblAdminLoginLabel.setBounds(210, 54, 223, 75);
		adminPanel.add(lblAdminLoginLabel);

		JLabel lblAdminAccountLabel = new JLabel("\u8D26\u53F7");
		lblAdminAccountLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblAdminAccountLabel.setBounds(187, 179, 48, 36);
		adminPanel.add(lblAdminAccountLabel);

		// 用户名
		accountTextField = new JTextField();
		accountTextField.setBounds(258, 179, 207, 36);
		adminPanel.add(accountTextField);
		accountTextField.setColumns(10);

		// 密码
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(258, 241, 207, 36);
		adminPanel.add(passwordTextField);

		JLabel lblAdminPasswordLabel = new JLabel("\u5BC6\u7801");
		lblAdminPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblAdminPasswordLabel.setBounds(187, 241, 48, 36);
		adminPanel.add(lblAdminPasswordLabel);

		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener() {
			// 登录事件
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		btnLogin.setBounds(246, 330, 152, 48);
		adminPanel.add(btnLogin);
	}
}

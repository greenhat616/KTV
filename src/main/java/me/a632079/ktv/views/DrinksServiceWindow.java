package me.a632079.ktv.views;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import me.a632079.ktv.models.Drinks;
import me.a632079.ktv.models.Food;
import me.a632079.ktv.models.Room;
import me.a632079.ktv.models.State;

public class DrinksServiceWindow {

	private JFrame frame;
	private String foodSelectedItem = null;
	private String drinksSelectedItem = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrinksServiceWindow window = new DrinksServiceWindow();
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
	public DrinksServiceWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frame.setTitle("\u9152\u6C34\u670D\u52A1");
		frame.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		frame.setBounds(100, 100, 471, 553);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		tabbedPane.setBounds(10, 10, 437, 428);
		frame.getContentPane().add(tabbedPane);

		// 食物面版
		JPanel foodPanel = new JPanel();
		tabbedPane.addTab("\u98DF\u7269", null, foodPanel, null);
		foodPanel.setLayout(new CardLayout(0, 0));
		JList<String> foodListComponent = new JList<String>();
		foodListComponent.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		DefaultListModel<String> foodListModel = new DefaultListModel<String>() {
			private static final long serialVersionUID = 1L;

			{
				String[] foodList = Food.toComboBoxList();
				for (String food : foodList) {
					addElement(food);
				}
			}
		};
		JPopupMenu foodPopupMenu = new JPopupMenu();
		JMenuItem foodPopupMenuBuyItem = new JMenuItem("购买");
		foodPopupMenuBuyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(foodListComponent,
						String.format("确定购买【%s】吗？\n您将支付 %d 元", foodSelectedItem, Food.getFee(foodSelectedItem)), "确认购买",
						JOptionPane.YES_NO_OPTION);
				if (op == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(foodListComponent, "您已成功购买，稍后送到！");
				}
			}
		});
		foodPopupMenu.add(foodPopupMenuBuyItem);
		foodListComponent.setModel(foodListModel);
		foodListComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // 双击事件
					String key = foodListComponent.getModel()
							.getElementAt(foodListComponent.locationToIndex(e.getPoint()));
					int op = JOptionPane.showConfirmDialog(foodListComponent,
							String.format("确定购买【%s】吗？\n您将支付 %d 元", key, Food.getFee(key)), "确认购买",
							JOptionPane.YES_NO_OPTION);
					if (op == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(foodListComponent, "您已成功购买，稍后送到！");
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) { // 右键
					int itemIndex = foodListComponent.locationToIndex(e.getPoint());
					foodListComponent.setSelectedIndex(itemIndex); // 设置焦点
					foodSelectedItem = foodListModel.getElementAt(itemIndex); // 设置选中 Item
					foodPopupMenu.show(foodListComponent, e.getX(), e.getY());
				}
			}
		});

		foodPanel.add(foodListComponent, "name_42299243385900");
		JScrollPane foodListComponentScrollPane = new JScrollPane(foodListComponent,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		foodPanel.add(foodListComponentScrollPane);

		// 酒水
		JPanel drinksPanel = new JPanel();
		tabbedPane.addTab("\u9152\u6C34", null, drinksPanel, null);
		drinksPanel.setLayout(new CardLayout(0, 0));

		JList<String> drinkListComponent = new JList<String>();
		drinkListComponent.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		DefaultListModel<String> drinksListModel = new DefaultListModel<String>() {
			private static final long serialVersionUID = 1L;

			{
				String[] drinksList = Drinks.toComboBoxList();
				for (String drink : drinksList) {
					addElement(drink);
				}
			}
		};
		JPopupMenu drinksPopupMenu = new JPopupMenu();
		JMenuItem drinksPopupMenuBuyItem = new JMenuItem("购买");
		drinksPopupMenuBuyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(drinksSelectedItem);
				int op = JOptionPane.showConfirmDialog(drinkListComponent,
						String.format("确定购买【%s】吗？\n您将支付 %d 元", drinksSelectedItem, Drinks.getFee(drinksSelectedItem)),
						"确认购买", JOptionPane.YES_NO_OPTION);
				if (op == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(drinkListComponent, "您已成功购买，稍后送到！");
				}
			}
		});
		drinksPopupMenu.add(drinksPopupMenuBuyItem);
		drinkListComponent.setModel(drinksListModel);
		drinkListComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // 双击事件
					String key = drinkListComponent.getModel()
							.getElementAt(drinkListComponent.locationToIndex(e.getPoint()));
					int op = JOptionPane.showConfirmDialog(drinkListComponent,
							String.format("确定购买【%s】吗？\n您将支付 %d 元", key, Drinks.getFee(key)), "确认购买",
							JOptionPane.YES_NO_OPTION);
					if (op == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(drinkListComponent, "您已成功购买，稍后送到！");
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) { // 右键
					int itemIndex = drinkListComponent.locationToIndex(e.getPoint());
					drinkListComponent.setSelectedIndex(itemIndex); // 设置焦点
					drinksSelectedItem = drinksListModel.getElementAt(itemIndex); // 设置选中 Item
					drinksPopupMenu.show(drinkListComponent, e.getX(), e.getY());
				}
			}
		});

		drinksPanel.add(drinkListComponent, "name_42307998010600");
		JScrollPane drinkListComponentScrollPane = new JScrollPane(drinkListComponent,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		drinksPanel.add(drinkListComponentScrollPane);

		JButton btnServiceRequestButton = new JButton("\u8BF7\u6C42\u670D\u52A1");
		btnServiceRequestButton.addActionListener(new ActionListener() {
			// 请求服务
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "请稍后，服务人员马上就到。");
			}
		});
		btnServiceRequestButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		btnServiceRequestButton.setBounds(34, 451, 182, 23);
		frame.getContentPane().add(btnServiceRequestButton);

		JButton btnBackToMainPageButton = new JButton("\u8FD4\u56DE\u4E3B\u754C\u9762");
		btnBackToMainPageButton.addActionListener(new ActionListener() {
			// 返回主界面
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBackToMainPageButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		btnBackToMainPageButton.setBounds(246, 448, 174, 23);
		frame.getContentPane().add(btnBackToMainPageButton);

		JButton btnGetConsumeTimeButton = new JButton("\u67E5\u770B\u6D88\u8D39\u65F6\u95F4");
		btnGetConsumeTimeButton.addActionListener(new ActionListener() {
			// 查看消费时间
			public void actionPerformed(ActionEvent e) {
				LocalDateTime currentTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				JOptionPane.showMessageDialog(frame, String.format(
						"您好。\n包厢等级：%s\n计费模式：%d 元/时\n入场时间：%s\n已使用 %,d 时, %d 分。计 %,d 元（不满一小时按一小时计算）", State.room,
						Room.getFee(State.room), State.startDate.format(formatter),
						Duration.between(State.startDate, currentTime).toHours(),
						Duration.between(State.startDate, currentTime).toMinutesPart(),
						(Duration.between(State.startDate, currentTime).toHours() + 1) * Room.getFee(State.room)),
						"消费时间", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnGetConsumeTimeButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		btnGetConsumeTimeButton.setBounds(34, 484, 182, 23);
		frame.getContentPane().add(btnGetConsumeTimeButton);

		JButton btnViewFeeScaleButton = new JButton("\u6536\u8D39\u6807\u51C6");
		btnViewFeeScaleButton.addActionListener(new ActionListener() {
			// 查看收费标准
			public void actionPerformed(ActionEvent e) {
				FeeScaleWindow.main(null);
			}
		});
		btnViewFeeScaleButton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		btnViewFeeScaleButton.setBounds(246, 481, 174, 23);
		frame.getContentPane().add(btnViewFeeScaleButton);
	}

}

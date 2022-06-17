package me.a632079.ktv.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextArea;

import me.a632079.ktv.models.Drinks;
import me.a632079.ktv.models.Food;
import me.a632079.ktv.models.Room;

import javax.swing.JScrollPane;

public class FeeScaleWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeeScaleWindow window = new FeeScaleWindow();
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
	public FeeScaleWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6536\u8D39\u6807\u51C6");
		frame.setBounds(100, 100, 463, 551);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setLocationRelativeTo(null);
		
		JTabbedPane feeTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		feeTabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		frame.getContentPane().add(feeTabbedPane, "name_81269495444100");
		
		// 包厢费用说明
		JPanel roomFeePanel = new JPanel();
		feeTabbedPane.addTab("\u5305\u53A2\u6536\u8D39", null, roomFeePanel, null);
		roomFeePanel.setLayout(new CardLayout(0, 0));
		JTextArea roomFeeTextArea = new JTextArea();
		roomFeeTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		roomFeeTextArea.setEditable(false); // 禁止修改
		roomFeeTextArea.setText(Room.getFeesDescription()); // 刷出费用说明
		JScrollPane roomFeeTextAreaScrollPane = new JScrollPane(roomFeeTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		roomFeePanel.add(roomFeeTextAreaScrollPane, "name_81775096949000");
		
		// 食品收费说明
		JPanel foodFeePanel = new JPanel();
		feeTabbedPane.addTab("\u98DF\u7269\u6536\u8D39", null, foodFeePanel, null);
		foodFeePanel.setLayout(new CardLayout(0, 0));
		JTextArea foodFeeTextArea = new JTextArea();
		foodFeeTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		foodFeeTextArea.setEditable(false);
		foodFeeTextArea.setText(Food.getFeesDescription());
		JScrollPane foodFeeTextAreaScrollPane = new JScrollPane(foodFeeTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		foodFeePanel.add(foodFeeTextAreaScrollPane, "name_81775096949001");
		
		
		// 酒水收费说明
		JPanel drinksFeePanel = new JPanel();
		feeTabbedPane.addTab("\u9152\u6C34\u6536\u8D39", null, drinksFeePanel, null);
		drinksFeePanel.setLayout(new CardLayout(0, 0));
		JTextArea drinksFeeTextArea = new JTextArea();
		drinksFeeTextArea.setEditable(false);
		drinksFeeTextArea.setText(Drinks.getFeesDescription());
		drinksFeeTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		JScrollPane drinksFeeTextAreaScrollPane = new JScrollPane(drinksFeeTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		drinksFeePanel.add(drinksFeeTextAreaScrollPane, "name_81775096949002");
		
	}

}

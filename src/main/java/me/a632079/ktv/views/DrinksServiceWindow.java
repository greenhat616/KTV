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
		frame.getContentPane().setFont(new Font("΢���ź�", Font.PLAIN, 13));
		frame.setTitle("\u9152\u6C34\u670D\u52A1");
		frame.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		frame.setBounds(100, 100, 471, 553);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		tabbedPane.setBounds(10, 10, 437, 428);
		frame.getContentPane().add(tabbedPane);

		// ʳ�����
		JPanel foodPanel = new JPanel();
		tabbedPane.addTab("\u98DF\u7269", null, foodPanel, null);
		foodPanel.setLayout(new CardLayout(0, 0));
		JList<String> foodListComponent = new JList<String>();
		foodListComponent.setFont(new Font("΢���ź�", Font.PLAIN, 15));
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
		JMenuItem foodPopupMenuBuyItem = new JMenuItem("����");
		foodPopupMenuBuyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(foodListComponent,
						String.format("ȷ������%s����\n����֧�� %d Ԫ", foodSelectedItem, Food.getFee(foodSelectedItem)), "ȷ�Ϲ���",
						JOptionPane.YES_NO_OPTION);
				if (op == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(foodListComponent, "���ѳɹ������Ժ��͵���");
				}
			}
		});
		foodPopupMenu.add(foodPopupMenuBuyItem);
		foodListComponent.setModel(foodListModel);
		foodListComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // ˫���¼�
					String key = foodListComponent.getModel()
							.getElementAt(foodListComponent.locationToIndex(e.getPoint()));
					int op = JOptionPane.showConfirmDialog(foodListComponent,
							String.format("ȷ������%s����\n����֧�� %d Ԫ", key, Food.getFee(key)), "ȷ�Ϲ���",
							JOptionPane.YES_NO_OPTION);
					if (op == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(foodListComponent, "���ѳɹ������Ժ��͵���");
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) { // �Ҽ�
					int itemIndex = foodListComponent.locationToIndex(e.getPoint());
					foodListComponent.setSelectedIndex(itemIndex); // ���ý���
					foodSelectedItem = foodListModel.getElementAt(itemIndex); // ����ѡ�� Item
					foodPopupMenu.show(foodListComponent, e.getX(), e.getY());
				}
			}
		});

		foodPanel.add(foodListComponent, "name_42299243385900");
		JScrollPane foodListComponentScrollPane = new JScrollPane(foodListComponent,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		foodPanel.add(foodListComponentScrollPane);

		// ��ˮ
		JPanel drinksPanel = new JPanel();
		tabbedPane.addTab("\u9152\u6C34", null, drinksPanel, null);
		drinksPanel.setLayout(new CardLayout(0, 0));

		JList<String> drinkListComponent = new JList<String>();
		drinkListComponent.setFont(new Font("΢���ź�", Font.PLAIN, 15));
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
		JMenuItem drinksPopupMenuBuyItem = new JMenuItem("����");
		drinksPopupMenuBuyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(drinksSelectedItem);
				int op = JOptionPane.showConfirmDialog(drinkListComponent,
						String.format("ȷ������%s����\n����֧�� %d Ԫ", drinksSelectedItem, Drinks.getFee(drinksSelectedItem)),
						"ȷ�Ϲ���", JOptionPane.YES_NO_OPTION);
				if (op == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(drinkListComponent, "���ѳɹ������Ժ��͵���");
				}
			}
		});
		drinksPopupMenu.add(drinksPopupMenuBuyItem);
		drinkListComponent.setModel(drinksListModel);
		drinkListComponent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // ˫���¼�
					String key = drinkListComponent.getModel()
							.getElementAt(drinkListComponent.locationToIndex(e.getPoint()));
					int op = JOptionPane.showConfirmDialog(drinkListComponent,
							String.format("ȷ������%s����\n����֧�� %d Ԫ", key, Drinks.getFee(key)), "ȷ�Ϲ���",
							JOptionPane.YES_NO_OPTION);
					if (op == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(drinkListComponent, "���ѳɹ������Ժ��͵���");
					}
				} else if (e.getButton() == MouseEvent.BUTTON3) { // �Ҽ�
					int itemIndex = drinkListComponent.locationToIndex(e.getPoint());
					drinkListComponent.setSelectedIndex(itemIndex); // ���ý���
					drinksSelectedItem = drinksListModel.getElementAt(itemIndex); // ����ѡ�� Item
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
			// �������
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "���Ժ󣬷�����Ա���Ͼ͵���");
			}
		});
		btnServiceRequestButton.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		btnServiceRequestButton.setBounds(34, 451, 182, 23);
		frame.getContentPane().add(btnServiceRequestButton);

		JButton btnBackToMainPageButton = new JButton("\u8FD4\u56DE\u4E3B\u754C\u9762");
		btnBackToMainPageButton.addActionListener(new ActionListener() {
			// ����������
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBackToMainPageButton.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		btnBackToMainPageButton.setBounds(246, 448, 174, 23);
		frame.getContentPane().add(btnBackToMainPageButton);

		JButton btnGetConsumeTimeButton = new JButton("\u67E5\u770B\u6D88\u8D39\u65F6\u95F4");
		btnGetConsumeTimeButton.addActionListener(new ActionListener() {
			// �鿴����ʱ��
			public void actionPerformed(ActionEvent e) {
				LocalDateTime currentTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				JOptionPane.showMessageDialog(frame, String.format(
						"���á�\n����ȼ���%s\n�Ʒ�ģʽ��%d Ԫ/ʱ\n�볡ʱ�䣺%s\n��ʹ�� %,d ʱ, %d �֡��� %,d Ԫ������һСʱ��һСʱ���㣩", State.room,
						Room.getFee(State.room), State.startDate.format(formatter),
						Duration.between(State.startDate, currentTime).toHours(),
						Duration.between(State.startDate, currentTime).toMinutesPart(),
						(Duration.between(State.startDate, currentTime).toHours() + 1) * Room.getFee(State.room)),
						"����ʱ��", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnGetConsumeTimeButton.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		btnGetConsumeTimeButton.setBounds(34, 484, 182, 23);
		frame.getContentPane().add(btnGetConsumeTimeButton);

		JButton btnViewFeeScaleButton = new JButton("\u6536\u8D39\u6807\u51C6");
		btnViewFeeScaleButton.addActionListener(new ActionListener() {
			// �鿴�շѱ�׼
			public void actionPerformed(ActionEvent e) {
				FeeScaleWindow.main(null);
			}
		});
		btnViewFeeScaleButton.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		btnViewFeeScaleButton.setBounds(246, 481, 174, 23);
		frame.getContentPane().add(btnViewFeeScaleButton);
	}

}

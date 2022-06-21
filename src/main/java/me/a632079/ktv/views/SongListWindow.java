package me.a632079.ktv.views;

import me.a632079.ktv.models.Player;
import me.a632079.ktv.models.Song;
import me.a632079.ktv.models.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: SongListWindow
 * @description: SongListWindow - 歌曲列表页面
 * @version: v1.0.0
 * @date: 2022/6/20 22:56
 * @author: haoduor
 */


public class SongListWindow {

    private JFrame frame;

    private Song selectedSong;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SongListWindow window = new SongListWindow();
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
    public SongListWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        Player player = State.player;

        frame = new JFrame();
        frame.setTitle("我的歌曲");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 分页组件
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        frame.getContentPane().add(tabbedPane);

        // 以点歌曲分页
        JPanel orderSong = new JPanel();
        tabbedPane.addTab("以点歌曲", null, orderSong, null);
        orderSong.setLayout(null);

        // 已唱歌曲分页
        JPanel sungSong = new JPanel();
        tabbedPane.addTab("已唱歌曲", null, sungSong, null);
        sungSong.setLayout(null);

        // 以点歌曲列表
        JList orderedSongList = new JList();
        orderedSongList.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        DefaultListModel listModel = new DefaultListModel();
        orderedSongList.setModel(listModel);
        orderedSongList.setSize(450, 290);
        orderSong.add(orderedSongList);

        refreshOrderedSongList(orderedSongList, player);

        JPopupMenu songControlMenu = new JPopupMenu();
        JMenuItem topItem = new JMenuItem("置顶");

        topItem.addActionListener(e -> {
            if (selectedSong != null) {
                player.topSong(selectedSong);
                refreshOrderedSongList(orderedSongList, player);
            }
        });

        JMenuItem deleteItem = new JMenuItem("删除");

        deleteItem.addActionListener(e-> {
            if (selectedSong != null) {
                player.removeSong(selectedSong.getId());
                refreshOrderedSongList(orderedSongList, player);
            }
        });

        songControlMenu.add(topItem);
        songControlMenu.add(deleteItem);

        orderedSongList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 判断右键事件
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int itemIndex = orderedSongList.locationToIndex(e.getPoint());
                    System.out.println(itemIndex);
                    orderedSongList.setSelectedIndex(itemIndex);
                    selectedSong = player.getPlayList().get(itemIndex);
                    songControlMenu.show(orderedSongList, e.getX(), e.getY());
                }
            }
        });


        // 以唱歌曲列表
        JList sungSongList = new JList();
        sungSongList.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        DefaultListModel listModel2 = new DefaultListModel();
        sungSongList.setModel(listModel2);
        sungSongList.setSize(450, 290);
        sungSong.add(sungSongList);

        sungSongList.setListData(convertListToArr(player.getPlayedList()));
    }

    public String[] convertListToArr(List<Song> songs) {
        return songs.stream()
                    .map(song -> String.format("%s - %s - %s - %s", song.getName(), song.getArtist().getName(), song.getStyle(), song.getLanguage()))
                    .toList()
                    .toArray(new String[songs.size()]);
    }

    public void refreshOrderedSongList(JList list, Player player) {
        list.setListData(convertListToArr(player.getPlayList()));
    }

}

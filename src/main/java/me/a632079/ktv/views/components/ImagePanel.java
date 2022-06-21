package me.a632079.ktv.views.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = -4914881061377544318L;
	private BufferedImage image;
	private int x = 0, y = 0;

	public ImagePanel() throws IOException {
		image = ImageIO.read(new File("./workdir/resources/default.jpg"));
	}

	public ImagePanel(String path) throws IOException {
		super();
		image = ImageIO.read(new File(path));
	}

	public ImagePanel(String path, int x, int y) throws IOException {
		super();
		image = ImageIO.read(new File(path));
		this.x = x;
		this.y = y;
	}

	public void setImage(String path) throws IOException {
		image = ImageIO.read(new File(path));
		this.repaint(); // 重绘
	}

	public void setImage(String path, int x, int y) throws IOException {
		image = ImageIO.read(new File(path));
		this.x = x;
		this.y = y;
		this.repaint(); // 重绘
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (x != 0 || y != 0) {
			g.drawImage(image, 0, 0, x, y, this); // x, y 为容器的大小，用于自动缩放图片
		} else {
			g.drawImage(image, 0, 0, this);
		}
	}

}
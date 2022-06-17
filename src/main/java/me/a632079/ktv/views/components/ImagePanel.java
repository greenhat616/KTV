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

	public ImagePanel() throws IOException {
		image = ImageIO.read(new File("./workdir/resources/default.jpg"));
	}

	public ImagePanel(String path) throws IOException {
		super();
		image = ImageIO.read(new File(path));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
	}

}
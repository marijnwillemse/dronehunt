package dronehunt.view.elements;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SceneryView extends JPanel {

	private Image backgroundImage;
	private Image foregroundImage;

	public SceneryView() {
		ImageLoader imageLoader = new ImageLoader();
		backgroundImage = imageLoader.loadImageIcon("background.png").getImage();
		foregroundImage = imageLoader.loadImageIcon("foreground.png").getImage();
	}

	public void drawBackground(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, this);
	}

	public void drawForeground(Graphics g) {
		g.drawImage(foregroundImage, 0, 0, this);
	}
}

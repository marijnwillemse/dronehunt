package main.java.view.elements;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.java.model.Game;

@SuppressWarnings("serial")
public class HUDView extends JPanel {

  private Image scoreBoxImage;
  private Image messageBoxImage;

  private Image imageBoxBullet;
  private Image imageBullet;

  public HUDView() {
    ImageLoader imageLoader = new ImageLoader();
    scoreBoxImage = imageLoader.loadImageIcon("scorebox.png").getImage();
    messageBoxImage = imageLoader.loadImageIcon("gameoverbox.png").getImage();
    
    imageBoxBullet = imageLoader.loadImageIcon("box.bullet.png").getImage();
    imageBullet = imageLoader.loadImageIcon("bullet.png").getImage();
  }

  public void drawScoreBox(Graphics g, int score) {
    g.drawImage(scoreBoxImage, 189, 197, this);

    g.drawString(Integer.toString(score), 192, 207);
    g.drawString("SCORE", 192, 216);
  }

  public void drawGameOverBox(Graphics g) {
    g.drawImage(messageBoxImage, 91, 35, this);
    g.drawString("GAME OVER", 98, 56);
  }

  public void drawLevelBox(Graphics g, int level) {
    g.drawImage(messageBoxImage, 91, 35, this);
    g.drawString("LEVEL " + level, 106, 56);
  }
  
  public void drawBulletInfo(Graphics g, Game game) {
    g.drawImage(imageBoxBullet, 17, 183, this);
  }
}

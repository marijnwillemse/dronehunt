package main.java.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.java.controller.AppController;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.Game;
import main.java.model.MainModel;
import main.java.model.World;
import main.java.view.elements.DroneView;
import main.java.view.elements.FrameCounter;
import main.java.view.elements.HUDView;
import main.java.view.elements.SceneryView;

public class GameRenderer {

  private MainModel model;
  private FrameCounter frameCounter;

  private Graphics g;
  private BufferedImage bufferedImage;
  private SpriteLoader spriteLoader;

  private DroneView droneView;
  private SceneryView sceneryView;
  private HUDView hudView;

  private Map<String, Sprite> sprites = new HashMap<String, Sprite>();

  private int scale;
  private int width;
  private int height;

  public GameRenderer(MainModel model) {
    this.model = model;
    spriteLoader = new SpriteLoader();
    loadSprites();

    frameCounter = new FrameCounter();

    droneView = new DroneView();
    sceneryView = new SceneryView();
    hudView = new HUDView();

    scale = AppController.getViewScale();
    width = AppController.getWidth();
    height = AppController.getHeight();

    bufferedImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_ARGB);
  }

  private void loadSprites() {
    for (String key : SpriteLoader.getImageAdresses().keySet()) {
      Sprite sprite = spriteLoader.createSprite(key);
      sprites.put(key, sprite);
    }
  }

  /**
   * Draw the frame to an image buffer and paint it into the screen buffer.
   * 
   * Performs rendering operations in a secondary image
   * so that it can be repainted directly into the window.
   * Prevents flickering and keeps paintComponent() simple.
   */
  public void render(double t) {
    g = bufferedImage.getGraphics();

    // Draw all game elements to the buffer image.
    fillBackground(g, GameColors.DBLUE.getRGB());

    int x; int y;
    x = y = 0;
    renderSprite(sprites.get("background.png"), t, g, x, y);
    for (Drone drone : model.getWorld().getDrones()) {
      Vector2D position = drone.getPosition();
      renderSprite(sprites.get("quadcopter.1.png"), t, g, (int) position.getX(),
          (int) position.getY());
    }
    renderSprite(sprites.get("foreground.png"), t, g, x, y);
    
    x = 17; y = 183;
    renderSprite(sprites.get("box.bullet.png"), t, g, x, y);
    int numberOfBullets = model.getGame().getBullets();
    int offset = 13;
    for (int i = 0; i < numberOfBullets; i++) {
      x = 22 + offset * i; y = 187;
      renderSprite(sprites.get("bullet.png"), t, g, x, y);
    }
    if (MainView.getDebug()) {
//      droneView.drawDebugOverlay(t, g);
    }
  }

  private void fillBackground(Graphics context, Color color) {
    context.setColor(color);
    context.fillRect(0, 0, width, height);
  }

  private void renderSprite(Sprite sprite, double t, Graphics g, int x, int y) {
    g.drawImage(sprite.getImage(), x, y, null);
  }

  /** 
   * Actively render the buffer image to the screen
   */
  public void paintBuffer(Graphics g) {
    try {
      if ((g != null) && (bufferedImage != null)) {
        g.drawImage(bufferedImage, 0, 0, width*scale, height*scale, null);
      }
      Toolkit.getDefaultToolkit().sync(); // Syncs the display on some systems
      g.dispose();
    }
    catch (Exception e) {
      System.out.println("Graphics context error: " + e);
    }
  }
}

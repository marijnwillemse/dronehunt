package main.java.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.controller.App;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.MainModel;

public class GameRenderer {

  private MainModel model;

  private Graphics g;
  private BufferedImage bufferedImage;
  private SpriteLoader spriteLoader;
  private FrameCounter frameCounter;

  private Map<String, Sprite> sprites = new HashMap<String, Sprite>();

  public GameRenderer(MainModel model) {
    this.model = model;
    spriteLoader = new SpriteLoader();
    loadSprites();
    frameCounter = new FrameCounter();

    bufferedImage = new BufferedImage(App.WIDTH, App.HEIGHT,
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
    DrawMethods methods = new DrawMethods();
    methods.fillBackground(g, GameColors.DBLUE.getRGB());
    methods.drawBackdrop(g);
    methods.drawDrones(g, model.getWorld().getDrones());
    methods.drawForeground(g);
    methods.drawBulletHUD(g, model.getGame().getBullets());
    methods.drawLifeHUD(g, model.getGame().getLife());

    methods.drawFramerate(g, frameCounter.getFramesPerSecond());
    if (MainView.getDebug()) {
      methods.drawDebug(g, model.getWorld().getDrones());
    }
  }

  /** 
   * Actively render the buffer image to the screen
   */
  public void paintBuffer(Graphics g) {
    try {
      if ((g != null) && (bufferedImage != null)) {
        int viewScale = App.getViewScale();
        int adjustedWidth = App.WIDTH * viewScale;
        int adjustedHeight = App.HEIGHT * viewScale;
        g.drawImage(bufferedImage, 0, 0, adjustedWidth, adjustedHeight, null);
      }
      Toolkit.getDefaultToolkit().sync(); // Syncs the display on some systems
      g.dispose();
    }
    catch (Exception e) {
      System.out.println("Graphics context error: " + e);
    }
  }

  private void renderSprite(Sprite sprite, Graphics g, int x, int y) {
    x -= sprite.getWidth() / 2;
    y -= sprite.getHeight() / 2;
    g.drawImage(sprite.getImage(), x, y, null);
  }

  private void setFontToDefault(Graphics g) {
    g.setFont(new Font("Courier", Font.PLAIN, 12));
    g.setColor(Color.white);
  }

  private class DrawMethods {

    private int xCenter() {
      return App.WIDTH / 2;
    }

    public void drawDebug(Graphics g, List<Drone> drones) {
      for (Drone drone : drones) {
        drawVelocityVector(g, drone);
        drawTarget(g, drone);
      }
    }

    public void drawVelocityVector(Graphics g, Drone drone) {
      g.setColor(Color.green);
      g.drawLine((int)drone.getX(), (int)drone.getY(),
          (int)(drone.getX() + drone.getVelocity().getX()/10),
          (int)(drone.getY() + drone.getVelocity().getY()/10));
    }

    public void drawTarget(Graphics g, Drone drone) {
      if (drone.hasTarget()) {
        g.setColor((drone.hasReachedTarget() ? Color.blue : Color.red));
        g.drawLine((int)drone.getTarget().getX()-2,
            (int)drone.getTarget().getY()-2,
            (int)drone.getTarget().getX()+2,
            (int)drone.getTarget().getY()+2);
        g.drawLine((int)drone.getTarget().getX()+2,
            (int)drone.getTarget().getY()-2,
            (int)drone.getTarget().getX()-2,
            (int)drone.getTarget().getY()+2);
      }
    }

    private int yCenter() {
      return App.HEIGHT / 2;
    }

    public void fillBackground(Graphics g, Color color) {
      g.setColor(color);
      g.fillRect(0, 0, App.WIDTH, App.HEIGHT);
    }

    public void drawBulletHUD(Graphics g, int bullets) {
      int x = 8;
      int y = 194;
      
      // String
      int fontSize = 12;
      g.setFont(new Font("Courier", Font.PLAIN, fontSize));
      g.setColor(Color.white);
      g.drawString("AMMO", x, y);
      
      x += 41;
      y -= 3;

      int numberOfBullets = model.getGame().getBullets();
      int offset = 13;
      
      for (int i = 0; i < numberOfBullets; i++) {
        int x2 = x + offset * i;
        renderSprite(sprites.get("bullet"), g, x2, y);
      }

    }

    public void drawForeground(Graphics g) {
      renderSprite(sprites.get("foreground"), g, xCenter(), yCenter());
    }

    public void drawDrones(Graphics g, List<Drone> drones) {
      for (Drone drone : drones) {
        Vector2D position = drone.getPosition();
        renderSprite(sprites.get("quadcopter.1"), g, (int) position.getX(),
            (int) position.getY());
      }
    }

    public void drawBackdrop(Graphics g) {
      renderSprite(sprites.get("background"), g, xCenter(), yCenter());
    }

    public void drawFramerate(Graphics g, String string) {
      int fontSize = 12;
      g.setFont(new Font("Courier", Font.PLAIN, fontSize));
      g.setColor(Color.white);
      g.drawString(string, 10, 20);
    }

    public void drawLifeHUD(Graphics g, int life) {
      int x = 8;
      int y = 214;
      
      // String
      int fontSize = 12;
      g.setFont(new Font("Courier", Font.PLAIN, fontSize));
      g.setColor(Color.white);
      g.drawString("LIFE", x, y);
      
      x += 37;
      y -= 3;
      
      g.setColor(GameColors.POMEGRANATE.getRGB());

      for (int i = 0; i < life; i++) {
        int x2 = x + i * 3;
        g.fillRect(x2, y - 3, 2, 6);
      }
    }
  }
}

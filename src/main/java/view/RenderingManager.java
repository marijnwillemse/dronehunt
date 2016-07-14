package main.java.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.List;

import main.java.controller.AppController;
import main.java.model.Drone;
import main.java.model.MainModel;
import main.java.view.elements.DroneView;
import main.java.view.elements.FrameCounter;
import main.java.view.elements.HUDView;
import main.java.view.elements.SceneryView;

/**
 * Batches and renders sprites from layer to layer.
 * Sprites essentially add / remove themselves to / from the rendering manager.
 */
public class RenderingManager {

  MainModel model;
  private FrameCounter frameCounter;

  private Graphics context;
  private BufferedImage bufferedImage;

  private DroneView droneView;
  private SceneryView sceneryView;
  private HUDView hudView;

  private int scale;
  private int width;
  private int height;

  public RenderingManager(MainModel model) {
    this.model = model;
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

  /**
   * Draw the frame to an image buffer and paint it into the screen buffer.
   * 
   * Performs rendering operations in a secondary image
   * so that it can be repainted directly into the window.
   * Prevents flickering and keeps paintComponent() simple.
   */
  public void render(double t) {
    context = bufferedImage.getGraphics();

    // Draw all game elements to the buffer image.
    drawBackground();
    sceneryView.drawBackground(context);
    drawUnits(t, context);
    sceneryView.drawForeground(context);
    drawHUD(context);
    if (MainView.getDebug()) {
      drawDebugOverlay(t, context);
    }
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

  private void drawBackground() {
    context.setColor(GameColors.DBLUE.getRGB());
//    dbg.setColor(GameColors.MELON.getRGB());
    context.fillRect(0, 0, width, height);
  }

  private void drawHUD(Graphics g) {
    resetFont(context, 12);

    //    hudView.drawGameOverBox(g);

    hudView.drawBulletInfo(g, model.getGame());

    // Frame count
    int fontSize = 12;
    context.setFont(new Font("Courier", Font.PLAIN, fontSize));
    context.setColor(Color.white);
    context.drawString(frameCounter.getFramesPerSecond(), 10, 20);
  }


  private void resetFont(Graphics g, int fontSize) {
    g.setFont(new Font("Courier", Font.PLAIN, fontSize));
    g.setColor(Color.white);
  }


  private void drawUnits(double t, Graphics g) {
    List<Drone> drones = model.getWorld().getDrones();
    for (Drone drone : drones) {
      droneView.draw(t, g, drone);
    }
  }

  private void drawDebugOverlay(double t, Graphics g) {
    List<Drone> drones = model.getWorld().getDrones();
    for (Drone drone : drones) {
      droneView.drawVelocityVector(t, g, drone);
      droneView.drawTarget(t, g, drone);
    }
  }

}

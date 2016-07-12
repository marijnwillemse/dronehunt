package main.java.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import main.java.model.MainModel;
import main.java.model.Drone;
import main.java.view.elements.DroneView;
import main.java.view.elements.FrameCounter;
import main.java.view.elements.HUDView;
import main.java.view.elements.SceneryView;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

  private MainModel model;

  private FrameCounter frameCounter;
  private DroneView droneView;
  private SceneryView sceneryView;
  private HUDView hudView;

  // Drawing fields
  private int gameWidth;
  private int gameHeight;
  private int scale;
  private Graphics context;
  private Image bufferImage = null;

  public GamePanel(MainModel model, InputContainer inputContainer,
      int gameWidth, int gameHeight, int scale) {

    this.model = model;

    frameCounter = new FrameCounter();
    droneView = new DroneView();
    sceneryView = new SceneryView();
    hudView = new HUDView();

    this.gameWidth = gameWidth;
    this.gameHeight = gameHeight;
    this.scale = scale;

    setPreferredSize(new Dimension(gameWidth*scale, gameHeight*scale));

    this.setBackground(Color.BLACK);

    setFocusable(true);
    requestFocus(); // the JPanel now has focus, so receives key events.

    setupInputListeners(inputContainer);
  }

  /**
   * Setup listener in game view to catch events.
   */
  private void setupInputListeners(InputContainer inputContainer) {
    addKeyListener( new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        inputContainer.addKeyEvent(e);
      }
    });

    addMouseListener( new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        inputContainer.addMouseEvent(e);
      }
    });
  }

  /*
   * Double buffer drawing
   * 
   * Performs rendering operations in a secondary image
   * so that it can be repainted directly into the window.
   * Prevents flickering and keeps paintComponent() simple.
   */
  public void render(double t)
  // Draw the current frame to an image buffer.
  {
    if (bufferImage == null) { // Create the buffer
      bufferImage = createImage(gameWidth, gameHeight);
      if (bufferImage == null) {
        System.out.println("Buffer image is null");
        return;
      }
      else {
        context = bufferImage.getGraphics();
      }
    }

    // Draw all game elements to the buffer image.
    drawBackground();
    sceneryView.drawBackground(context);
    drawUnits(t, context);
    sceneryView.drawForeground(context);
    drawHUD(context);
  }

  private void drawBackground() {
    context.setColor(GameColors.DBLUE.getRGB());

    //    Unit myUnit = model.getActiveUnit();
    //    if(myUnit != null) {
    //      if(myUnit.getState() instanceof EscapeState) {
    //        dbg.setColor(GameColors.MELON.getRGB());
    //      }
    //    }

    context.fillRect(0, 0, gameWidth, gameHeight);
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
      droneView.drawVelocityVector(t, g, drone);
      droneView.drawTarget(t, g, drone);
    }
  }


  /* Active rendering */
  public void paintBuffer()
  /* Actively render the buffer image to the screen
   * Puts the task of rendering the buffer image to the screen in my hands.
   * The call to repaint() is gone and the functionality of paintComponent()
   * has been incorporated. */
  {
    Graphics g;
    try {
      g = this.getGraphics(); // Get the panel's graphic context
      if ((g != null) && (bufferImage != null))
        g.drawImage(bufferImage, 0, 0, gameWidth*scale, gameHeight*scale, null);
      Toolkit.getDefaultToolkit().sync(); // Sync the display on some systems
      g.dispose();
    }
    catch (Exception e)
    { System.out.println("Graphics context error: "+ e); }
  }

  /**
   * Handle application and game-play related keys.
   */
  private void processKey(KeyEvent e) {
    //    int keyCode = e.getKeyCode();
    //
    //    // Termination keys
    //    // Listen for ESCAPE, and CTRL + W on the canvas for exiting the game.
    //    if ((keyCode == KeyEvent.VK_ESCAPE) ||
    //        ((keyCode == KeyEvent.VK_W) && e.isControlDown()) ) {
    //      view.set = false;
    //    }
    //    
    //    // game-play keys
    //    if (!isPaused && !gameOver) {
    //      if (keyCode == KeyEvent.VK_LEFT)
    //        bat.moveLeft(); 
    //      else if (keyCode == KeyEvent.VK_RIGHT)
    //        bat.moveRight();
    //      else if (keyCode == KeyEvent.VK_DOWN)
    //        bat.stayStill();
    //    }
  }  // end of processKey()

}
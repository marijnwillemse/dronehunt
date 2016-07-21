package main.java.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.java.model.MainModel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

  private GameRenderer gameRenderer;

  public GamePanel(MainModel model, InputContainer inputContainer,
      int gameWidth, int gameHeight, int scale) {

    gameRenderer = new GameRenderer(model);

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

  public void refresh(double t) {
    // Renders the game to an image buffer
    gameRenderer.render(t);
    // Draw the buffer onto the screen
    gameRenderer.paintBuffer(this.getGraphics());
  }

  public GameRenderer getGameRenderer() {
    return gameRenderer;
  }
}
package main.java.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import main.java.model.MainModel;

public class MainView {

  private MainModel model;

  private JFrame app;			 // JFrame in which the application is displayed

  private GamePanel gamePanel;	 // Game screen component panel
  private GameView gameView;	 // Game view drawing panel

  public MainView(MainModel model) {
    this.model = model;
  }

  public void createAndShowGUI(int width, int height, int viewScale) {
    /* Test to confirm this thread is running as the EDT */
    if (javax.swing.SwingUtilities.isEventDispatchThread()) {
      System.out.println("GUI creation is running on the EDT");
    }

    // Create the main game frame
    app = new JFrame("Drone Hunt");
    // Exit application when closed
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Position frame at platform preference
    app.setLocationByPlatform(true);

    // Create GUI panels
    gamePanel = new GamePanel();
    gameView = new GameView(model, width, height, viewScale);

    showMainScreen(); // Initialize the main screen
  }

  private void showMainScreen() {
    // Make sure the frame is clear
    app.getContentPane().removeAll();

    // Configure components to be assigned to regions
    gamePanel.setLayout(new BorderLayout());

    gamePanel.add(gameView, BorderLayout.CENTER);

    // Add panel to frame
    app.getContentPane().add(gamePanel, "Center");

    app.setResizable(false); // Disable window resizing
    app.pack();				 // Adjust window to contents
    // pack must be called after setting resizable to false,
    // for when window is resizeable, layout manager will
    // leave 10px margin for (potential) scrollbars.

    // Show the frame
    app.setVisible(true);
  }

  public void refresh(double t) {
    gameView.render(t);     // Renders the game to an image buffer
    gameView.paintBuffer(); // Draws the buffer onto the screen
  }

  public GameView getGameView() {
    return gameView;
  }
}

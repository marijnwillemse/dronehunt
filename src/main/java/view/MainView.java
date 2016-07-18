package main.java.view;

import javax.swing.JFrame;

import main.java.model.MainModel;

public class MainView {

  private MainModel model;

  private JFrame app; // JFrame in which the application is displayed

  private GamePanel gamePanel; // Game view drawing panel

  private InputContainer inputContainer; // Stores user input events
  
  private static boolean debug = false;

  public MainView(MainModel model) {
    this.model = model;
    inputContainer = new InputContainer();
  }

  public void createAndShowGUI(int width, int height, int viewScale) {
    /* Test to confirm this thread is running as the EDT */
    if (!javax.swing.SwingUtilities.isEventDispatchThread()) {
      System.out.println("Warning: GUI creation is not running on the EDT");
    }

    // Create the main game frame
    app = new JFrame("Drone Hunt");
    // Exit application when closed
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Position frame at platform preference
    app.setLocationByPlatform(true);

    // Create GUI panels
    gamePanel = new GamePanel(model, inputContainer, width, height, viewScale);

    showMainScreen(); // Initialize the main screen
  }

  private void showMainScreen() {
    // Make sure the frame is clear
    app.getContentPane().removeAll();

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
    gamePanel.refresh(t);
  }

  public GamePanel getGamePanel() {
    return gamePanel;
  }

  public InputContainer getInputContainer() {
    return inputContainer;
  }
  
  public static void setDebug(boolean b) {
    debug = b;
  }
  
  public static boolean getDebug() {
    return debug;
  }
}

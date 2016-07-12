package main.java.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.java.model.MainModel;
import main.java.view.InputContainer;
import main.java.view.MainView;

public class AppController {
  
  private GameController gameController;
  private MainView view;

  public final int WIDTH = 256;		// Game window width
  public final int HEIGHT = 224;	// Game window height
  public final int VIEW_SCALE = 2;	// Upscale factor
  
  private boolean finished = false;
  
  public AppController(MainModel model, MainView view) {
    gameController = new GameController(this, model);
    this.view = view;
  }

  /**
   * Update the application
   */
  public void update(double dt) {
    processInput();	// Process user input and clear.
    gameController.update(dt); // Update general game logic.
  }

  /**
   * User input is collected from the input container,
   * processed and cleared.
   */
  private void processInput() {
    // Fetch input container from view.
    InputContainer inputContainer = view.getInputContainer();
    
 // Process mouse events catched in input container.
    for(MouseEvent e : inputContainer.getMouseEvents()) {
      int button = e.getButton();
      
      // Adjust to view scale.
      int x = e.getX() / VIEW_SCALE;
      int y = e.getY() / VIEW_SCALE;
      
      gameController.MousePressed(button, x, y);
    }

 // Process key events catched in input container.
    for(KeyEvent e : inputContainer.getKeyEvents()) {
      int keyCode = e.getKeyCode();
      if (keyCode == KeyEvent.VK_ESCAPE ||
          ((keyCode == KeyEvent.VK_W) && e.isControlDown())) {
        finished = true;
      }
      gameController.KeyPressed(keyCode);
    }
    inputContainer.clearInput();
  }

  public boolean isFinished() {
    return finished;
  }
}

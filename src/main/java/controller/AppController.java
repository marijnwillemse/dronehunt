package main.java.controller;

import java.awt.event.MouseEvent;

import main.java.model.MainModel;

public class AppController {
  private GameController gameController;
  
  private InputContainer inputContainer;

  public final int WIDTH = 256;		// game width
  public final int HEIGHT = 224;	// game height
  public final int VIEW_SCALE = 4;	// game scale degree 

  public AppController(MainModel model, InputContainer inputContainer) {
    gameController = new GameController(model);
    
    this.inputContainer = inputContainer;
  }

  /**
   * Update the application
   */
  public void update(double dt) {
    processInput();	// Process user input and clear
    gameController.update(dt); // Update game logic
  }

  /**
   * User input is collected from the input container,
   * processed in game and removed.
   */
  private void processInput() {
    // process mouse events catched in input container
    for(MouseEvent e : inputContainer.getMouseEvents()) {
      int button = e.getButton();
      
      // adjust to view scale
      int x = e.getX() / VIEW_SCALE;
      int y = e.getY() / VIEW_SCALE;
      
      gameController.MousePressed(button, x, y);
    }
    inputContainer.clearInput();
  }
}

package main.java.controller;

import java.awt.event.MouseEvent;

import main.java.model.MainModel;

public class AppController {
  private MainModel model;
  private InputContainer inputContainer;

  public final int WIDTH = 256;		// application width
  public final int HEIGHT = 224;	// application height
  public final int VIEW_SCALE = 4;	// application scale degree 

  public AppController(MainModel model, InputContainer inputContainer) {
    this.model = model;
    model.newGame();
    this.inputContainer = inputContainer;
  }

  /**
   * Update the game
   */
  public void update(double dt) {
    processInput();	  // process user input and clear
    model.update(dt); // update game models relative to delta time
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
      model.getCurrentLevel().testMousePress(button, x, y);
    }
    inputContainer.clearInput();
  }
}

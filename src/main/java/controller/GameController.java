package main.java.controller;

import main.java.model.Game;
import main.java.model.MainModel;

public class GameController {
  
  private Game game;

  public GameController(MainModel model) {
    game = model.newGame();
  }
  
  /**
   * Update game
   */
  public void update(double dt) {

  }
}

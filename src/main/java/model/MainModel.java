package main.java.model;

public class MainModel {

  private World game;

  public MainModel() {
  }

  public World newGame() {
    return game = new World();
  }

  public World getWorld() {
    return game;
  }
}

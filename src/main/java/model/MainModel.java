package main.java.model;

public class MainModel {

  private World world;
  private Game game;

  public MainModel() {

  }

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  public void newGame() {
    this.game = new Game();
  }

  public Game getGame() {
    return game;
  }

  public void init() {
    world = new World();
    newGame();
  }
}

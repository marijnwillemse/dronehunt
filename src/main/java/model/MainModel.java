package main.java.model;

public class MainModel {

  private World world;
  private Game game;

  public MainModel() {
    world = new World();
    game = new Game();
  }

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }

  public Game newGame() {
    game = new Game();
    return game;
  }

  public Game getGame() {
    return game;
  }
}

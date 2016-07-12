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
    this.game = new Game(world);
  }

  public Game getGame() {
    return game;
  }

  public void init(int width, int height) {
    world = new World(width, height);
    newGame();
  }
}

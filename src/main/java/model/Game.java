package main.java.model;

public class Game {

  private World world;

  private int score = 0;
  
  private int bullets;
  
  private boolean reloading = false;

  public Game(World world) {
    this.world = world;
    this.bullets = 2;
  }

  public void changeScore(int amount) {
    score += amount;
  }

  public int getScore() {
    return score;
  }
  
  public void changeBullets(int amount) {
    bullets += amount;
  }
  
  public void setBullets(int amount) {
    bullets = amount;
  }
  
  public int getBullets() {
    return bullets;
  }

  public void startReloading() {
    reloading = true;
  }

  public boolean isReloading() {
    return reloading;
  }

  public void reload() {
    reloading = false;
    setBullets(2);
  }
}

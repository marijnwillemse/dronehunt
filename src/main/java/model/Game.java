package main.java.model;

public class Game {

  public final static int MAX_LIFE = 5;
  public final static int MAX_BULLETS = 2;
  
  private int score;
  private int life;
  private int bullets;
  
  private boolean reloading = false;

  public Game() {
    life = MAX_LIFE;
    bullets = MAX_BULLETS;
  }
  
  public void changeScore(int amount) {
    score += amount;
  }

  public int getScore() {
    return score;
  }
  
  public int getLife() {
    return life;
  }
  
  public void changeLife(int amount) {
    life += amount;
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
    setBullets(MAX_BULLETS);
  }
}

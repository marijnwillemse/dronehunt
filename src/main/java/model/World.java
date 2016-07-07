package main.java.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

  private int score = 0;
  private int width;
  private int height;
  private Rectangle areaAir;

  private List<Drone> drones;

  public World(int width, int height) {
    drones = new ArrayList<Drone>();
    this.width = width;
    this.height = height;
    areaAir = new Rectangle(0, 0, width, (int) (height * 0.6));
  }

  public int numberOfDrones() {
    return drones.size();
  }

  public void addDrone(Drone drone) {
    drones.add(drone);
  }

  public List<Drone> getDrones() {
    return drones;
  }
  
  public void changeScore(int amount) {
    score += amount;
  }
  
  public int getScore() {
    return score;
  }

  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
  public Rectangle getAreaAir() {
    return areaAir;
  }
}

package main.java.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

  public static final int WIDTH = 256;
  public static final int HEIGHT = 224;
  
  // Size and location information of areas in the game
  public final static Rectangle AIR_AREA;
  
  static {
      int x = 38;
      int y = 24;
      int areaWidth = WIDTH - x * 2;
      int areaHeight = 100;
      AIR_AREA = new Rectangle(x, y, areaWidth, areaHeight);
  };
  
  public final static int GROUND_LINE = 168;

  private List<Drone> drones;

  public World() {
    drones = new ArrayList<Drone>();
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

  public static Rectangle getAreaAir() {
    return AIR_AREA;
  }

  public void removeDrone(int index) {
    drones.remove(index);
  }
}

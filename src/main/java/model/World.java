package main.java.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

  private static int width;
  private static int height;
  
  // Size and location information of areas in the game
  private static Rectangle areaAir;

  private List<Drone> drones;

  public World(int width, int height) {
    drones = new ArrayList<Drone>();
    World.width = width;
    World.height = height;
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

  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
  public static Rectangle getAreaAir() {
    return areaAir;
  }
}

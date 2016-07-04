package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class World {

  private int score = 0;

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
}

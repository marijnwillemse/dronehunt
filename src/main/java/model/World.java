package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class World {

  private int score = 0;

  private List<Drone> droneList;

  public World() {
    droneList = new ArrayList<Drone>();

  }

  public int numberOfDrones() {
    return droneList.size();
  }

  public void addDrone(Drone drone) {
    droneList.add(drone);
  }
}

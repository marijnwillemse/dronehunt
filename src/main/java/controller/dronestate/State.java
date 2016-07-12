package main.java.controller.dronestate;

import main.java.controller.DroneController;

public interface State {
  public void update(DroneController droneController, double dt);
}

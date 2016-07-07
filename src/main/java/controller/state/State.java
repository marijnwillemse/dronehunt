package main.java.controller.state;

import main.java.controller.DroneController;

public interface State {
  public void update(DroneController droneController, double dt);
}

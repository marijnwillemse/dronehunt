package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.model.Drone;

public class IdleState implements State {

  public IdleState(Drone drone) {
  }

  @Override
  public void update(DroneController droneController, double dt) {
  }
}
package main.java.controller.state;

import main.java.controller.DroneController;
import main.java.model.Drone;

public class EvadeState implements State {

  private Drone drone;

  private final double PAUSE_TIME = 0.2;
  private double pauseCounter = 0;

  public EvadeState(Drone drone) {
    this.drone = drone;
  }

  @Override
  public void update(DroneController droneController, double dt) {
    if (!drone.hasTarget()) {
      droneController.newTarget(drone);
    }
    if (drone.hasReachedTarget()) {
      if (pauseCounter < PAUSE_TIME) {
        pauseCounter += dt;
      } else {
        pauseCounter = 0;
        droneController.newTarget(drone);
      }
    }
  }
}

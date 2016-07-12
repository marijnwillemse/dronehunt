package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.model.Drone;

public class HitState implements State {

  private Drone drone;
  
  private final double PAUSE_TIME = 0.35;
  private double pauseCounter = 0;

  public HitState(Drone drone) {
    this.drone = drone;
    
    drone.setTarget(drone.getPosition());
  }

  @Override
  public void update(DroneController droneController, double dt) {
    if (pauseCounter < PAUSE_TIME) {
      pauseCounter += dt;
    } else {
      pauseCounter = 0;
      drone.setState(new TumbleState(drone));
    }
  }
}

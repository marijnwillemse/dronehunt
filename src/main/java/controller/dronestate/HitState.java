package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.model.Drone;

public class HitState extends State {

  private final double PAUSE_TIME = 0.35;
  private double pauseCounter = 0;

  public HitState(Drone drone) {
    super(drone);
    
    drone.setTarget(drone.getPosition());
  }

  @Override
  public void update(DroneController droneController, double dt) {
    if (pauseCounter < PAUSE_TIME) {
      pauseCounter += dt;
    } else {
      drone.setState(new TumbleState(drone));
    }
  }
}

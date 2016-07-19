package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.model.Drone;

public class HitState extends State {

  private final double PAUSE_TIME = 0.1;
  private double pauseCounter = 0;

  private boolean willDie = false;

  public HitState(Drone drone) {
    super(drone);    
    drone.setTarget(drone.getPosition());

    if (drone.isInjured()) {
      willDie = true;
    }
    drone.injure();
  }

  @Override
  public void update(DroneController droneController, double dt) {
    if (pauseCounter < PAUSE_TIME) {
      pauseCounter += dt;
    } else {
      if (willDie) {
        drone.setState(new TumbleState(drone));
      } else {
        drone.setState(droneController.react(drone));
      }
    }
  }
}

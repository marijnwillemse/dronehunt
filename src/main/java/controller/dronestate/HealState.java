package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.model.Drone;

public class HealState extends State {

  private double counter = 0;
  private final double RECOVER_TIME = 1.0;

  public HealState(Drone drone) {
    super(drone);
    drone.setTarget(drone.getPosition());
  }

  @Override
  public void update(DroneController droneController, double dt) {
    
    if (!drone.isInjured()) {
      drone.setState(droneController.react(drone));
    }

    if (counter < RECOVER_TIME) {
      counter += dt;
    } else {
      drone.heal();
      drone.setState(droneController.react(drone));
    }
  }
}

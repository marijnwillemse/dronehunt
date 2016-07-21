package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.model.Drone;

public class AttackState extends State {

  private static final double RATE_OF_FIRE = 1.0;
  private double counter = 0;

  public AttackState(Drone drone) {
    super(drone);
    drone.setTarget(drone.getPosition());
  }

  @Override
  public void update(DroneController droneController, double dt) {

    if (counter < RATE_OF_FIRE) {
      counter += dt;
    } else {
      droneController.fire(drone);
      drone.setState(droneController.react(drone));
    }
  }
}

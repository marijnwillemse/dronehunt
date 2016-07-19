package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.model.Drone;

public class AttackState extends State {

  private final double CHARGE_TIME = 0.5;
  private final double RECOVER_TIME = 0.2;
  private double counter = 0;
  private boolean fired = false;

  public AttackState(Drone drone) {
    super(drone);
    drone.setTarget(drone.getPosition());
  }

  @Override
  public void update(DroneController droneController, double dt) {
    double period = fired ? RECOVER_TIME : CHARGE_TIME;
    
    if (counter < period) {
      counter += dt;
    } else {
      if (fired) {
        drone.setState(droneController.react(drone));
      } else {
        counter = 0;
        droneController.fire(drone);
        fired = true;
      }
    }
  }
}

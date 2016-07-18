package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.math.Vector2D;
import main.java.model.Drone;

public class EscapeState extends State {

  public EscapeState(Drone drone) {
    super(drone);
    Vector2D position = drone.getPosition();
    drone.setTarget(new Vector2D(position.getX(), -15));
    torque = 8000;
  }

  @Override
  public void update(DroneController droneController, double dt) {
    if (drone.hasReachedTarget() && drone.isActive()) {
      drone.deactivate();
    }
  }

}

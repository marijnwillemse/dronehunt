package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.World;

public class TumbleState extends State {

  public TumbleState(Drone drone) {
    super(drone);
    Vector2D position = drone.getPosition();
    drone.setTarget(new Vector2D(position.getX(), World.GROUND_LINE));
    torque = 8000;
    hittable = false;
  }

  @Override
  public void update(DroneController droneController, double dt) {
    if (drone.hasReachedTarget() && drone.isActive()) {
      drone.deactivate();
    }
  }
}

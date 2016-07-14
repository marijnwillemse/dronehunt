package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.World;

public class TumbleState implements State {

  private Drone drone;

  public TumbleState(Drone drone) {
    this.drone = drone;
    Vector2D position = drone.getPosition();
    drone.setTarget(new Vector2D(position.getX(), World.GROUND_LINE));
  }

  @Override
  public void update(DroneController droneController, double dt) {
    if (drone.hasReachedTarget() && drone.isActive()) {
      drone.deactivate();
    }
  }
}

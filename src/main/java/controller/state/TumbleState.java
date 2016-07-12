package main.java.controller.state;

import main.java.controller.DroneController;
import main.java.math.Vector2D;
import main.java.model.Drone;

public class TumbleState implements State {

  private Drone drone;

  public TumbleState(Drone drone) {
    this.drone = drone;
  }

  @Override
  public void update(DroneController droneController, double dt) {
    Vector2D position = drone.getPosition();
    drone.setTarget(new Vector2D(position.getX(), position.getY() + 100));
  }
}

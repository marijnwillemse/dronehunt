package main.java.controller.state;

import java.awt.Rectangle;

import main.java.controller.DroneController;
import main.java.math.MathOperations;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.World;

public class EvadeState implements State {

  private Drone drone;

  private final double PAUSE_TIME = 0.2;
  private double pauseCounter = 0;

  public EvadeState(Drone drone) {
    this.drone = drone;
  }
  
  private Vector2D newEvasiveTarget() {
    Rectangle areaAir = World.getAreaAir();
    Vector2D target = new Vector2D(
        (double)MathOperations.randomInteger(areaAir.x, areaAir.width),
        (double)MathOperations.randomInteger(areaAir.y, areaAir.height));
    return target;
  }
  
  @Override
  public void update(DroneController droneController, double dt) {
    if (!drone.hasTarget()) {
      drone.setTarget(newEvasiveTarget());
    }
    if (drone.hasReachedTarget()) {
      if (pauseCounter < PAUSE_TIME) {
        pauseCounter += dt;
      } else {
        pauseCounter = 0;
        drone.setTarget(newEvasiveTarget());
      }
    }
  }
}

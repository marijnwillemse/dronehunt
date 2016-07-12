package main.java.controller.dronestate;

import java.awt.Point;
import java.awt.Rectangle;

import main.java.controller.DroneController;
import main.java.math.MathOperations;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.World;

public class EvadeState implements State {

  private Drone drone;

  public EvadeState(Drone drone) {
    this.drone = drone;
  }

  private Vector2D newEvasiveTarget() {
    Rectangle areaAir = World.getAreaAir();

    Vector2D position = drone.getPosition();
    Vector2D target;

    Point p;

    do {
      target = new Vector2D(position.getX(), position.getY());

      double radius = 50;

      // Generate a random angle for defining the target direction. 
      double theta = MathOperations.randomDouble(0, (2 * Math.PI) );

      // Convert the angle into coordinates including radius.
      double x = radius * Math.cos(theta);
      double y = radius * Math.sin(theta);

      target = target.add(new Vector2D(x, y));

      p = new Point((int) target.getX(), (int) target.getY());

    } while (!areaAir.contains(p));

    return target;
  }

  @Override
  public void update(DroneController droneController, double dt) {
    if (!drone.hasTarget() || drone.hasReachedTarget()) {
      drone.setTarget(newEvasiveTarget());
    }
  }
}

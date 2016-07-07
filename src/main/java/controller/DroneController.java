package main.java.controller;

import main.java.model.World;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.MainModel;

public class DroneController {

  MainModel model;
  World world;

  public DroneController(MainModel model) {
    this.model = model;
    this.world = model.getWorld();
  }

  public void spawnDrone() {
    Drone drone = new Drone();
    drone.setPosition(new Vector2D(50.0, 50.0));
    world.addDrone(drone);
  }

  public void move(Drone drone, double dt) {
    Vector2D position = drone.getPosition();
    Vector2D velocity = drone.getVelocity();

    position.setX(position.getX() + dt * velocity.getX());
    position.setY(position.getY() + dt * velocity.getY());

    drone.setPosition(position);
  }

  public void update(Drone drone, double dt) {
    drone.getState().update();
    if (drone.getTarget() != null) {
      steer(drone, dt);
    }
  }

  /*
   * Steers the drone towards its current target.
   * Accelerates and decelerates smoothly, taking torque, maximum speed and
   * braking power into account.
   */
  private void steer(Drone drone, double dt) {
    double x = drone.getX();
    double y = drone.getY();

    double targetX = drone.getTarget().getX();
    double targetY = drone.getTarget().getY();

    // Calculate the magnitude of drone velocity.
    double velocity = drone.getVelocity().length();

    double maxSpeed = drone.getMaxSpeed();
    double torque = drone.getTorque();
    double brakingPower = drone.getBrakingPower();

    // Calculate the distances from the drone to the target for each dimension.
    double deltaX = targetX - x;
    double deltaY = targetY - y;

    // Calculate the angles in order to distribute the velocity later on 
    double angle = Math.atan2(deltaY, deltaX);
    double cosAngle = Math.cos(angle);
    double sinAngle = Math.sin(angle);

    // Calculate the length of the third side of the right triangle between
    // the drone target and the target using Pythagoras' theorem.
    double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

    // Assess the braking distance taking deceleration capability into account.
    double brakingDistance = Math.pow(velocity, 2) / (2 * brakingPower);


    if (distance > brakingDistance) {
      // Still able to brake before reaching target so increase acceleration.
      velocity = Math.min(velocity + torque * dt, maxSpeed);
    } else {
      // Target distance is before braking distance so decelerate.
      velocity = Math.max(velocity - brakingPower * dt, 0);
    }

    // Apply velocity to drone
    drone.setVelocity(new Vector2D(velocity * cosAngle, velocity * sinAngle));
    
    if (distance < (velocity * dt)) {
      // Almost reached target so snap the drone to the target.
      // This counteracts overshoot and dt variance.
      drone.setPosition(new Vector2D(targetX, targetY));
      drone.setVelocity(new Vector2D());
      return;
    }
  }
}

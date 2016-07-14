package main.java.controller;

import main.java.model.World;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import main.java.controller.dronestate.DeployState;
import main.java.controller.dronestate.HitState;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.MainModel;

public class DroneController {

  private MainModel model;
  private World world;

  private Observer inactiveObserver; 

  public DroneController(MainModel model) {
    this.model = model;
    this.world = model.getWorld();

    inactiveObserver = new Observer() {
      public void update(Observable obj, Object arg) {
        System.out.println("Dead!");
      }
    };
  }

  public void spawnDrone() {
    Drone drone = new Drone();
    drone.setPosition(new Vector2D(50.0, 50.0));
    world.addDrone(drone);
    addObserver(drone);
    drone.setState(new DeployState(drone));
  }
  
  private void addObserver(Drone drone) {
    drone.addObserver(inactiveObserver);
  }
  
  public void removeObserver(Drone drone) {
    drone.deleteObserver(inactiveObserver);
  }

  public void move(Drone drone, double dt) {
    Vector2D position = drone.getPosition();
    Vector2D velocity = drone.getVelocity();

    position.setX(position.getX() + dt * velocity.getX());
    position.setY(position.getY() + dt * velocity.getY());

    drone.setPosition(position);
  }

  public void control(Drone drone, double dt) {
    drone.getState().update(this, dt);
    if (drone.getTarget() != null) {
      steer(drone, dt);
    }
  }

  /*
   * Steers the drone towards its current target.
   */
  private void steer(Drone drone, double dt) {
    double x = drone.getX();
    double y = drone.getY();

    double targetX = drone.getTarget().getX();
    double targetY = drone.getTarget().getY();

    double torque = drone.getTorque();

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

    // Calculate velocity by multiplying the torque by the elapsed frame time.
    double velocity = torque * dt;

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

  public void testShot(int x, int y) {
    Point p = new Point(x, y);
    System.out.println("Shot!");
    for (Drone drone : model.getWorld().getDrones()) {
      Rectangle hitArea = drone.getHitArea();
      if (hitArea.contains(p)) {
        System.out.println("Hit!");
        drone.setState(new HitState(drone));
      }
    }
  }
}

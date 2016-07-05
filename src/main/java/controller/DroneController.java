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
    Vector2D acceleration = drone.getAcceleration();

    velocity.setX(velocity.getX() + dt * acceleration.getX());
    velocity.setY(velocity.getY() + dt * acceleration.getY());

    position.setX(position.getX() + dt * velocity.getX());
    position.setY(position.getY() + dt * velocity.getY());
    
    drone.setVelocity(velocity);
    drone.setPosition(position);
  }



  //  public void update(double dt) {
  //    state.update();
  //    move(dt);
  //  }
  //
  //  private void move(double dt) {

  //  }
  //  public void checkBounce() {
  //    if(x < xSize/2) {
  //      x = xSize/2;
  //      vx = -vx; 
  //    }
  //    if(x > 256.0 - xSize/2) {
  //      x = 256.0 - xSize/2;
  //      vx = -vx;
  //    }
  //    if(y < ySize/2) {
  //      y = ySize/2;
  //      vy = -vy;
  //    }
  //    if(y > 177.0 - ySize/2) {
  //      y = 177.0 - ySize/2;
  //      vy = -vy;
  //    }
  //  }

}

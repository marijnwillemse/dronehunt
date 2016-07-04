package main.java.controller;

import main.java.model.World;
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
    drone.setPosition(50, 50);
    world.addDrone(drone);
  }
  
  
  
//  public void update(double dt) {
//    state.update();
//    move(dt);
//  }
//
//  private void move(double dt) {
//    vx = vx + dt*ax;
//    vy = vy + dt*ay;
//
//    x = x + dt*vx;
//    y = y + dt*vy;
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

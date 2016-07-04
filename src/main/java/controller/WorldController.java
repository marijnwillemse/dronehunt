package main.java.controller;

import main.java.model.World;
import main.java.model.MainModel;

public class WorldController {

  private World world;
  
  private DroneController droneController;

  public WorldController(MainModel model) {
    world = model.newGame();
    droneController = new DroneController(model);
  }

  /**
   * Update game
   */
  public void update(double dt) {
    
    if (world.numberOfDrones() == 0) {
      // Spawn a new drone
      droneController.spawnDrone();
    }
    // Feel listener
    // Move
    // Update
    // Feel force
    // Collide
  }
}

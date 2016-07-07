package main.java.controller;

import main.java.model.World;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.MainModel;

public class GameController {

  private World world;
  private DroneController droneController;

  public GameController(MainModel model) {
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
    for (Drone drone : world.getDrones()) {
      droneController.move(drone, dt);
    }

    // Update
    for (Drone drone : world.getDrones()) {
      droneController.update(drone, dt);
    }

    // Feel force
    // Collide
  }

  public void MousePressed(int button, int x, int y) {
    if (button == 1) { // Left button clicked
      world.getDrones().get(0).setTarget(
          new Vector2D((double) x, (double) y));
    }
  }
}

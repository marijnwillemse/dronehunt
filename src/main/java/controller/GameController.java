package main.java.controller;

import main.java.model.World;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.MainModel;

public class GameController {

  private MainModel model;
  private DroneController droneController;

  public GameController(AppController appController, MainModel model) {
    this.model = model;
    model.setWorld(new World(appController.WIDTH, appController.HEIGHT));
    droneController = new DroneController(model);
  }

  /**
   * Update game
   */
  public void update(double dt) {
    if (model.getWorld().numberOfDrones() == 0) {
      // Spawn a new drone.
      droneController.spawnDrone();
    }

    // Move entities
    for (Drone drone : model.getWorld().getDrones()) {
      droneController.move(drone, dt);
    }

    // Control entities
    for (Drone drone : model.getWorld().getDrones()) {
      droneController.control(drone, dt);
    }
  }

  public void MousePressed(int button, int x, int y) {
    if (button == 1) { // Left button clicked
      model.getWorld().getDrones().get(0).setTarget(
          new Vector2D((double) x, (double) y));
    }
  }
}

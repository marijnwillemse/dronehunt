package main.java.controller;

import java.util.List;

import main.java.math.MathOperations;
import main.java.model.Drone;
import main.java.model.Game;
import main.java.model.MainModel;

public class GameController {

  private MainModel model;
  private Game game;
  private DroneController droneController;

  private double reloadCounter;
  private static final double RELOAD_TIME = 1.0;

  private double spawnCounter;
  private static final double SPAWN_TIME = 0.5;
  private static final double SPAWN_CHANCE = 0.2;

  public GameController(MainModel model) {
    this.model = model;
    game = model.getGame();
    droneController = new DroneController(model);
  }

  /**
   * Update game
   */
  public void update(double dt) {
    // Collect inactive entities
    collect();

    // Move entities
    for (Drone drone : model.getWorld().getDrones()) {
      droneController.move(drone, dt);
    }

    // Control game
    control(dt);

    // Control entities
    for (Drone drone : model.getWorld().getDrones()) {
      droneController.control(drone, dt);
    }
  }

  /**
   * Collects inactive entities in the world and properly removes them from
   * the game.
   */
  private void collect() {
    List<Drone> drones = model.getWorld().getDrones();
    for (int index = 0; index < drones.size(); index++) {
      Drone drone = drones.get(index);
      if (!drone.isActive()) {
        droneController.removeObserver(drone);
        model.getWorld().removeDrone(index);
      }
    }
  }

  private void control(double dt) {
    spawnCounter += dt;
    if (spawnCounter > SPAWN_TIME) {
      spawnCounter = spawnCounter % SPAWN_TIME;
      if (model.getWorld().numberOfDrones() < 2 && Math.random() < SPAWN_CHANCE) {
        // Spawn a new drone.
        String type = (MathOperations.randomBoolean()) ? "QUAD" : "HEXA";
        droneController.spawnDrone(type);
      }
    }

    if (game.getBullets() == 0 && !game.isReloading()) {
      game.startReloading();
    }

    if (game.isReloading()) {
      reloadCounter += dt;
      if (reloadCounter > RELOAD_TIME) {
        game.reload();
        reloadCounter = 0;
      }
    }
  }

  public void MousePressed(int button, int x, int y) {
    if (button == 1) { // Left button clicked
      if (game.getBullets() > 0) {
        droneController.testShot(x, y);
        game.changeBullets(-1);
      }
    }

    // Set drone target
    //    if (button == 1) { // Left button clicked
    //      model.getWorld().getDrones().get(0).setTarget(
    //          new Vector2D((double) x, (double) y));
    //    }

  }

  public void KeyPressed(int keyCode) {

  }
}

package main.java.controller.dronestate;

import java.awt.Rectangle;

import main.java.controller.DroneController;
import main.java.math.MathOperations;
import main.java.math.Vector2D;
import main.java.model.Drone;
import main.java.model.World;

public class DeployState implements State {

  private Drone drone;

  public DeployState(Drone drone) {
    this.drone = drone;
    drone.setPosition(startPosition());
    drone.setTarget(initialTarget());
  }

  @Override
  public void update(DroneController droneController, double dt) {
    if(drone.getY() < 0) {
      drone.setState(new EndState(drone));
    }
  }
  
  private Vector2D startPosition() {
    int x = MathOperations.randomInteger(World.getAreaAir().x,
        World.getAreaAir().x + World.getAreaAir().width);
    return new Vector2D(x, World.GROUND_LINE);
  }
  
  private Vector2D initialTarget() {
    Rectangle areaAir = World.getAreaAir();
    int min = (int) (areaAir.getY() + areaAir.getHeight() * 0.6);
    int max = (int) (areaAir.getY() + areaAir.getHeight());
    int y = MathOperations.randomInteger(min, max);
    
    Vector2D position = drone.getPosition();
    
    return new Vector2D(position.getX(), y);
  }
}

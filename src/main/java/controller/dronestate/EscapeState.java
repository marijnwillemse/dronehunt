package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.model.Drone;

public class EscapeState implements State {

  private Drone drone;

  public EscapeState(Drone drone) {
    this.drone = drone;
    drone.setVelocity(0.0, -200.0);
  }
  
  @Override
  public void update(DroneController droneController, double dt) {
    // TODO Auto-generated method stub
    
  }

}

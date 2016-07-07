package main.java.controller.state;

import main.java.model.Drone;

public class EscapeState implements State {

  private Drone drone;

  public EscapeState(Drone drone) {
    this.drone = drone;
    drone.setVelocity(0.0, -200.0);
  }

  public void update() {
    if(drone.getY() < 0) {
      drone.setState(new EndState(drone));
    }
  }

}

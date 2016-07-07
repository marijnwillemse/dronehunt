package main.java.controller.state;

import main.java.model.Drone;

public class DeployState implements State {

  private Drone drone;

  public DeployState(Drone drone) {
    this.drone = drone;
    drone.setPosition(0.0, -200.0);
  }

  public void update() {
    if(drone.getY() < 0) {
      drone.setState(new EndState(drone));
    }
  }

}

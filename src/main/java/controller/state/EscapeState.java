package main.java.controller.state;

import main.java.model.Drone;

public class EscapeState implements State {

  private Drone unit;

  public EscapeState(Drone unit) {
    this.unit = unit;
    unit.setVelocity(0.0, -200.0);
  }

  public void update() {
    if(unit.getY() < 0) {
      unit.setState(new EndState(unit));
    }
  }

}
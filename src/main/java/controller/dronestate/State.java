package main.java.controller.dronestate;

import main.java.controller.DroneController;
import main.java.model.Drone;

public abstract class State {
  
  protected Drone drone;
  protected int torque = 5000; // Value to calculate owner movement speed with
  protected boolean hittable = true;

  public State(Drone drone) {
    this.drone = drone;
  }
  
  public abstract void update(DroneController droneController, double dt);
  
  public int getTorque() {
    return torque;
  }

  public boolean isHittable() {
    return hittable;
  }
}

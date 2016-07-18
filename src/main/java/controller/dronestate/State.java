package main.java.controller.dronestate;

import main.java.controller.DroneController;

public abstract class State {
  
  protected int torque = 5000; // Value to calculate owner movement speed with
  
  public abstract void update(DroneController droneController, double dt);
  
  public int getTorque() {
    return torque;
  }
}

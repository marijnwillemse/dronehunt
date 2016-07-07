package main.java.model;

import java.awt.Rectangle;

import main.java.controller.state.State;
import main.java.controller.state.WaitState;
import main.java.math.Vector2D;

public class Drone {

  // Basic physics mechanics are saved as vector quantities  
  private Vector2D position;
  private Vector2D velocity;

  private int xSize;
  private int ySize;

  private State state;
  
  private Vector2D target;
  
  private double maxSpeed;
  private double torque;
  private double brakingPower;
  
  public Drone() {		
    position = new Vector2D(0.0, 0.0);
    velocity = new Vector2D(0.0, 0.0);

    this.xSize = 40;
    this.ySize = 40;
    
    maxSpeed = 100000;
    torque = 2000;
    brakingPower = torque;
    

    setState(new WaitState(this));
  }
  
  public Vector2D getPosition() {
    return position;
  }

  public Rectangle getRegion() {
    // Calculate top left corner position
    int x1 = ((int) position.getX()) - xSize / 2;
    int y1 = ((int) position.getY()) - ySize / 2;

    return new Rectangle(x1, y1, xSize, ySize);
  }

  public State getState(){
    return state;
  }

  public Vector2D getTarget() {
    return target;
  }
  
  public Vector2D getVelocity() {
    return velocity;
  }

  public double getX() { return position.getX(); }

  public double getY() { return position.getY(); }
  
  public void setPosition(double x, double y) {
    this.position = new Vector2D(x, y);
  }

  public void setPosition(Vector2D position) {
    this.position = position;
  }

  public void setState(State state) {
    this.state = state;
  }

  public void setTarget(Vector2D target) {
    this.target = target;
  }
  public void setVelocity(double x, double y) {
    this.velocity = new Vector2D(x, y);
  }
  
  public void setVelocity(Vector2D velocity) {
    this.velocity = velocity;
  }
  
  public double getMaxSpeed() {
    return maxSpeed;
  }
  
  public double getTorque() {
    return torque;
  }
  
  public double getBrakingPower() {
    return brakingPower;
  }

  public boolean hasTarget() {
    if (target == null) { return false; }
    return true;
  }

  public boolean hasReachedTarget() {
    if (target == null) { return false; }
    if (position.distanceTo(target) < 1) { return true; }
    return false;
  }
}

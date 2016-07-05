package main.java.model;

import java.awt.Rectangle;

import main.java.controller.state.State;
import main.java.controller.state.WaitState;
import main.java.math.Vector2D;

public class Drone {

  // Basic physics mechanics are saved as vector quantities  
  private Vector2D position;
  private Vector2D velocity;
  private Vector2D acceleration;

  private int xSize;
  private int ySize;

  private State state;
  
  public Drone() {		
    position = new Vector2D(0.0, 0.0);
    velocity = new Vector2D(0.0, 0.0);
    acceleration = new Vector2D(0.0, 0.0);

    this.xSize = 40;
    this.ySize = 40;

    setState(new WaitState(this));
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getState(){
    return state;
  }

  public double getX() { return position.getX(); }
  public double getY() { return position.getY(); }

  public void setPosition(Vector2D position) {
    this.position = position;
  }

  public void setVelocity(Vector2D velocity) {
    this.velocity = velocity;
  }

  public void setAcceleration(Vector2D acceleration) {
    this.acceleration = acceleration;
  }

  public Rectangle getRegion() {
    // Calculate top left corner position
    int x1 = ((int) position.getX()) - xSize / 2;
    int y1 = ((int) position.getY()) - ySize / 2;

    return new Rectangle(x1, y1, xSize, ySize);
  }

  public Vector2D getPosition() {
    return position;
  }

  public Vector2D getVelocity() {
    return velocity;
  }

  public Vector2D getAcceleration() {
    return acceleration;
  }

  public void setPosition(double x, double y) {
    this.position = new Vector2D(x, y);
  }
  public void setVelocity(double x, double y) {
    this.velocity = new Vector2D(x, y);
  }
  public void setAcceleration(double x, double y) {
    this.acceleration = new Vector2D(x, y);
  }
}

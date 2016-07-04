package main.java.model;

import java.awt.Rectangle;

import main.java.controller.state.State;
import main.java.controller.state.WaitState;

public class Drone {

  private double x, y, vx, vy, ax, ay;

  private int xSize;
  private int ySize;

  private State state;
  
  public Drone() {		
    x = 0.0;
    y = 0.0;
    vx = 0.0;
    vy = 0.0;
    ax = 0.0;
    ay = 0.0;

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

  public void setPosition(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() { return x; }
  public double getY() { return y; }

  public void setVelocity(double vx, double vy) {
    this.vx = vx;
    this.vy = vy;
  }

  public Rectangle getRegion() {
    // Calculate top left corner position
    int x1 = ((int) x) - xSize / 2;
    int y1 = ((int) y) - ySize / 2;

    return new Rectangle(x1, y1, xSize, ySize);
  }
}

package main.java.model.unit;

import java.awt.Rectangle;

public abstract class Unit {

  private double x, y, vx, vy, ax, ay;

  private int xSize;
  private int ySize;

  private State state;

  public Unit(int xSize, int ySize) {		
    x = 0.0;
    y = 0.0;
    vx = 0.0;
    vy = 0.0;
    ax = 0.0;
    ay = 0.0;

    this.xSize = xSize;
    this.ySize = ySize;

    setState(new WaitState(this));
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getState(){
    return state;
  }

  public void update(double dt) {
    state.update();
    move(dt);
  }


  private void move(double dt) {
    vx = vx + dt*ax;
    vy = vy + dt*ay;

    x = x + dt*vx;
    y = y + dt*vy;
  }

  public void setPosition(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {	return x; }
  public double getY() {	return y; }

  public void setVelocity(double vx, double vy) {
    this.vx = vx;
    this.vy = vy;
  }

  public void checkBounce() {
    if(x < xSize/2) {
      x = xSize/2;
      vx = -vx; 
    }
    if(x > 256.0 - xSize/2) {
      x = 256.0 - xSize/2;
      vx = -vx;
    }
    if(y < ySize/2) {
      y = ySize/2;
      vy = -vy;
    }
    if(y > 177.0 - ySize/2) {
      y = 177.0 - ySize/2;
      vy = -vy;
    }
  }

  public Rectangle getRegion() {
    // Calculate top left corner position
    int x1 = ((int) x) - xSize / 2;
    int y1 = ((int) y) - ySize / 2;

    return new Rectangle(x1, y1, xSize, ySize);
  }
}

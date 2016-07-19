package main.java.model;

import java.awt.Rectangle;
import java.util.Observable;

import main.java.controller.dronestate.IdleState;
import main.java.controller.dronestate.State;
import main.java.math.Vector2D;

public class Drone extends Observable {

  // Basic physics mechanics are saved as vector quantities.
  private Vector2D position;
  private Vector2D velocity;

  private int xSize;
  private int ySize;

  private State state;
  
  private Vector2D target;
  
  public static final Rectangle HIT_BOX = new Rectangle(0, -4, 18, 18);
  
  private boolean active = true;
  
  private boolean injured = false;
  
  private String type;
  
  public Drone(String type) {		
    position = new Vector2D(0.0, 0.0);
    velocity = new Vector2D(0.0, 0.0);

    this.xSize = 40;
    this.ySize = 40;
    
    this.type = type;
    
    setState(new IdleState(this));
  }
  
  public Vector2D getPosition() {
    return position;
  }

  public Rectangle getRegion() {
    // Calculate top left corner position.
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

  public boolean hasTarget() {
    if (target == null) { return false; }
    return true;
  }

  public boolean hasReachedTarget() {
    if (target == null) { return false; }
    if (position.distanceTo(target) < 1) { return true; }
    return false;
  }

  public Rectangle getHitArea() {
    Rectangle hitArea = new Rectangle(HIT_BOX);
    hitArea.translate((int) position.getX(), (int) position.getY());
    hitArea.translate(-(hitArea.width / 2), -(hitArea.height / 2));
    return hitArea;
  }

  public double getTorque() {
    return state.getTorque();
  }

  public void deactivate() {
    active = false;
    setChanged();
    notifyObservers(active);
  }

  public boolean isActive() {
    return active;
  }
  
  public void injure() {
    if (injured == false) {
      injured = true;
    }
  }
  
  public boolean isHittable() {
    return state.isHittable();
  }

  public boolean isInjured() {
    return injured;
  }

  public String getType() {
    return "type";
  }
}

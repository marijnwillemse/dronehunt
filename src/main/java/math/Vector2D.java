package main.java.math;

public class Vector2D {
  
  protected double x;
  protected double y;

  public Vector2D() {
    x = y = 0.0;
  }

  public Vector2D( double dX, double dY ) {
    this.x = dX;
    this.y = dY;
  }

  public String toString() {
    return "Vector2D(" + x + ", " + y + ")";
  }

  // Calculate the magnitude of the vector.
  public double length() {
    return Math.sqrt ( x*x + y*y );
  }

  // Calculate the sum of a vector with this.
  public Vector2D add(Vector2D v1) {
    Vector2D v2 = new Vector2D(this.x + v1.x, this.y + v1.y);
    return v2;
  }

  // Calculate the subtraction of a vector from this.
  public Vector2D subtract(Vector2D v1) {
    Vector2D v2 = new Vector2D(this.x - v1.x, this.y - v1.y);
    return v2;
  }

  // Calculate this vector multiplied by a scalar.
  public Vector2D scale(double factor) {
    Vector2D v2 = new Vector2D(this.x*factor, this.y*factor);
    return v2;
  }

  // Normalize the vector length.
  public Vector2D normalize() {
    Vector2D v2 = new Vector2D();

    double length = Math.sqrt(this.x*this.x + this.y*this.y);
    if (length != 0) {
      v2.x = this.x/length;
      v2.y = this.y/length;
    }
    return v2;
  }   

  // Calculate the dot product of a vector with this.
  public double dotProduct(Vector2D v2) {
    return this.x * v2.x + this.y * v2.y;
  }
  
  public double distanceTo(Vector2D v2) {
    double distanceX = v2.x - x;
    double distanceY = v2.y - y;
    return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
  }
  
  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void setX(double x) {
    this.x = x;
  }
  public void setY(double y) {
    this.y = y;
  }
}

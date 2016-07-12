package main.java.math;

public abstract class MathOperations {

  /**
   * Generates and returns a random integer number between a floor and a ceil.
   */
  public static int randomInteger(int floor, int ceil) {
    return (int) (floor + Math.floor(Math.random() * ceil));
  }
  
  /**
   * Generates and returns a random double number between a floor and a ceil.
   */
  public static double randomDouble(double floor, double ceil) {
    return floor + Math.random() * ceil;
  }
}
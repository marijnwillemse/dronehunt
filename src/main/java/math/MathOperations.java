package main.java.math;

public abstract class MathOperations {

  /**
   * Generates and returns a random integer between a floor and a ceil number.
   */
  public static int randomInteger(int floor, int ceil) {
    return (int) (floor + Math.floor(Math.random() * ceil));
  }
}
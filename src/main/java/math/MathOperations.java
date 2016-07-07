package main.java.math;

public abstract class MathOperations {

  public static int randomInteger(int floor, int ceil) {
    return (int) (floor + Math.floor(Math.random() * ceil));
  }
  
}

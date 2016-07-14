package main.java.math;

public abstract class MathOperations {

  /**
   * Generates and returns a random integer number between [min, max].
   */
  public static int randomInteger(int min, int max) {
    return min + (int)(Math.random() * ((max - min) + 1));
  }
  
  /**
   * Generates and returns a random double number between [min, max].
   */
  public static double randomDouble(double min, double max) {
    return min + Math.random() * ((max - min) + 1);
  }
}
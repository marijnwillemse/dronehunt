package main.java.controller;

import main.java.controller.dronestate.IdleState;
import main.java.controller.dronestate.State;
import main.java.model.Drone;

public class Brain {

  private static final int INPUT_NEURONS = 4;
  private static final int HIDDEN_NEURONS = 3;
  private static final int OUTPUT_NEURONS = 4;

  private static final double LEARN_RATE = 0.6; // Rho
  private static final int TRAINING_REPS = 10000;

  // Input to Hidden Weights (with Biases).
  private static double weightsInHid[][] = null;

  // Hidden to Output Weights (with Biases).
  private static double weightsHidOut[][] = null;

  // Activations
  private static double inputs[] = null;
  private static double hidden[] = null;
  private static double target[] = null;
  private static double actual[] = null;

  // Unit errors
  private static double errOut[] = null;
  private static double errHid[] = null;

  private static final int MAX_SAMPLES = 18; // Length of arrays below

  
  // TODO: Fill in proper sample input and output cases.
  
  // Health status (2 = healthy, 1 = minor injuries, 0 = major injuries).
  private static final double SAMPLE_HEALTH[] = new double[] {
      2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 
      1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 
      0.0, 0.0, 0.0, 0.0, 0.0, 0.0
  };

  // Number of bullets in player chamber.
  private static final double SAMPLE_BULLETS[] = new double[] {
      0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 
      0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 
      0.0, 0.0, 0.0, 0.0, 1.0, 1.0
  };

  // Drone fire power (1 = much, 0 = little).
  private static final double SAMPLE_POWER[] = new double[] {
      0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 
      0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 
      0.0, 0.0, 1.0, 1.0, 0.0, 0.0
  };

  // Number of drones present.
  private static final double SAMPLE_ENEMY[] = new double[] {
      0.0, 1.0, 1.0, 2.0, 2.0, 1.0, 
      0.0, 1.0, 1.0, 2.0, 2.0, 1.0, 
      0.0, 1.0, 1.0, 2.0, 2.0, 1.0
  };
  private static final double SAMPLE_OUT[][] = new double[][] {
    {0.0, 0.0, 1.0, 0.0}, 
    {0.0, 0.0, 1.0, 0.0}, 
    {1.0, 0.0, 0.0, 0.0}, 
    {1.0, 0.0, 0.0, 0.0}, 
    {0.0, 0.0, 0.0, 1.0}, 
    {1.0, 0.0, 0.0, 0.0}, 
    {0.0, 0.0, 1.0, 0.0}, 
    {0.0, 0.0, 0.0, 1.0}, 
    {1.0, 0.0, 0.0, 0.0}, 
    {0.0, 0.0, 0.0, 1.0}, 
    {0.0, 0.0, 0.0, 1.0}, 
    {0.0, 0.0, 0.0, 1.0}, 
    {0.0, 0.0, 1.0, 0.0}, 
    {0.0, 0.0, 0.0, 1.0}, 
    {0.0, 0.0, 0.0, 1.0}, 
    {0.0, 1.0, 0.0, 0.0}, 
    {0.0, 1.0, 0.0, 0.0}, 
    {0.0, 0.0, 0.0, 1.0}
  };

  private static final String[] OUTPUT_NAMES = new String[] {
      "Attack", "Run", "Wander", "Hide"
  };


  private void observe() {
    // Adjust input values to observed environment
  }

  /**
   * Feed forward input values through the network.
   * 
   * Push the input information forward, from the input nodes, through the
   * hidden nodes and to the output nodes.
   */
  private void feedForward() {

  }

  /**
   * Intelligently chooses a state based on neural net processing.
   * 
   * Feedforwards drone input through the neural net and generates an output.
   * The reaction upon the input is determined by the distribution of weights,
   * which is established by training the network. 
   */
  public State chooseState(Drone drone) {
    return new IdleState(drone);
  }
}

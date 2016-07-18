package main.java.controller;

import java.text.DecimalFormat;
import java.util.Random;

import main.java.controller.dronestate.IdleState;
import main.java.controller.dronestate.State;
import main.java.model.Drone;
import main.java.model.MainModel;

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

  // Drone health status (1 = healthy, 0 = injured).
  private static final double SAMPLE_HEALTH[] = new double[] {
      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
      0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 
      1.0, 1.0, 1.0, 1.0, 1.0, 1.0
  };

  // Number of bullets in the player's gun.
  private static final double SAMPLE_BULLETS[] = new double[] {
      0.0, 1.0, 2.0, 0.0, 1.0, 2.0, 
      0.0, 1.0, 2.0, 0.0, 1.0, 2.0, 
      0.0, 1.0, 2.0, 0.0, 1.0, 2.0
  };

  // Drone fire power (1 = much, 0 = little).
  private static final double SAMPLE_POWER[] = new double[] {
      0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 
      0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 
      0.0, 0.0, 0.0, 1.0, 1.0, 1.0
  };

  // Allied drones present (1 = yes, 0 = no).
  private static final double SAMPLE_ALLY[] = new double[] {
      0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 
      1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 
      0.0, 0.0, 0.0, 1.0, 1.0, 1.0
  };
  private static final double SAMPLE_OUT[][] = new double[][] {
    // ATT  ESC  EVA  HEA      H B P A
    {1.0, 0.0, 0.0, 0.0}, // 0 0 0 0 
    {0.0, 1.0, 0.0, 0.0}, // 0 1 0 0
    {0.0, 1.0, 0.0, 0.0}, // 0 2 0 0
    {1.0, 0.0, 0.0, 0.0}, // 0 0 1 0
    {0.0, 1.0, 0.0, 0.0}, // 0 1 1 0
    {0.0, 0.0, 1.0, 0.0}, // 0 2 1 0
    {0.0, 0.0, 0.0, 1.0}, // 0 0 0 1
    {0.0, 0.0, 0.0, 1.0}, // 0 1 0 1
    {0.0, 0.0, 1.0, 0.0}, // 0 2 0 1
    {1.0, 0.0, 0.0, 0.0}, // 1 0 1 0
    {1.0, 0.0, 0.0, 0.0}, // 1 1 1 0
    {0.0, 0.0, 1.0, 0.0}, // 1 2 1 0
    {1.0, 0.0, 0.0, 0.0}, // 1 0 0 0
    {0.0, 0.0, 1.0, 0.0}, // 1 1 0 0
    {0.0, 0.0, 1.0, 0.0}, // 1 2 0 0
    {1.0, 0.0, 0.0, 0.0}, // 1 0 1 1
    {1.0, 0.0, 0.0, 0.0}, // 1 1 1 1
    {0.0, 0.0, 1.0, 0.0}  // 1 2 1 1
  };

  private static final String[] OUTPUT_NAMES = new String[] {
      "Attack", "Escape", "Evade", "Heal"
  };
  
  private MainModel model;
  
  public Brain(MainModel model) {
    this.model = model;
    init();
    demonstrate();
  }

  private static void init() {
    weightsInHid = new double[INPUT_NEURONS + 1][HIDDEN_NEURONS];
    weightsHidOut = new double[HIDDEN_NEURONS + 1][OUTPUT_NEURONS];
    inputs = new double[INPUT_NEURONS];
    hidden = new double[HIDDEN_NEURONS];
    target = new double[OUTPUT_NEURONS];
    actual = new double[OUTPUT_NEURONS];
    errOut = new double[OUTPUT_NEURONS];
    errHid = new double[HIDDEN_NEURONS];
  }

  private static void demonstrate() {
    double err = 0.0;
    int sampleIndex = 0;
    int iterations = 0;
    int sum = 0;
    boolean stopLoop = false;
    DecimalFormat decimalFormat = new java.text.DecimalFormat("###0.000");

    assignRandomWeights();

    // Train the network.
    while(!stopLoop) {
      sampleIndex++;

      if (sampleIndex == MAX_SAMPLES) {
        sampleIndex = 0;
      }

      inputs[0] = SAMPLE_HEALTH[sampleIndex];
      inputs[1] = SAMPLE_BULLETS[sampleIndex];
      inputs[2] = SAMPLE_POWER[sampleIndex];
      inputs[3] = SAMPLE_ALLY[sampleIndex];

      target[0] = SAMPLE_OUT[sampleIndex][0];
      target[1] = SAMPLE_OUT[sampleIndex][1];
      target[2] = SAMPLE_OUT[sampleIndex][2];
      target[3] = SAMPLE_OUT[sampleIndex][3];

      feedForward();

      err = 0.0;
      for (int i = 0; i < OUTPUT_NEURONS; i++) {
        err += Math.sqrt(SAMPLE_OUT[sampleIndex][i] - actual[i]);
      }
      err = 0.5 * err;

      if (iterations > TRAINING_REPS) {
        stopLoop = true;
      }
      iterations++;

      backPropagate();
    }

    // Test the network.
    for (int i = 0; i < MAX_SAMPLES; i++) {
      inputs[0] = SAMPLE_HEALTH[i];
      inputs[1] = SAMPLE_BULLETS[i];
      inputs[2] = SAMPLE_POWER[i];
      inputs[3] = SAMPLE_ALLY[i];

      target[0] = SAMPLE_OUT[i][0];
      target[1] = SAMPLE_OUT[i][1];
      target[2] = SAMPLE_OUT[i][2];
      target[3] = SAMPLE_OUT[i][3];

      feedForward();

      if (action(actual) != action(target)){
        System.out.println(inputs[0] + "\t" + inputs[1] + "\t" + inputs[2] + "\t" + inputs[3] + 
            "\tActual: " + OUTPUT_NAMES[action(actual)] + 
            "\tExpected: " + OUTPUT_NAMES[action(target)]);
        System.out.println();
      }else{
        sum += 1;
      }
    }

    System.out.println("Network is " + decimalFormat.format(
        (double)sum / ((double)MAX_SAMPLES) * 100.0) + "% correct.");

    // Run some tests.
    System.out.println();
    
    System.out.println("H B P A");
    //          Health            Bullets           Power             Allies
    inputs[0] = 1.0 ; inputs[1] = 2.0 ; inputs[2] = 1.0 ; inputs[3] = 1.0;
    feedForward();
    System.out.println("1-2-1-1 Action: " + OUTPUT_NAMES[action(actual)]);

    inputs[0] = 1.0 ; inputs[1] = 1.0 ; inputs[2] = 1.0 ; inputs[3] = 1.0;
    feedForward();
    System.out.println("1-1-1-1 Action: " + OUTPUT_NAMES[action(actual)]);

    inputs[0] = 0.0 ; inputs[1] = 0.0 ; inputs[2] = 0.0 ; inputs[3] = 0.0;
    feedForward();
    System.out.println("0-0-0-0 Action: " + OUTPUT_NAMES[action(actual)]);

    inputs[0] = 0.0 ; inputs[1] = 1.0 ; inputs[2] = 1.0 ; inputs[3] = 1.0;
    feedForward();
    System.out.println("0-1-1-1 Action: " + OUTPUT_NAMES[action(actual)]);

    inputs[0] = 0.0 ; inputs[1] = 0.0 ; inputs[2] = 1.0 ; inputs[3] = 1.0;
    feedForward();
    System.out.println("0-0-1-1 Action: " + OUTPUT_NAMES[action(actual)]);

    inputs[0] = 1.0 ; inputs[1] = 2.0 ; inputs[2] = 0.0 ; inputs[3] = 0.0;
    feedForward();
    System.out.println("1-2-0-0 Action: " + OUTPUT_NAMES[action(actual)]);

    inputs[0] = 1.0 ; inputs[1] = 1.0 ; inputs[2] = 0.0 ; inputs[3] = 2.0;
    feedForward();
    System.out.println("1-1-0-2 Action: " + OUTPUT_NAMES[action(actual)]);
  }
  
  private static void assignRandomWeights() {
    for (int inp = 0; inp < INPUT_NEURONS; inp++) { // Do not subtract 1 here.
      for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
        // Assign a random weight value between -0.5 and 0.5
        weightsInHid[inp][hid] = new Random().nextDouble() - 0.5;
      }
    }

    for (int hid = 0; hid < HIDDEN_NEURONS; hid++) { // Do not subtract 1 here.
      for (int out = 0; out < OUTPUT_NEURONS; out++) {
        // Assign a random weight value between -0.5 and 0.5
        weightsHidOut[hid][out] = new Random().nextDouble() - 0.5;
      }
    }
  }


  /**
   * Picks the output neuron with the highest value and returns its index.
   */
  private static int action(double[] vector) {
    int selection = 0;

    double max = vector[selection];
    for (int i = 0; i < OUTPUT_NEURONS; i++) {
      if (vector[i] > max) {
        max = vector[i];
        selection = i;
      }
    }
    return selection;
  }

  /**
   * the inputs are fed directly to the outputs via a series of weights.
   * 
   * Pushes the input information forward, from the input nodes, through the
   * hidden nodes and to the output nodes.
   */
  private static void feedForward() {
    double sum = 0.0;

    // Calculate input to hidden layer.
    for (int hidNeuron = 0; hidNeuron < HIDDEN_NEURONS; hidNeuron++) {
      sum = 0.0;

      for (int inNeuron = 0; inNeuron < INPUT_NEURONS; inNeuron++) {
        sum += inputs[inNeuron] * weightsInHid[inNeuron][hidNeuron];
      }

      // Add in bias.
      sum += weightsInHid[INPUT_NEURONS][hidNeuron];
      hidden[hidNeuron] = sigmoid(sum);
    }

    // Calculate the hidden to output layer.
    for (int outNeuron = 0; outNeuron < OUTPUT_NEURONS; outNeuron++) {
      sum = 0.0;

      for (int hidNeuron = 0; hidNeuron < HIDDEN_NEURONS; hidNeuron++) {
        sum += hidden[hidNeuron] * weightsHidOut[hidNeuron][outNeuron];
      }

      // Add in bias.
      sum += weightsHidOut[HIDDEN_NEURONS][outNeuron];
      actual[outNeuron] = sigmoid(sum);
    }
  }
  
  private static void backPropagate() {
    // Calculate the output layer error (step 3 for output cell).
    for (int out = 0; out < OUTPUT_NEURONS; out++) {
      errOut[out] = (target[out] - actual[out]) * sigmoidDerivative(actual[out]);
    }

    // Calculate the hidden layer error (step 3 for hidden cell).
    for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
      errHid[hid] = 0.0;
      for (int out = 0; out < OUTPUT_NEURONS; out++) {
        errHid[hid] += errOut[out] * weightsHidOut[hid][out];
      }
      errHid[hid] *= sigmoidDerivative(hidden[hid]);
    }

    // Update the weights for the output layer (step 4).
    for (int out = 0; out < OUTPUT_NEURONS; out++) {
      for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
        weightsHidOut[hid][out] += (LEARN_RATE * errOut[out] * hidden[hid]);
      }
      // Update the bias.
      weightsHidOut[HIDDEN_NEURONS][out] += (LEARN_RATE * errOut[out]);
    }

    // Update the weights for the hidden layer (step 4).
    for (int hid = 0; hid < HIDDEN_NEURONS; hid++) {
      for (int inp = 0; inp < INPUT_NEURONS; inp++) {
        weightsInHid[inp][hid] += (LEARN_RATE * errHid[hid] * inputs[inp]);
      }
      // Update the bias.
      weightsInHid[INPUT_NEURONS][hid] += (LEARN_RATE * errHid[hid]);
    }
  }

  
  /**
   * A mathematical function having an "S" shaped curve (sigmoid curve).
   * 
   * Neurons use a nonlinear activation function which was developed to model
   * the frequency of action potentials, or firing, of biological neurons in
   * the brain.
   */
  private static double sigmoid(double val) {
    return 1.0 / (1.0 + Math.exp(-val));
  }
  
  private static double sigmoidDerivative(double val) {
    return val * (1.0 - val);
  }

  private void observe() {
    // Adjust input values to observed environment
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

package main.java.view;

import java.util.ArrayList;
import java.util.List;

public class Animation {

  private double timer;   // Current time position in animation.
  private int currentFrame; // Index of current frame in animation.
  private double duration;    // Running length of animation in ms.

  private List<Frame> frames = new ArrayList<Frame>();    // Arraylist of frames 

  public Animation() {
    this.timer = 0;
    this.currentFrame = 0;
  }

  public void reset() {
    timer = 0;
  }

  public void addFrame(Sprite sprite, double duration) {
    if (duration <= 0) {
      System.err.println("Invalid duration: " + duration);
      throw new RuntimeException("Invalid duration: " + duration);
    }
    frames.add(new Frame(sprite, duration));
    refreshLength();
  }

  private void refreshLength() {
    double sum = 0;
    for (Frame frame : frames) {
      sum += frame.duration;
    }
    duration = sum;
  }

  public Sprite getCurrentSprite() {
    return frames.get(currentFrame).sprite;
  }

  public void update(double dt) {
    timer += dt;
    if (timer > duration) {
      // Wrap timer around animation length.
      timer = timer % duration;
    }
    
    double sum = 0;
    for (int i = 0; i < frames.size(); i++) {
      sum += frames.get(i).duration;
      if (sum >= timer) {
        currentFrame = i;
        break;
      }
    }
  }

  public class Frame {

    private Sprite sprite;
    private double duration;

    public Frame(Sprite sprite, double duration) {
      this.sprite = sprite;
      this.duration = duration;
    }
  }

}
package main.java.view.elements;

public class FrameCounter {
  private long nextSecond = System.nanoTime() + 1000000000;
  private int framesInLastSecond = 0;
  private int framesInCurrentSecond = 0;

  public String getFramesPerSecond() {
    long currentTime = System.nanoTime();
    if (currentTime > nextSecond) {
      nextSecond += 1000000000;
      framesInLastSecond = framesInCurrentSecond;
      framesInCurrentSecond = 0;
    }
    framesInCurrentSecond++;
    return Integer.toString(framesInLastSecond);
  }
}

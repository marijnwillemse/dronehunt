package main.java.view;

import java.awt.Color;

public enum GameColors {
  MELON(252, 188, 176),
  DBLUE(60, 188, 252),
  POMEGRANATE(244, 67, 54);

  private GameColors(final Integer red, final Integer green, final Integer blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  private final Integer red, green, blue;

  public Color getRGB() {
    return new Color(red, green, blue);
  }
}
package main.java.view;

import java.awt.image.BufferedImage;

public class SpriteSheet extends Sprite {

  private int TILE_SIZE = 32;

  public SpriteSheet(BufferedImage image, int width, int height) {
    super(image, width, height);
  }


  public BufferedImage getImage(int row, int column) {
      return super.getImage().getSubimage(row * TILE_SIZE, column * TILE_SIZE, TILE_SIZE, TILE_SIZE);
  }
}

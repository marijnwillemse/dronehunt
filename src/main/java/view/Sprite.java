package main.java.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {    
  private Image spriteImage;
  
  // Graphics object for the sprite
  private Graphics2D spriteImageG2D;
  
  private int x;
  private int y;
  private int width;
  private int height;

  /**The double buffer onto which this Sprite object should draw itself */
  BufferedImage spriteDoubleBuffer;
  /**The double buffer's graphics context */
  Graphics2D spriteDoubleBufferG2D;
}
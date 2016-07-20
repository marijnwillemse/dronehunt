package main.java.view;


import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class SpriteLoader {

  private static Map<String, String[]> imageInfo = new HashMap<String, String[]>();

  static {
    imageInfo.put("quadcopter.1", new String[] {"/main/resources/images/quadcopter.1.png", "62", "29"});
    imageInfo.put("quadcopter.2", new String[] {"/main/resources/images/quadcopter.2.png", "62", "29"});
    imageInfo.put("hexacopter.1", new String[] {"/main/resources/images/hexacopter.1.png", "62", "29"});
    imageInfo.put("hexacopter.2", new String[] {"/main/resources/images/hexacopter.2.png", "62", "29"});
    imageInfo.put("bullet", new String[] {"/main/resources/images/bullet.png", "10", "10"});
    imageInfo.put("foreground", new String[] {"/main/resources/images/foreground.png", "256", "224"});
    imageInfo.put("background", new String[] {"/main/resources/images/background.png", "256", "224"});
    imageInfo.put("box.bullet", new String[] {"/main/resources/images/box.bullet.png", "46", "18"});
  }

  private static Map<String, String[]> animationInfo = new HashMap<String, String[]>();

  static {
    animationInfo.put("quadcopter", new String[] {
        "quadcopter.1",
        "quadcopter.2"
    });
    animationInfo.put("hexacopter", new String[] {
        "hexacopter.1",
        "hexacopter.2"
    });
  }

  public Sprite createSprite(String key) {
    String[] info = imageInfo.get(key);
    String imgUrl = info[0];
    int width = Integer.parseInt(info[1]);
    int height = Integer.parseInt(info[2]);

    BufferedImage image = null;
    try {
      image = ImageIO.read(getClass().getResource(imgUrl));
    } catch(Exception ioe) {
      System.out.println("Unable load system image " + imgUrl);
    }
    return new Sprite(image, width, height);
  }
  
  public Animation createAnimation(String key) {
    String[] frameNames = animationInfo.get(key);
    BufferedImage[] frames = new BufferedImage[frameNames.length];
    Animation animation = new Animation(frames, 1);
    return animation;
  }

  public static Map<String, String[]> getImageAdresses() {
    return imageInfo;
  }

  public static Map<String, String[]> getAnimationAdresses() {
    return animationInfo;
  }
}

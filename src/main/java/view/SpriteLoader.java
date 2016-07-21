package main.java.view;


import java.awt.color.ColorSpace;
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
    
    imageInfo.put("drone.hearts.1", new String[] {"/main/resources/images/drone.hearts.1.png", "44", "32"});
    imageInfo.put("drone.hearts.2", new String[] {"/main/resources/images/drone.hearts.2.png", "44", "32"});
    imageInfo.put("drone.hearts.3", new String[] {"/main/resources/images/drone.hearts.3.png", "44", "32"});
    
    imageInfo.put("drone.smoke.1", new String[] {"/main/resources/images/drone.smoke.1.png", "11", "8"});
    imageInfo.put("drone.smoke.2", new String[] {"/main/resources/images/drone.smoke.2.png", "11", "8"});
    imageInfo.put("drone.smoke.3", new String[] {"/main/resources/images/drone.smoke.3.png", "11", "8"});

    imageInfo.put("hexa.fire.1", new String[] {"/main/resources/images/hexa.fire.1.png", "62", "29"});
    imageInfo.put("hexa.fire.2", new String[] {"/main/resources/images/hexa.fire.2.png", "62", "29"});

    imageInfo.put("quad.fire.1", new String[] {"/main/resources/images/quad.fire.1.png", "62", "29"});
    imageInfo.put("quad.fire.2", new String[] {"/main/resources/images/quad.fire.2.png", "62", "29"});

    imageInfo.put("bullet", new String[] {"/main/resources/images/bullet.png", "10", "10"});
    imageInfo.put("foreground", new String[] {"/main/resources/images/foreground.png", "256", "224"});
    imageInfo.put("background", new String[] {"/main/resources/images/background.png", "256", "224"});
    imageInfo.put("box.bullet", new String[] {"/main/resources/images/box.bullet.png", "46", "18"});
  }

  private static Map<String, String[][]> animationInfo = new HashMap<String, String[][]>();

  static {
    animationInfo.put("quadcopter", new String[][] {
      { "quadcopter.1", "0.1" },
      { "quadcopter.2", "0.1" }
    });
    animationInfo.put("hexacopter", new String[][] {
      { "hexacopter.1", "0.1" },
      { "hexacopter.2", "0.1" }
    });
    animationInfo.put("drone.hearts", new String[][] {
      { "drone.hearts.1", "0.1" },
      { "drone.hearts.2", "0.1" },
      { "drone.hearts.3", "0.1" }
    });
    animationInfo.put("drone.smoke", new String[][] {
      { "drone.smoke.1", "0.1" },
      { "drone.smoke.2", "0.1" },
      { "drone.smoke.3", "0.1" }
    });
    animationInfo.put("hexa.fire", new String[][] {
      { "hexa.fire.1", "0.1" },
      { "hexa.fire.2", "0.1" }
    });
    animationInfo.put("quad.fire", new String[][] {
      { "quad.fire.1", "0.1" },
      { "quad.fire.2", "0.1" }
    });
  }

  public Sprite createSprite(String key) {
    String[] info = imageInfo.get(key);
    
    BufferedImage image;
    int width = 1;
    int height = 1;
   
    try {
      image = loadImage(info[0]);
      width = Integer.parseInt(info[1]);
      height = Integer.parseInt(info[2]);

    } catch (Exception e) {
      System.out.println("Unable load system image with key <" + key + ">.\r"
          + "Sprite created as empty 1 x 1 RGB image.");
      image = new BufferedImage(1, 1, ColorSpace.TYPE_RGB);
    }
    
    return new Sprite(image, width, height);
  }

  private BufferedImage loadImage(String Url) {
    try {
      return ImageIO.read(getClass().getResource(Url));
    } catch (Exception e) {
      System.out.println("Unable load system image " + Url);
    }
    return null;
  }

  public Animation createAnimation(String key) {
    Animation animation = new Animation();
    String[][] info = animationInfo.get(key);
    // For every frame
    for (int i = 0; i < info.length; i++) {
      // Process name and duration
      double duration = Double.parseDouble(info[i][1]);
      animation.addFrame(createSprite(info[i][0]), duration);
    }
    return animation;
  }

  public static Map<String, String[]> getImageAdresses() {
    return imageInfo;
  }

  public static Map<String, String[][]> getAnimationAdresses() {
    return animationInfo;
  }
}

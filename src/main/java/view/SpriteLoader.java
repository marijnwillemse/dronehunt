package main.java.view;


import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class SpriteLoader {

  private static Map<String, String[]> imageInfo =
      new HashMap<String, String[]>();
  
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

  public static Map<String, String[]> getImageAdresses() {
    return imageInfo;
  }
  
//  private void drawBackground() {
//    context.setColor(GameColors.DBLUE.getRGB());
//    //    dbg.setColor(GameColors.MELON.getRGB());
//    context.fillRect(0, 0, width, height);
//  }
//
//  private void drawHUD(Graphics g) {
//    resetFont(context, 12);
//
//    //    hudView.drawGameOverBox(g);
//
//    hudView.drawBulletInfo(g, model.getGame());
//
//    // Frame count
//    int fontSize = 12;
//    context.setFont(new Font("Courier", Font.PLAIN, fontSize));
//    context.setColor(Color.white);
//    context.drawString(frameCounter.getFramesPerSecond(), 10, 20);
//  }
//
//

//
//
//  private void drawUnits(double t, Graphics g) {
//    List<Drone> drones = model.getWorld().getDrones();
//    for (Drone drone : drones) {
//      droneView.draw(t, g, drone);
//    }
//  }
//
//  private void drawDebugOverlay(double t, Graphics g) {
//    List<Drone> drones = model.getWorld().getDrones();
//    for (Drone drone : drones) {
//      droneView.drawVelocityVector(t, g, drone);
//      droneView.drawTarget(t, g, drone);
//    }
//  }

}

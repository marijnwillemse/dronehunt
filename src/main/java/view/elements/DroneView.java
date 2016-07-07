package main.java.view.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import main.java.model.Drone;

@SuppressWarnings("serial")
public class DroneView extends JPanel {

  private Image hexaImage1;
  private Image hexaImage2;
  private Image quadImage1;
  private Image quadImage2;

  public DroneView() {
    ImageLoader imageLoader = new ImageLoader();
    hexaImage1 = imageLoader.loadImageIcon("hexacopter.1.png").getImage();
    hexaImage2 = imageLoader.loadImageIcon("hexacopter.2.png").getImage();
    quadImage1 = imageLoader.loadImageIcon("quadcopter.1.png").getImage();
    quadImage2 = imageLoader.loadImageIcon("quadcopter.2.png").getImage();
  }

  public void draw(double t, Graphics g, int x, int y, String droneType) {
    if(droneType.equalsIgnoreCase("QUADCOPTER")) {
      //			g.drawRect(x, y, 40, 29);
      if(t % 0.1 > 0.05) {
        g.drawImage(quadImage1, x, y, this);
      } else {
        g.drawImage(quadImage2, x, y, this);
      }
    }
    if(droneType.equalsIgnoreCase("HEXACOPTER")) {
      //			g.drawRect(x, y, 62, 29);
      if(t % 0.1 > 0.05) {
        g.drawImage(hexaImage1, x, y, this);
      } else {
        g.drawImage(hexaImage2, x, y, this);
      }
    }
  }

  public void draw(double t, Graphics g, Drone drone) {
    if(t % 0.1 > 0.05) {
      g.drawImage(quadImage1, (int)drone.getX(), (int)drone.getY(), this);
    } else {
      g.drawImage(quadImage2, (int)drone.getX(), (int)drone.getY(), this);
    }
  }
  
  public void drawVelocityVector(double t, Graphics g, Drone drone) {
    g.setColor(Color.green);
    g.drawLine((int)drone.getX(), (int)drone.getY(),
        (int)(drone.getX() + drone.getVelocity().getX()/10),
        (int)(drone.getY() + drone.getVelocity().getY()/10));
  }
  
  public void drawTarget(double t, Graphics g, Drone drone) {
    if (drone.hasTarget()) {
      g.setColor((drone.hasReachedTarget() ? Color.blue : Color.red));
      g.drawLine((int)drone.getTarget().getX()-2,
          (int)drone.getTarget().getY()-2,
          (int)drone.getTarget().getX()+2,
          (int)drone.getTarget().getY()+2);
      g.drawLine((int)drone.getTarget().getX()+2,
          (int)drone.getTarget().getY()-2,
          (int)drone.getTarget().getX()-2,
          (int)drone.getTarget().getY()+2);
    }
  }
}

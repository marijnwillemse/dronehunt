package main.java.view.elements;

import javax.swing.ImageIcon;

public class ImageLoader {

  protected ImageIcon loadImageIcon(String imageName) {
    imageName = "/main/resources/images/" + imageName;
    java.net.URL imgUrl = getClass().getResource(imageName);
    ImageIcon icon = null;
    try {
      icon = new ImageIcon(imgUrl);
    } catch(Exception ioe) {
      System.out.println("Unable load system image " + imageName);
    }
    return icon;
  }
}

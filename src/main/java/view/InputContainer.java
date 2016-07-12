package main.java.view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InputContainer {

  private ArrayList<MouseEvent> mouseEvents;
  private ArrayList<KeyEvent> keyEvents;

  public InputContainer() {		
    mouseEvents = new ArrayList<MouseEvent>();
    keyEvents = new ArrayList<KeyEvent>();
  }
  
  public void addKeyEvent(KeyEvent e) {
    keyEvents.add(e);
  }
  
  public void addMouseEvent(MouseEvent e) {
    mouseEvents.add(e);
  }

  /**
   * Clears catched user input
   */
  public void clearInput() {
    mouseEvents.clear();
    keyEvents.clear();
  }

  public ArrayList<MouseEvent> getMouseEvents() {
    return mouseEvents;
  }

  public ArrayList<KeyEvent> getKeyEvents() {
    return keyEvents;
  }
}

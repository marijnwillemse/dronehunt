package main.java.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.java.view.MainView;

public class InputContainer {

  private ArrayList<MouseEvent> mouseEvents; 

  public InputContainer() {		
    mouseEvents = new ArrayList<MouseEvent>();
  }

  /**
   * Setup listener in game view to catch events
   */
  public void setupListeners(MainView view) {
    // Catch mouse events.
    view.getGameView().addMouseListener( new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        mouseEvents.add(e);
      }
    });
  }

  /**
   * Clears catched user input
   */
  public void clearInput() {
    mouseEvents.clear();
  }

  public ArrayList<MouseEvent> getMouseEvents() {
    return mouseEvents;
  }
}

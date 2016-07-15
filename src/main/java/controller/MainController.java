package main.java.controller;

import main.java.model.MainModel;
import main.java.view.MainView;

public class MainController {

  private MainView view;
  private MainModel model;

  private App app;
  private Engine engine;

  public MainController(MainModel model, MainView view) {
    this.view = view;
    this.model = model;
  }

  /**
   * Initialize essential objects and start game engine
   */
  public void run() {
    app = new App(model, view);
    engine = new Engine(app, view);
    view.createAndShowGUI(App.WIDTH, App.HEIGHT,
        App.getViewScale());
    engine.init();
  }
}
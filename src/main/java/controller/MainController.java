package main.java.controller;

import main.java.model.MainModel;
import main.java.view.MainView;

public class MainController {

  private MainView view;
  private MainModel model;

  private AppController app;
  private Engine engine;
  private InputContainer inputContainer;

  public MainController(MainModel model, MainView view) {
    this.view = view;
    this.model = model;
  }

  /**
   * Initialize essential objects and start game engine
   */
  public void run() {
    inputContainer = new InputContainer();
    app = new AppController(model, inputContainer);
    engine = new Engine(app, view);
    view.createAndShowGUI(app.WIDTH, app.HEIGHT, app.VIEW_SCALE);
    inputContainer.setupListeners(view);
    engine.init();
  }
}
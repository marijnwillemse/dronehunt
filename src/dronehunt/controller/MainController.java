package dronehunt.controller;

import dronehunt.model.MainModel;
import dronehunt.view.MainView;

public class MainController {
	
	private MainView view;
	private MainModel model;
	
	private Game game;
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
		game = new Game(model, inputContainer);
		engine = new Engine(game, view);

		view.createAndShowGUI(game.WIDTH, game.HEIGHT, game.VIEW_SCALE);
		inputContainer.setupListeners(view);
		
		engine.init();
	}
}

package main.java.controller;

import main.java.view.MainView;

public class Engine {

	private Game game;
	private MainView view;

	private boolean running;

	public Engine(Game game, MainView view) {
		this.game = game;
		this.view = view;
	}

	/**
	 * The engine runs in a seperate thread so it won't interfere with the EDT
	 */
	public void init() {
		// Place the gameloop in a runnable job
		Runnable gameLoop = new Runnable() { public void run() { gameLoop(); } };
		new Thread(gameLoop).start(); // Then execute it
	}

	/**
	 * Game loop running constantly to update and render game.
	 * Uses a fixed delta time value for the simulation plus
	 * the ability to render at different framerates.
	 * 
	 * The renderer produces time and the simulation consumes
	 * it in discrete dt sized chunks. The leftover time is
	 * passed on to the next frame.
	 * 
	 * Credits: Glenn Fiedler @ gafferongames.com 
	 */
	private void gameLoop() {
		double t = 0.0;

		final double dt = 1 / 60.0;

		double currentTime = timeInSeconds();
		double accumulator = 0.0;

		running = true;

		while (running) {
			double newTime = timeInSeconds();
			double frameTime = newTime - currentTime;
			currentTime = newTime;

			accumulator += frameTime;

			while ( accumulator >= dt ) {
				game.update(dt);
				accumulator -= dt;
				t += dt;
			}
			
			view.refresh(t);
		}
	}

	private double timeInSeconds() {
		return (double) System.nanoTime() / 1000000000.0;
	}
}

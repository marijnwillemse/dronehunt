package dronehunt;

import dronehunt.controller.MainController;
import dronehunt.model.MainModel;
import dronehunt.view.MainView;

/* 
 * Simple Duckhunt clone
 * 
 */

public class Main {

	public static void main(String args[]) {
		
		/* Model View Controller structure initialization */
		
		// Model stores data retrieved to the controller and displayed by the view
		// View requests information from the model to generate an output
		// Controller updates the model state and changes the view presentation

		MainModel model = new MainModel();
		MainView view = new MainView(model);
		MainController controller = new MainController(model, view);
		
		/* Schedule a job for event dispatch thread to create and show the game */
		// Swing code mostly runs on the event dispatch thread because a lot of Swing
		// object methods are not "thread safe." The code below wraps the main methods
		// into a single EDT thread, so as to run the application safely.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				controller.run();
			}
		});
	}

}

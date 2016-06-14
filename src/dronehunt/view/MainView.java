package dronehunt.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import dronehunt.model.MainModel;

public class MainView {

	private MainModel model;

	private JFrame app;				 // JFrame in which the application is displayed
	
	private GamePanel gamePanel;	 // Game screen component panel
	private GameView gameView;		 // Game view drawing panel
	
	public MainView(MainModel model) {
		this.model = model;
	}
	
	public void createAndShowGUI(int width, int height, int viewScale) {
		/* Test to confirm this thread is running as the EDT */
		if (javax.swing.SwingUtilities.isEventDispatchThread()) { System.out.println("GUI creation is running on the EDT"); }
		
		app = new JFrame("Drone Hunt");					    // Create the main game frame
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application when closed
		app.setLocationByPlatform(true); 					// Position frame at platform preference
		
		// Create GUI panels
		gamePanel = new GamePanel();
		gameView = new GameView(model, width, height, viewScale);
		
		showMainScreen(); // Initialize the main screen
	}
	
	private void showMainScreen() {
	    app.getContentPane().removeAll(); // Make sure the frame is clear
	    
	    // Configure components to be assigned to regions
		gamePanel.setLayout(new BorderLayout());
		
		gamePanel.add(gameView, BorderLayout.CENTER);
		
		app.getContentPane().add(gamePanel, "Center"); // Add panel to frame
		
		app.setResizable(false); // Disable window resizing
		app.pack();				 // Adjust window to contents
		// pack must be called after setting resizable to false,
		// for when window is resizeable, layout manager will
		// leave 10px margin for (potential) scrollbars.
		
		app.setVisible(true);	 // Show the frame
	}
	
	public void refresh(double t) {
		gameView.render(t); // Renders the game to an image buffer
		gameView.paintBuffer(); // Draws the buffer onto the screen
	}
	
	public GameView getGameView() {
		return gameView;
	}
	
}

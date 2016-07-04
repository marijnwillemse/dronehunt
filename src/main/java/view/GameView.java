package main.java.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JPanel;

import main.java.model.MainModel;
import main.java.model.level.FinishedLevel;
import main.java.model.level.GameLevel;
import main.java.model.unit.EscapeState;
import main.java.model.unit.Hexacopter;
import main.java.model.unit.Quadcopter;
import main.java.model.unit.Unit;
import main.java.view.elements.DroneView;
import main.java.view.elements.FrameCounter;
import main.java.view.elements.HUDView;
import main.java.view.elements.SceneryView;

@SuppressWarnings("serial")
public class GameView extends JPanel {

	private MainModel model;

	private FrameCounter frameCounter;
	private DroneView droneView;
	private SceneryView sceneryView;
	private HUDView hudView;

	// Draw fields
	private int gameWidth;
	private int gameHeight;
	private int scale;
	private Graphics dbg;
	private Image dbImage = null;

	public GameView(MainModel model, int gameWidth, int gameHeight, int scale) {
		this.model = model;

		frameCounter = new FrameCounter();
		droneView = new DroneView();
		sceneryView = new SceneryView();
		hudView = new HUDView();

		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.scale = scale;

		setPreferredSize(new Dimension(gameWidth*scale, gameHeight*scale));

		this.setBackground(Color.BLACK);
	}

	// Double buffer drawing 
	/* Performs rendering operations in a secondary image
	 * so that it can be repainted directly into the window.
	 * Prevents flickering and keeps paintComponent() simple. */
	public void render(double t)
	// Draw the current frame to an image buffer
	{
		if (dbImage == null) { // Create the buffer
			dbImage = createImage(gameWidth, gameHeight);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}
			else {
				dbg = dbImage.getGraphics();
			}
		}

		// Draw all game elements to the buffer image
		drawBackground();
		sceneryView.drawBackground(dbg);
		drawUnits(t);
		sceneryView.drawForeground(dbg);
		drawHUD();
	}

	private void drawBackground() {
		dbg.setColor(GameColors.DBLUE.getRGB());

		Unit myUnit = model.getActiveUnit();
		if(myUnit != null) {
			if(myUnit.getState() instanceof EscapeState) {
				dbg.setColor(GameColors.MELON.getRGB());
			}
		}
		
		dbg.fillRect(0, 0, gameWidth, gameHeight);
	}

	private void drawHUD() {
		resetFont(dbg, 12);
		
		if(model.getCurrentLevel().isPlaying() == false){
			hudView.drawLevelBox(dbg, model.getLevelPosition()+1);
		}
		
		hudView.drawScoreBox(dbg, model.getScore());
		
		if(model.getCurrentLevel() instanceof FinishedLevel){
			hudView.drawGameOverBox(dbg);
		}

		// Frame count
		int fontSize = 12;
		dbg.setFont(new Font("Courier", Font.PLAIN, fontSize));
		dbg.setColor(Color.white);
		dbg.drawString(frameCounter.getFramesPerSecond(), 10, 20);
	}
	
	private void resetFont(Graphics g, int fontSize) {
		g.setFont(new Font("Courier", Font.PLAIN, fontSize));
		g.setColor(Color.white);
	}


	private void drawUnits(double t) {
		// if current level is a game level
		if(model.getCurrentLevel() instanceof GameLevel) {
			Unit myUnit = model.getActiveUnit();
			if(myUnit != null) {

				Rectangle unitRegion = myUnit.getRegion();

				if (myUnit instanceof Quadcopter) {
					droneView.draw(t, dbg, unitRegion.x, unitRegion.y, "QUADCOPTER");
				}
				if (myUnit instanceof Hexacopter) {
					droneView.draw(t, dbg, unitRegion.x, unitRegion.y, "HEXACOPTER");
				}
			}
		}
	}


	/* Active rendering */
	public void paintBuffer()
	/* Actively render the buffer image to the screen
	 * Puts the task of rendering the buffer image to the screen in my hands.
	 * The call to repaint() is gone and the functionality of paintComponent()
	 * has been incorporated. */
	{
		Graphics g;
		try {
			g = this.getGraphics(); // Get the panel's graphic context
			if ((g != null) && (dbImage != null))
				g.drawImage(dbImage, 0, 0, gameWidth*scale, gameHeight*scale, null);
			Toolkit.getDefaultToolkit().sync(); // Sync the display on some systems
			g.dispose();
		}
		catch (Exception e)
		{ System.out.println("Graphics context error: "+ e); }
	}
}
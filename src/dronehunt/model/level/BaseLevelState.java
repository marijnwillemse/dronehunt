package dronehunt.model.level;

import java.util.ArrayList;
import java.util.UUID;

import dronehunt.model.MainModel;
import dronehunt.model.unit.Unit;

public abstract class BaseLevelState {

	private MainModel model;
	private LevelFactory levelFactory;
	private UUID id;
	
	private boolean playing;
	private boolean finished;
	
	BaseLevelState(MainModel model, LevelFactory levelFactory) {
		this.model = model;
		this.levelFactory = levelFactory;
		
		id = UUID.randomUUID();
		
		finished = false;
	}

	public abstract void update(double dt);
	
	public UUID getID() {
	    return id;
	}
	
	public BaseLevelState nextLevel() {
		BaseLevelState nextLevel = levelFactory.nextLevel(this);

		if (nextLevel == null)
			nextLevel = levelFactory.finished();

		return nextLevel;
	}

	public ArrayList<Unit> getUnits() {
		return null;
	}
	
	public void setFinished(boolean b) {
		finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public abstract void testMousePress(int button, int x, int y);

	public void incrementScore() {
		model.incrementScore();
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public abstract Unit getActiveUnit();

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	public boolean isPlaying() {
		return playing;
	}
}

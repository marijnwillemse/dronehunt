package dronehunt.model.level;

import dronehunt.model.MainModel;
import dronehunt.model.unit.Unit;

public class FinishedLevel extends BaseLevelState {
	
	public FinishedLevel(MainModel model, LevelFactory levelFactory) {
		super(model, levelFactory);
		super.setPlaying(true);
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testMousePress(int button, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Unit getActiveUnit() {
		// TODO Auto-generated method stub
		return null;
	}
}

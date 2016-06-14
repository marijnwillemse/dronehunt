package dronehunt.model.level;

import dronehunt.model.MainModel;

public class GameLevel1 extends GameLevel {
	
	private static String[] units = {"QUADCOPTER", "QUADCOPTER", "QUADCOPTER"};

	public GameLevel1(MainModel model, LevelFactory levelFactory) {
		super(model, levelFactory, units);
	}

}

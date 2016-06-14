package dronehunt.model.level;

import java.util.ArrayList;
import java.util.UUID;

import dronehunt.model.MainModel;

public class LevelFactory {
	
	private MainModel model;
	private ArrayList<BaseLevelState> levelMap;
	
	public LevelFactory(MainModel model) {
		this.model = model;
		levelMap = new ArrayList<BaseLevelState>();
	}
	
	public BaseLevelState createLevel(String levelType) {

		if(levelType == null){
			return null;
		}		

		if(levelType.equalsIgnoreCase("1")){
			return new GameLevel1(model, this);

		}
		else if(levelType.equalsIgnoreCase("2")){
			return new GameLevel2(model, this);
		}

		return null;
	}

	public BaseLevelState nextLevel(BaseLevelState currentLevel) {
		if (currentLevel == null) return null;

		int index = levelPosition(currentLevel);

		if(index == levelMap.size()-1) {
			return null;
		}
		
		else
			return levelMap.get(index+1);
	}
	
	public int levelPosition(BaseLevelState level) {
		UUID id = level.getID();
		
		for(int i=0; i<levelMap.size(); i++) {
			if(levelMap.get(i).getID() == id)
				return i;
		}
		return -1;
	}

	public BaseLevelState finished() {
		System.out.println("Finished!");
		return new FinishedLevel(model, this);
	}

	public void addLevel(BaseLevelState level) {
		levelMap.add(level);
	}

	public BaseLevelState getLevel(int index) {
		return levelMap.get(index);
	}
}

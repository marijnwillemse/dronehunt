package main.java.model;

import java.util.ArrayList;

import main.java.model.level.BaseLevelState;
import main.java.model.level.LevelFactory;
import main.java.model.unit.Unit;

public class MainModel {

  private LevelFactory levelFactory;		// level manager and creator
  
  private Game game;
  private BaseLevelState currentLevel;	// currently active level

  private int score = 0;	// game score

  public MainModel() {
    levelFactory = new LevelFactory(this);
    setupGameLevels();
  }

  /**
   * Manually add game levels and select first as current
   */
  private void setupGameLevels() {
    levelFactory.addLevel(levelFactory.createLevel("1"));
    levelFactory.addLevel(levelFactory.createLevel("2"));
    setCurrentLevel(levelFactory.getLevel(0));
  }

  /**
   * Update mechanics of active level
   */
  public void update(double dt) {
    if(currentLevel.isFinished()) {
      currentLevel = currentLevel.nextLevel();
    } else {
      currentLevel.update(dt);
    }
  }

  public int getScore() { return score; }
  public void incrementScore() { score++; }

  public void setCurrentLevel(BaseLevelState currentLevel) {
    this.currentLevel = currentLevel;
  }

  public BaseLevelState getCurrentLevel() {
    return currentLevel;
  }

  public ArrayList<Unit> getUnitList() {
    return currentLevel.getUnits();
  }

  public int getLevelPosition() {
    return levelFactory.levelPosition(currentLevel);
  }

  public Unit getActiveUnit() {
    return currentLevel.getActiveUnit();
  }
  
  public Game newGame() {
    return game = new Game();
  }

  public Game getGame() {
    return game;
  }
}

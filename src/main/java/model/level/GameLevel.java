package main.java.model.level;

import java.awt.Point;
import java.util.ArrayList;

import main.java.model.MainModel;
import main.java.model.unit.EndState;
import main.java.model.unit.EvadeState;
import main.java.model.unit.Unit;
import main.java.model.unit.UnitFactory;

public abstract class GameLevel extends BaseLevelState {

  private ArrayList<Unit> unitList;	// Container of units

  private UnitFactory unitFactory;	// Maker of units

  private Unit currentUnit;

  private int delayCounter = 180;

  GameLevel(MainModel model, LevelFactory levelFactory, String[] units) {
    super(model, levelFactory);
    super.setPlaying(false);

    unitFactory = new UnitFactory();
    unitList = new ArrayList<Unit>();

    if(units != null) {
      addUnits(units);
    }
  }

  /**
   * Walk through a list of game objects and call an update method for each of them
   */
  @Override
  public void update(double dt) {
    if(super.isPlaying() == true) {

      // if there is no unit on stage
      if (currentUnit == null) {
        // get next unit
        if (!unitList.isEmpty()) {
          Unit nextUnit = unitList.get(0);
          // launch unit
          nextUnit.setState(new EvadeState(nextUnit));
          // set as current unit
          currentUnit = nextUnit;
        }
        else {
          super.setFinished(true);
        }
      }
      // if current unit is finished
      else if(currentUnit.getState() instanceof EndState) {
        // discharge current unit
        currentUnit = null;
        // remove unit from list
        unitList.remove(0);
      }
      else {
        currentUnit.update(dt);
      }
    } else {
      delayCounter--;
      if(delayCounter < 0) {
        super.setPlaying(true);
      }
    }
  }

  private void addUnit(String unitType) {
    unitList.add(unitFactory.createUnit(unitType));
  }


  public ArrayList<Unit> getUnits() {
    return unitList;
  }

  private void addUnits(String[] units) {
    for(String unitType : units) {
      addUnit(unitType);
    }
  }

  public void testMousePress(int button, int x, int y) {
    if (button == 1) { // Left button clicked
      for (Unit myUnit : unitList) {
        if(myUnit.getRegion().contains(new Point(x, y))) {
          if(myUnit.getState() instanceof EvadeState) {
            myUnit.setState(new EndState(myUnit));
            super.incrementScore();
          }
        }
      }
    }
  }

  public Unit getActiveUnit() {
    return currentUnit;
  }
}

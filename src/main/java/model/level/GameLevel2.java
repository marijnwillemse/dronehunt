package main.java.model.level;

import main.java.model.MainModel;

public class GameLevel2 extends GameLevel {

  private static String[] units = {"HEXACOPTER", "QUADCOPTER", "HEXACOPTER", "QUADCOPTER"};

  public GameLevel2(MainModel model, LevelFactory levelFactory) {
    super(model, levelFactory, units);
  }
}

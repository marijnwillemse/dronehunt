package dronehunt.model.unit;

public class EvadeState implements State {
	
	private Unit unit;
	
	private int turnTimer;
	private static final int TURNFLOOR = 15;
	private static final int TURNCEIL = 40;
	
	private int escapeTimer;
	private static final int ESCAPEFLOOR = 120;
	private static final int ESCAPECEIL = 180;
	
	public EvadeState(Unit unit) {
		this.unit = unit;
		
		deploy();
		
		turnTimer = roll(TURNFLOOR, TURNCEIL);
		escapeTimer = roll(ESCAPEFLOOR, ESCAPECEIL);
	}

	private void deploy() {
		unit.setPosition(roll(64, 192), 177);
		unit.setVelocity(0.0, -100.0);
	}

	private int roll(int floor, int ceil) {
		return (int) (floor + Math.floor(Math.random() * ceil));
	}

	public void update() {
		if(turnTimer <= 0) {
			turn();
			turnTimer = roll(TURNFLOOR, TURNCEIL);
		}
		turnTimer--;
		
		if(escapeTimer <= 0) {
			unit.setState(new EscapeState(unit));
		}
		escapeTimer--;
		
		unit.checkBounce();
	}
	
	private void turn() {
		if(unit instanceof Quadcopter){
			int roll = roll(0, 4);
			switch(roll) {
			case 0: unit.setVelocity(100.0, 0.0);
			break;
			case 1: unit.setVelocity(-100.0, 0.0);
			break;
			case 2: unit.setVelocity(0.0, 100.0);
			break;
			case 3: unit.setVelocity(0.0, -100.0);
			break;
			}
		}
		if(unit instanceof Hexacopter){
			int roll = roll(0, 4);
			switch(roll) {
			case 0: unit.setVelocity(65.0, 65.0);
			break;
			case 1: unit.setVelocity(-65.0, 65.0);
			break;
			case 2: unit.setVelocity(65.0, -65.0);
			break;
			case 3: unit.setVelocity(-65.0, -65.0);
			break;
			}
		}
	}
}

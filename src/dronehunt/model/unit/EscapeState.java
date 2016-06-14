package dronehunt.model.unit;

public class EscapeState implements State {
	
	private Unit unit;
	
	public EscapeState(Unit unit) {
		this.unit = unit;
		unit.setVelocity(0.0, -200.0);
	}
	
	public void update() {
		if(unit.getY() < 0) {
			unit.setState(new EndState(unit));
		}
	}

}

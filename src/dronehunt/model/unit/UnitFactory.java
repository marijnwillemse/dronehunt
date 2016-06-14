package dronehunt.model.unit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnitFactory {

	@SuppressWarnings({ "rawtypes", "serial" })
	private static final Map<String,Class> factoryMap =
			Collections.unmodifiableMap(new HashMap<String,Class>() {{
				put("QUADCOPTER", Quadcopter.class);
				put("HEXACOPTER", Hexacopter.class);
			}});


	public Unit createUnit(String unitType) {
		try {
			return (Unit) factoryMap.get(unitType).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}

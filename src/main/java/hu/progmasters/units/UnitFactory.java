package hu.progmasters.units;

import hu.progmasters.enums.CombatUnit;
import hu.progmasters.enums.Units;

import java.util.ArrayList;
import java.util.List;

public class UnitFactory {

    public static List<Unit> unitsList(int quantity, CombatUnit unitType) {
        List<Unit> units = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            units.add(new Unit(unitType.getHp(), unitType.getDamage(), unitType.isDistance(), getLoot(unitType)));
        }
        return units;
    }

    private static int getLoot(CombatUnit unitType) {
        if (unitType instanceof Units) {
            return ((Units) unitType).getLoot();
        }
        return 0;
    }
}

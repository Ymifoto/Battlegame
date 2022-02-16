package hu.progmasters.battle;

import hu.progmasters.enums.CombatUnit;
import hu.progmasters.enums.Kingdom;
import hu.progmasters.units.Player;
import hu.progmasters.units.Unit;

import java.util.List;

public class Opponent {

    private final CombatUnit unitsType;
    private final Player player;

    public Opponent(Player player, CombatUnit unitType) {
        this.unitsType = unitType;
        this.player = player;
    }

    public CombatUnit getUnitsType() {
        return unitsType;
    }

    public Kingdom getKingdom() {
        return player.getKingdom();
    }

    public synchronized void addMoney(int money) {
        player.addMoney(money);
    }

    public List<Unit> getUnits() {
        return player.getArmy().get(unitsType);
    }

    public double getAttackBonus() {
        return player.getAttackBonus();
    }

    public double getDefendBonus() {
        return player.getDefenseBonus();
    }
}

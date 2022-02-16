package hu.progmasters.units;

import hu.progmasters.enums.*;
import hu.progmasters.handlers.BattleLog;
import hu.progmasters.handlers.OutputHandler;

import java.util.*;

public class Player {

    private int money = 5000;
    private final Kingdom kingdom;
    private final Map<CombatUnit, List<Unit>> army = new HashMap<>();
    private int meleeUnits = 0;
    private int rangeUnits = 0;
    private int structuresNumber = 0;
    private double attackBonus;
    private double defenseBonus;

    public Player(Kingdom kingdom) {
        this.kingdom = kingdom;
        setUnitsTypes();
    }

    public void addUnit(CombatUnit unit, int quantity) {
        if (checkResources(unit, quantity)) {
            setUnitNumber(unit, quantity);
            BattleLog.addLog(kingdom.name() + " buy " + quantity + " " + unit.getName());
            army.get(unit).addAll(UnitFactory.unitsList(quantity, unit));
        }
    }

    public String getStatus() {
        statusToLog();
        return new StringBuilder(System.lineSeparator())
                .append(kingdom == Kingdom.MORDOR ? "Mordor's armies:" : "Gondor's defenses:")
                .append(System.lineSeparator())
                .append("Number of melee units: ").append(meleeUnits).append(System.lineSeparator())
                .append("Number of ranged units: ").append(rangeUnits).append(System.lineSeparator())
                .append(kingdom == Kingdom.MORDOR ? "Number of siege weapons: " : "Number of buildings: ")
                .append(structuresNumber).append(System.lineSeparator()).append("Resources: ")
                .append(money).append(System.lineSeparator()).toString();
    }

    public void setBonus() {
        defenseBonus = 1;
        attackBonus = 1;
        for (Map.Entry<CombatUnit, List<Unit>> entry : army.entrySet()) {
            if (entry.getKey() instanceof Structures) {
                defenseBonus += entry.getValue().size() * ((Structures) entry.getKey()).getDefenseBonus();
                attackBonus += entry.getValue().size() * ((Structures) entry.getKey()).getAttackBonus();
            }
        }
    }

    private void setUnitsTypes() {
        List<CombatUnit> unitTypes = new ArrayList<>(Arrays.asList(Structures.values()));
        unitTypes.addAll(Arrays.asList(Units.values()));

        unitTypes.stream().filter(u -> u.getKingdom() == kingdom || u.getKingdom() == Kingdom.COMMON)
                .forEach(u -> army.put(u, Collections.synchronizedList(new ArrayList<>())));
    }

    private void setUnitNumber(CombatUnit unitType, int quantity) {
        if (quantity > 0 && unitType instanceof Units && unitType.isDistance()) {
            rangeUnits += quantity;
        } else if (quantity > 0 && unitType instanceof Units && !unitType.isDistance()) {
            meleeUnits += quantity;
        } else if (quantity > 0 && unitType instanceof Structures) {
            structuresNumber += quantity;
        }
    }

    public void refreshUnitsNumber() {
        rangeUnits = 0;
        meleeUnits = 0;
        structuresNumber = 0;
        army.forEach((k,v) -> setUnitNumber(k,v.size()));
    }

    private boolean checkResources(CombatUnit unit, int quantity) {
        double fullPrice = unit.getPrice() * quantity;
        if (fullPrice <= money) {
            money -= fullPrice;
            return true;
        }
        OutputHandler.outputRed("Not enough resources to buy " + quantity + " " + unit.getName() + System.lineSeparator());
        return false;
    }

    private void statusToLog() {
        BattleLog.addLog(new StringBuffer(kingdom.name())
                .append(": ")
                .append(meleeUnits).append(" melee units, ")
                .append(rangeUnits).append(" ranged units, ")
                .append(structuresNumber).append(" structures, ")
                .append(money).append(" gold.").toString());
    }

    public Map<CombatUnit, List<Unit>> getArmy() {
        return army;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public double getAttackBonus() {
        return attackBonus;
    }

    public double getDefenseBonus() {
        return defenseBonus;
    }

    public int getMeleeUnits() {
        return meleeUnits;
    }

    public int getRangeUnits() {
        return rangeUnits;
    }
}

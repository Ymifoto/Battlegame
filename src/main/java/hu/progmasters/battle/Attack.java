package hu.progmasters.battle;

import hu.progmasters.handlers.OutputHandler;
import hu.progmasters.units.Unit;

import java.util.Random;

public class Attack implements Runnable {

    private int loot = 0;
    private int killedUnits = 0;
    private final Random random;
    private final Opponent attacker;
    private final Opponent defender;

    public Attack(Random random, Opponent attacker, Opponent defender) {
        this.random = random;
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public void run() {
        for (Unit attackerUnit : attacker.getUnits()) {
            if (defender.getUnits().isEmpty()) {
                break;
            }
            int defendersArmySize = defender.getUnits().size();
            Unit defenderUnit = defender.getUnits().get(random.nextInt(defendersArmySize));

            double attackerDamage = attackerUnit.isDistance() ? attackerUnit.getDamage() * attacker.getAttackBonus() : attackerUnit.getDamage();
            defenderUnit.setHp(defenderUnit.getHp() * defender.getDefendBonus() - attackerDamage);
            setUnit(defenderUnit, attackerUnit.getLoot());
        }
        attackResult();
    }

    private void setUnit(Unit defenderUnit, int unitLoot) {
        if (defenderUnit.getHp() <= 0) {
            defender.getUnits().remove(defenderUnit);
            killedUnits += 1;
            loot += unitLoot;
        }
    }

    private void attackResult() {
        if (killedUnits > 0) {
            StringBuilder result = new StringBuilder(attacker.getUnitsType().getName());
            result.append("'s kill ");
            result.append(killedUnits);
            result.append(" ").append(defender.getUnitsType().getName());
            result.append(", ");
            result.append(attacker.getKingdom().name());
            result.append(" steal ").append(loot).append(" gold");
            attacker.addMoney(loot);
            OutputHandler.outputYellow(result.toString());
        }
    }
}

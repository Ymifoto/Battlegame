package hu.progmasters.battle;

import hu.progmasters.enums.Structures;
import hu.progmasters.enums.Units;

import java.util.Set;

public class OpponentSelection {

    public static Opponent getDefender(Opponent attacker, Set<Opponent> attackers) {

        if (attacker.getUnitsType() instanceof Structures) {
            if (((Structures) attacker.getUnitsType()).isCanAttack() && !((Structures) attacker.getUnitsType()).isAttackUnit()) {
                return opponentStructureAgainstStructure(attacker, attackers);
            } else if (((Structures) attacker.getUnitsType()).isCanAttack() && ((Structures) attacker.getUnitsType()).isAttackUnit()) {
                return opponentStructureAgainstUnits(attacker, attackers);
            } else {
                return null;
            }
        }
        return opponentUnitAgainstUnit(attacker, attackers);
    }

    private static Opponent opponentStructureAgainstStructure(Opponent attacker, Set<Opponent> attackers) {

        return attackers.stream()
                .filter(defender -> defender.getUnitsType() instanceof Structures && defender.getKingdom() != attacker.getKingdom())
                .findFirst()
                .orElse(null);
    }

    private static Opponent opponentStructureAgainstUnits(Opponent attacker, Set<Opponent> attackers) {

        return attackers.stream()
                .filter(defender -> defender.getUnitsType() instanceof Units && defender.getKingdom() != attacker.getKingdom() && defender.getUnitsType().isDistance())
                .findFirst()
                .orElse(getInCorrectOpponent(attacker, attackers));
    }

    private static Opponent opponentUnitAgainstUnit(Opponent attacker, Set<Opponent> attackers) {

        return attackers.stream()
                .filter(defender -> attacker.getUnitsType().isDistance() == defender.getUnitsType().isDistance() && defender.getKingdom() != attacker.getKingdom() && defender.getUnitsType() instanceof Units)
                .findFirst()
                .orElse(getInCorrectOpponent(attacker, attackers));
    }

    private static Opponent getInCorrectOpponent(Opponent attacker, Set<Opponent> attackers) {

        return attackers.stream()
                .filter(defender -> defender.getKingdom() != attacker.getKingdom() && defender.getUnitsType() instanceof Units)
                .findFirst()
                .orElse(null);
    }
}
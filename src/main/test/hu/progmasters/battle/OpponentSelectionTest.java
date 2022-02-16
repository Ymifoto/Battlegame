package hu.progmasters.battle;

import hu.progmasters.enums.CombatUnit;
import hu.progmasters.enums.Kingdom;
import hu.progmasters.enums.Structures;
import hu.progmasters.enums.Units;
import hu.progmasters.units.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class OpponentSelectionTest {
    Player mordors;
    Player gondors;
    Set<Opponent> attackers;
    Player[] players = new Player[2];

    @BeforeEach
    void init() {
        mordors = new Player(Kingdom.MORDOR);
        gondors = new Player(Kingdom.GONDOR);
        attackers = new HashSet<>();
        players[0] = mordors;
        players[1] = gondors;
    }

    @Test
    void opponentDistanceUnitsVersusUnits() {
        addUnits();
        initAttackers();

        Opponent defender = OpponentSelection.getDefender(new Opponent(mordors, Units.ORCARCHER),attackers);
        assertTrue(defender.getUnitsType().isDistance());
        assertEquals(Kingdom.GONDOR, defender.getKingdom());

        defender = OpponentSelection.getDefender(new Opponent(mordors, Units.ORC),attackers);
        assertFalse(defender.getUnitsType().isDistance());
        assertEquals(Kingdom.GONDOR, defender.getKingdom());

        List<CombatUnit> distances = gondors.getArmy().keySet().stream().filter(unit -> unit.isDistance()).collect(Collectors.toList());
        distances.stream().forEach(u -> gondors.getArmy().get(u).clear());

        initAttackers();

        defender = OpponentSelection.getDefender(new Opponent(mordors, Units.ORCARCHER),attackers);
        assertFalse(defender.getUnitsType().isDistance());
        assertEquals(Kingdom.GONDOR, defender.getKingdom());
    }

    @Test
    void opponentGroundVersusUnits2() {
        addUnits();
        initAttackers();

        Opponent defender = OpponentSelection.getDefender(new Opponent(mordors, Units.ORC),attackers);
        assertFalse(defender.getUnitsType().isDistance());
        assertEquals(Kingdom.GONDOR, defender.getKingdom());

        List<CombatUnit> notdistances = gondors.getArmy().keySet().stream().filter(unit -> !unit.isDistance()).collect(Collectors.toList());
        notdistances.stream().forEach(u -> gondors.getArmy().get(u).clear());

        initAttackers();
        defender = OpponentSelection.getDefender(new Opponent(mordors, Units.ORC),attackers);

        assertTrue(defender.getUnitsType().isDistance());
        assertEquals(Kingdom.GONDOR, defender.getKingdom());
    }

    @Test
    void opponentUnitsVersusStructures() {
        addStructures();
        initAttackers();
        assertNull(OpponentSelection.getDefender(new Opponent(gondors, Units.ARCHER),attackers));
        assertNull(OpponentSelection.getDefender(new Opponent(mordors, Units.ORC),attackers));
    }


    @Test
    void opponentStructuresVsStructures() {
        addStructures();
        initAttackers();
        assertNull(OpponentSelection.getDefender(new Opponent(mordors, Structures.BALLIST),attackers));
        assertTrue(OpponentSelection.getDefender(new Opponent(mordors, Structures.CATAPULT),attackers).getUnitsType() instanceof Structures);
    }

    @Test
    void opponentStructuresVsUnits() {
        addStructures();
        addUnits();
        initAttackers();

        assertTrue(OpponentSelection.getDefender(new Opponent(mordors, Structures.BALLIST),attackers).getUnitsType().isDistance());
        assertTrue(OpponentSelection.getDefender(new Opponent(mordors, Structures.CATAPULT),attackers).getUnitsType() instanceof Structures);

        List<CombatUnit> distances = gondors.getArmy().keySet().stream().filter(unit -> unit.isDistance()).collect(Collectors.toList());
        distances.stream().forEach(u -> gondors.getArmy().get(u).clear());

        initAttackers();
        assertFalse(OpponentSelection.getDefender(new Opponent(mordors, Structures.BALLIST),attackers).getUnitsType().isDistance());
    }

    void initAttackers() {
        attackers.clear();
        for (Player player : players) {
            player.getArmy().entrySet().stream()
                    .filter(e -> !e.getValue().isEmpty())
                    .forEach(e -> attackers.add(new Opponent(player, e.getKey())));

        }
    }

    void addStructures() {
        Arrays.stream(Structures.values()).filter(s -> s.getKingdom() == Kingdom.MORDOR)
                .forEach(s -> mordors.addUnit(s, 1));
        Arrays.stream(Structures.values()).filter(s -> s.getKingdom() == Kingdom.GONDOR)
                .forEach(s -> gondors.addUnit(s, 1));
    }

    void addUnits() {
        Arrays.stream(Units.values()).filter(unit -> unit.getKingdom() == Kingdom.MORDOR)
                .forEach(unit -> mordors.addUnit(unit, 1));

        Arrays.stream(Units.values()).filter(unit -> unit.getKingdom() == Kingdom.GONDOR)
                .forEach(unit -> gondors.addUnit(unit, 1));
    }
}

package hu.progmasters.battle;

import hu.progmasters.handlers.OutputHandler;
import hu.progmasters.units.OpponentComparator;
import hu.progmasters.units.Player;

import java.util.*;

public abstract class InitBattle {

    private final Player mordors;
    private final Player gondors;
    protected final Random random = new Random();
    protected final Map<Opponent, Opponent> opponentsList = new TreeMap<>(new OpponentComparator());

    public InitBattle(Player mordors, Player gondors) {
        this.mordors = mordors;
        this.gondors = gondors;
    }

    protected boolean checkWinner() {
        if (checkArmySize(mordors)) {
            OutputHandler.outputGreen("Gondor wins the battle");
            return false;
        } else if (checkArmySize(gondors)) {
            OutputHandler.outputGreen("Mordor wins the battle");
            return false;
        }
        return true;
    }

    private boolean checkArmySize(Player player) {
        player.refreshUnitsNumber();
        return player.getMeleeUnits() + player.getRangeUnits() == 0;
    }

    protected void initOpponentsMap() {
        opponentsList.clear();
        Player[] players = {mordors, gondors};
        for (Player player : players) {
            initOpponentKeys(player);
            player.setBonus();
        }
        initOpponentsListValue();
    }

    private void initOpponentKeys(Player player) {
        player.getArmy().entrySet().stream()
                .filter(e -> !e.getValue().isEmpty())
                .forEach(e -> opponentsList.put(new Opponent(player, e.getKey()), null));
    }

    protected void initOpponentsListValue() {
        Set<Opponent> attackers = opponentsList.keySet();
        opponentsList.entrySet().forEach(e -> e.setValue(OpponentSelection.getDefender(e.getKey(), attackers)));
    }
}

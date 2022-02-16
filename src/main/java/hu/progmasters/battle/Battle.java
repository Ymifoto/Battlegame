package hu.progmasters.battle;

import hu.progmasters.handlers.OutputHandler;
import hu.progmasters.units.Player;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Battle extends InitBattle {

    public Battle(Player mordors, Player gondors) {
        super(mordors, gondors);
    }

    public boolean battle(boolean normalMode) {
        initOpponentsMap();

        if (normalMode) {
            normalAttack();
        } else {
            chaoticAttack();
        }

        System.out.println();
        return checkWinner();
    }

    private void normalAttack() {
        for (Map.Entry<Opponent, Opponent> entry : opponentsList.entrySet()) {
            if (entry.getValue() != null) {
                OutputHandler.outputYellow(getUnitDescription(entry.getKey()) + " attack " + getUnitDescription(entry.getValue()));
                new Attack(random, entry.getKey(), entry.getValue()).run();
            }
        }
    }

    private void chaoticAttack() {
        ExecutorService service = Executors.newCachedThreadPool();
        for (Map.Entry<Opponent, Opponent> entry : opponentsList.entrySet()) {
            if (entry.getValue() != null) {
                OutputHandler.outputYellow(getUnitDescription(entry.getKey()) + " attack " + getUnitDescription(entry.getValue()));
                service.submit(new Attack(random, entry.getKey(), entry.getValue()));
            }
        }
        service.shutdown();
        waiting(service);
    }

    private void waiting(ExecutorService service) {
        try {
            service.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getUnitDescription(Opponent opponent) {
        return opponent.getUnitsType().getName() + "'s from " + opponent.getKingdom();
    }
}
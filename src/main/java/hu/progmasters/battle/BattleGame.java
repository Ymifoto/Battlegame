package hu.progmasters.battle;

import hu.progmasters.enums.CommonOptions;
import hu.progmasters.enums.Kingdom;
import hu.progmasters.enums.MenuOptions;
import hu.progmasters.handlers.BattleLog;
import hu.progmasters.handlers.OutputHandler;
import hu.progmasters.units.Player;
import hu.progmasters.userinterface.UserInterface;

public class BattleGame {

    private static final String VERSION = "1.6.0";
    private final UserInterface userInterface = new UserInterface();
    private final Player mordors = new Player(Kingdom.MORDOR);
    private final Player gondors = new Player(Kingdom.GONDOR);
    private boolean normalMode;

    public void battleGame() {
        BattleLog.addLog("Start game - " + VERSION);
        System.out.println("BattleGame " + VERSION);
        normalMode = userInterface.getGameMode() == 1;
        startBattles();
    }
    
    private void startBattles() {
        Battle battle = new Battle(mordors, gondors);
        boolean playing = true;
        while (playing) {
            playing = false;
            OutputHandler.outputCyan("Game mode: " + (normalMode ? "NORMAL" : "CHAOTIC"));
            if (startBuy()) {
                playing = battle.battle(normalMode);
            }
        }
        BattleLog.writeLog();
    }

    private boolean startBuy() {
        MenuOptions commonOptions = userInterface.playerMenu(mordors);
        if (commonOptions == CommonOptions.FINISH) {
            commonOptions = userInterface.playerMenu(gondors);
            return commonOptions == CommonOptions.FINISH;
        }
        return false;
    }
}

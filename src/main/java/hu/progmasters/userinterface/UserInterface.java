package hu.progmasters.userinterface;

import hu.progmasters.enums.CombatUnit;
import hu.progmasters.enums.CommonOptions;
import hu.progmasters.enums.MenuOptions;
import hu.progmasters.handlers.InputHandler;
import hu.progmasters.handlers.OutputHandler;
import hu.progmasters.units.Player;

public class UserInterface extends MenuBuilder {

    InputHandler inputHandler = new InputHandler();
    CommonOptions commonOptions;

    public MenuOptions playerMenu(Player player) {
        createMenu(player.getKingdom());
        return selectOption(player);
    }

    public int getGameMode() {
        int choice;
        do {
        OutputHandler.outputCyan("Select game mode:");
        OutputHandler.outputCyan("1. Normal mode");
        OutputHandler.outputCyan("2. Chaotic mode");
        choice = inputHandler.getInputNumber();
        OutputHandler.outputCyan((choice < 1 || choice > 2) ? "Wrong option!" : "");
        } while (choice < 1 || choice > 2);
        return choice;
    }

    private CommonOptions selectOption(Player player) {
        boolean option;
        do {
            OutputHandler.outputBlue(player.getStatus());
            showOptions();
            System.out.println("Please select an option!");
            option = optionSelect(inputHandler.getInputNumber(), player);
        } while (option);
        return commonOptions;
    }

    private boolean optionSelect(Integer selectedOption, Player player) {
        if (menuItems.containsKey(selectedOption)) {
            return selectedOptionisUnit(menuItems.get(selectedOption), player);
        } else {
            System.out.println("Wrong option!");
            return true;
        }
    }

    private void showOptions() {
        menuItems.forEach((k, v) -> OutputHandler.outputCyan(k + "): recruit/build: " + v + " " + v.getPrice()));
    }

    private boolean selectedOptionisUnit(MenuOptions optionValue, Player player) {
        if (optionValue != CommonOptions.FINISH && optionValue != CommonOptions.QUIT) {
            System.out.println("Please enter the quantity to recruit/build!");
            player.addUnit((CombatUnit) optionValue, inputHandler.getInputNumber());
            return true;
        } else {
            commonOptions = (CommonOptions) optionValue;
            return false;
        }
    }
}

package hu.progmasters.userinterface;

import hu.progmasters.enums.*;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuBuilder {

    protected Map<Integer, MenuOptions> menuItems = new HashMap<>();

    protected void createMenu(Kingdom kingdom) {
        int counter = 0;
        counter = buildMenuItems(counter, kingdom, Units.values());
        counter = buildMenuItems(counter, kingdom, Structures.values());
        buildMenuItems(counter, kingdom, CommonOptions.values());
    }

    private int buildMenuItems(int counter, Kingdom playerKingdom, MenuOptions[] menuOptions) {
        for (MenuOptions menuOption : menuOptions) {
            if (menuOption.getKingdom() == playerKingdom || menuOption.getKingdom() == Kingdom.COMMON || menuOption.getKingdom() == Kingdom.NONE) {
                menuItems.put(counter, menuOption);
                counter++;
            }
        }
        return counter;
    }
}

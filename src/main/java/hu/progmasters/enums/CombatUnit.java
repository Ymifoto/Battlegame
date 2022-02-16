package hu.progmasters.enums;


public interface CombatUnit extends MenuOptions {

    double getAttackOrder();

    boolean isDistance();

    String getName();

    double getHp();

    double getDamage();
}

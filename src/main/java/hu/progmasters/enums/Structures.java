package hu.progmasters.enums;


public enum Structures implements CombatUnit {

    WALL(Kingdom.GONDOR, 75, 150, 0, 0, 0.05, false, false, 1.0),
    TOWER(Kingdom.GONDOR, 75, 100, 0, 0.1, 0, false, false, 1.0),
    CATAPULT(Kingdom.MORDOR, 0, 100, 10, 0, 0, true, false, 1.0),
    BALLIST(Kingdom.MORDOR, 0, 100, 50, 0, 0, true, true, 1.1);

    private final Kingdom kingdom;
    private final double hp;
    private final double price;
    private final double damage;
    private final double attackBonus;
    private final double defenseBonus;
    private final boolean canAttack;
    private final boolean attackUnit;
    private final double attackOrder;

    Structures(Kingdom kingdom, int hp, double price, double damage, double attackBonus, double defenseBonus, boolean canAttack, boolean attackUnit, double attackOrder) {
        this.kingdom = kingdom;
        this.hp = hp;
        this.price = price;
        this.damage = damage;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.canAttack = canAttack;
        this.attackUnit = attackUnit;
        this.attackOrder = attackOrder;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public Kingdom getKingdom() {
        return kingdom;
    }

    @Override
    public double getAttackOrder() {
        return attackOrder;
    }

    @Override
    public boolean isDistance() {
        return true;
    }

    @Override
    public String getName() {
        return name();
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public double getHp() {
        return hp;
    }

    public double getDamage() {
        return damage;
    }

    public double getAttackBonus() {
        return attackBonus;
    }

    public double getDefenseBonus() {
        return defenseBonus;
    }

    public boolean isAttackUnit() {
        return attackUnit;
    }
}

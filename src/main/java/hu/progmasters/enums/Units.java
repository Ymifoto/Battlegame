package hu.progmasters.enums;

public enum Units implements CombatUnit {

    ARCHER(80, 10, 40, 15, true, Kingdom.GONDOR, 2.0),
    SOLDIER(100, 10, 50, 20, false, Kingdom.GONDOR, 5.0),
    KNIGHT(250, 20, 150, 60, false, Kingdom.GONDOR, 5.0),
    ORC(120, 15, 55, 25, false, Kingdom.MORDOR, 3.0),
    TROLL(200, 18, 140, 50, false, Kingdom.MORDOR, 3.0),
    ORCARCHER(100, 8, 40, 15, true, Kingdom.MORDOR, 4.0),
    TOOTH_FAIRY(100, 8, 40, 15, true, Kingdom.COMMON, 4.0);

    private final double hp;
    private final double damage;
    private final double price;
    private final int loot;
    private final boolean distance;
    private final Kingdom kingdom;
    private final double attackOrder;

    Units(double hp, double damage, double price, int loot, boolean distance, Kingdom kingdom, double attackOrder) {
        this.hp = hp;
        this.damage = damage;
        this.price = price;
        this.loot = loot;
        this.distance = distance;
        this.kingdom = kingdom;
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
    public String getName() {
        return name();
    }

    public double getHp() {
        return hp;
    }

    public double getDamage() {
        return damage;
    }

    public int getLoot() {
        return loot;
    }

    public boolean isDistance() {
        return distance;
    }
}

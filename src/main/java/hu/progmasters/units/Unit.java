package hu.progmasters.units;


public class Unit {

    private double hp;
    private double damage;
    private boolean isDistance;
    private int loot;

    public Unit(double hp, double damage, boolean isDistance, int loot) {
        this.hp = hp;
        this.damage = damage;
        this.isDistance = isDistance;
        this.loot = loot;
    }

    public double getHp() {
        return hp;
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
    public double getDamage(){
        return damage;
    }
    public boolean isDistance() {
        return isDistance;
    }
    public int getLoot() {
        return loot;
    }
}

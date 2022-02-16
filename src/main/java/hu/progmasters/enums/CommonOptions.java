package hu.progmasters.enums;

public enum CommonOptions implements MenuOptions {

    FINISH, QUIT;

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public Kingdom getKingdom() {
        return Kingdom.NONE;
    }
}

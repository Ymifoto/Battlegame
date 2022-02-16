package hu.progmasters.units;

import hu.progmasters.battle.Opponent;

import java.util.Comparator;

public class OpponentComparator implements Comparator<Opponent> {

    @Override
    public int compare(Opponent o1, Opponent o2) {

        String o1value = String.valueOf(o1.getUnitsType().getAttackOrder()).replaceAll("\\.", "") + o1.getUnitsType().getName().length() + Math.abs(o1.getKingdom().hashCode()) + o1.getUnitsType().getPrice();
        String o2value = String.valueOf(o2.getUnitsType().getAttackOrder()).replaceAll("\\.", "") + o2.getUnitsType().getName().length() + Math.abs(o2.getKingdom().hashCode()) + o2.getUnitsType().getPrice();

        int unit1Number = Integer.parseInt(o1value.substring(0, 7));
        int unit2Number = Integer.parseInt(o2value.substring(0, 7));

        return unit1Number - unit2Number;
    }
}

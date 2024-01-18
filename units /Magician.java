package units;

import java.util.ArrayList;

public class Magician extends HeroBase{
    protected int mp;
    public Magician(String name, int maxHp, int hp, int armor, int damage, int initiative,
                    double criticalChance, double evasion, int x, int y, boolean liveStatus) {
        super(name, maxHp, hp, armor, damage, initiative, criticalChance, evasion, x, y, liveStatus);
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        if (!this.getLiveStatus()) {
            System.out.println(this + " is dead and disappears from the battlefield forever ...");
            return;
        }
    }
}

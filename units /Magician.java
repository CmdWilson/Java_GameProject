package units;

import java.util.ArrayList;
import java.util.Random;

public class Magician extends HeroBase {
    protected int mp;

    public Magician(String name, int maxHp, int hp, int armor, int damage, int initiative,
                    double criticalChance, double evasion, int x, int y, boolean liveStatus, String actions) {
        super(name, maxHp, hp, armor, damage, initiative, criticalChance, evasion, x, y, liveStatus, actions);
    }

    public HeroBase getMostDamaged(ArrayList<HeroBase> allies) {
        int maxDamage = 0;
        HeroBase mostDamagedHero = null;
        for (HeroBase ally : allies) {
            if (ally.liveStatus) {
                if (mostDamagedHero == null || ally.getHealthReport() > maxDamage) {
                    maxDamage = ally.getHealthReport();
                    mostDamagedHero = ally;
                }
            }
        }
        return mostDamagedHero;
    }

    public int calculateHeal(HeroBase ally){
        Random random = new Random();
        int criticalDamage = 1;
        double randomCritValue = random.nextDouble();
        if (randomCritValue <= this.criticalChance) criticalDamage = 2;
        int currentHeal = -damage * criticalDamage;
        if (currentHeal < -ally.getHealthReport()) currentHeal = -ally.getHealthReport();
        return currentHeal;
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        if (!this.getLiveStatus()) {
            this.actions =  " is dead ...";
            return;
        }
        if (mp <20) {
            mp += 60;
            actions = "Restores half of his mana";
            return;
        }
        HeroBase ally = getMostDamaged(allies);
        if (ally == null) return;
        int currentHeal = calculateHeal(ally);
        if (currentHeal == 0){
            actions = "";
            return;
        }
        ally.getDamage(currentHeal);
        mp -= 20;
        actions = " heal " + ally.name + " on " + -currentHeal;
    }
}

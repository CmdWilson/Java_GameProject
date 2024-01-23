package units;

import java.util.ArrayList;

public class Melee extends HeroBase {

    public Melee(String name, int maxHp, int hp, int armor, int damage, int initiative,
                 double criticalChance, double evasion, int x, int y, boolean liveStatus, String actions) {
        super(name, maxHp, hp, armor, damage, initiative, criticalChance, evasion, x, y, liveStatus, actions);
    }


    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        if (!this.getLiveStatus()) {
            this.actions = "is dead  ...";
            return;
        }
        HeroBase enemy = getNearestEnemy(enemies);
        if (enemy == null) {
            actions = "doesn't has any target";
            return;
        }
        if (this.getDistance(enemy) < 2) getDamageNearestEnemy(enemy, calculateDamage(this, enemy));
        else if (emptyStep(allies, moveTo(enemy))) position = moveTo(enemy);
    }

}

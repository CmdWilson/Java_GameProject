package units;

import java.util.ArrayList;


abstract class Archer extends HeroBase {
    protected int arrows;
    protected float attackDistance;

    public Archer(String name, int maxHp, int hp, int armor, int damage, int initiative,
                  double criticalChance, double evasion, int x, int y, boolean liveStatus, String actions) {
        super(name, maxHp, hp, armor, damage, initiative, criticalChance, evasion, x, y, liveStatus, actions);
    }


    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        if (!this.getLiveStatus()) {
            this.actions = "is dead ...";
            return;
        }
        HeroBase enemy = getNearestEnemy(enemies);
        if (enemy == null) {
            actions = "doesn't have any target";
            return;
        }
        if (this.arrows < 1) {
            actions = "is empty ";
            if (this.getDistance(enemy) < 2) {
                getDamageNearestEnemy(enemy, calculateDamage(this, enemy));
            } else if (emptyStep(allies, moveTo(enemy))) position = moveTo(enemy);
            return;
        }
        this.arrows--;
        int currentDamage = calculateDamage(this, enemy) /
                (int) (1 + this.getDistance(enemy) / attackDistance);
        if (currentDamage == 0) currentDamage = 1;
        if (currentDamage > enemy.hp) currentDamage = enemy.hp;
        enemy.getDamage(currentDamage);
        if (!enemy.getLiveStatus())
            actions = "kill " + enemy.name + " " + currentDamage + " dmg";
        else
            actions = "atk " + enemy.name + " " + currentDamage + " dmg";
    }
}

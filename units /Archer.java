package units;

import java.util.ArrayList;


abstract class Archer extends HeroBase{
    protected int arrows;
    protected float attackDistance;
    public Archer(String name, int maxHp, int hp, int armor, int damage, int initiative,
                  double criticalChance, double evasion, int x, int y, boolean liveStatus, String actions) {
        super(name, maxHp, hp, armor, damage, initiative, criticalChance, evasion, x, y, liveStatus,actions);
    }


    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        if (!this.getLiveStatus()) {
            this.actions =  " is dead ...";
            return;
        }
        HeroBase enemy = getNearestEnemy(enemies);
        if (enemy == null) return;
        if (this.arrows <1 ) {
            actions = " is empty ";
            return;
        }
        this.arrows --;
        int currentDamage = calculateDamage(this, enemy) /
                (int) (1 + this.getDistance(enemy) / attackDistance);
        enemy.getDamage(currentDamage);
        actions = " attack " + enemy.name + " " + currentDamage + " dmg";
    }
}

package units;

import java.util.ArrayList;

abstract class Archer extends HeroBase{
    protected int arrows;
    protected float attackDistance;
    public Archer(String name, int maxHp, int hp, int armor, int damage, int initiative,
                  double criticalChance, double evasion, int x, int y, boolean liveStatus) {
        super(name, maxHp, hp, armor, damage, initiative, criticalChance, evasion, x, y, liveStatus);
    }


    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        if (!this.getLiveStatus()) {
            System.out.println(this + " is dead and disappears from the battlefield forever ...");
            return;
        }
        HeroBase enemy = getNearestEnemy(enemies);
        if (enemy == null) return;
        if (this.arrows <1 ) {
            System.out.println(this + " arrows is empty");
            return;
        }
        this.arrows --;
        int currentDamage = calculateDamage(this, enemy) /
                (int) (1 + this.getDistance(enemy) / attackDistance);
        enemy.getDamage(currentDamage);
        System.out.println(this + " attack " + enemy + " with damage " + currentDamage);
    }
}

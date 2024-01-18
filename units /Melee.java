package units;

import java.util.ArrayList;

public class Melee extends HeroBase {

    public Melee(String name, int maxHp, int hp, int armor, int damage, int initiative,
                 double criticalChance, double evasion, int x, int y, boolean liveStatus) {
        super(name, maxHp, hp, armor, damage, initiative, criticalChance, evasion, x, y, liveStatus);
    }

    protected Coordinates moveTo(HeroBase enemy) {
        Coordinates delta = position.deltaCoordinates(enemy);
        Coordinates destanation = new Coordinates(position.x, position.y);
        if (delta.x < 0) {
            destanation.x++;
            System.out.println(this + " move right to " + enemy);
            return destanation;
        }
        if (delta.x > 0) {
            destanation.x--;
            System.out.println(this + " move left to " + enemy);
            return destanation;
        }
        if (delta.y < 0) {
            destanation.y++;
            System.out.println(this + " move down to " + enemy);
            return destanation;
        }
        if (delta.y > 0) {
            destanation.y--;
            System.out.println(this + " move up to " + enemy);
            return destanation;
        }
        return destanation;
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        if (!this.getLiveStatus()) {
            System.out.println(this + " is dead and disappears from the battlefield forever ...");
            return;
        }
        HeroBase enemy = getNearestEnemy(enemies);
        if (enemy == null) return;
        if (this.getDistance(enemy) < 2) {
            enemy.getDamage(calculateDamage(this, enemy));
            System.out.println(this + " attack " + enemy + " with damage " + calculateDamage(this, enemy));
        } else position = moveTo(enemy);
    }

}

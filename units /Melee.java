package units;

import java.util.ArrayList;

public class Melee extends HeroBase {

    public Melee(String name, int maxHp, int hp, int armor, int damage, int initiative,
                 double criticalChance, double evasion, int x, int y, boolean liveStatus, String actions) {
        super(name, maxHp, hp, armor, damage, initiative, criticalChance, evasion, x, y, liveStatus, actions);
    }

    protected Coordinates moveTo(HeroBase enemy) {
        Coordinates delta = position.deltaCoordinates(enemy);
        Coordinates destanation = new Coordinates(position.x, position.y);
        if (delta.y < 0) {
            destanation.y++;
            actions = " move ►►► " + enemy.name ;
            return destanation;
        }
        if (delta.y > 0) {
            destanation.y--;
            actions = " move ◄◄◄ "  + enemy.name ;
            return destanation;
        }
        if (delta.x < 0) {
            destanation.x++;
            actions = " move ▼▼▼ "  + enemy.name ;
            return destanation;
        }
        if (delta.x > 0) {
            destanation.x--;
            actions = " move ▲▲▲ "  + enemy.name ;
            return destanation;
        }
        return destanation;
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        if (!this.getLiveStatus()) {
            this.actions =  " is dead  ...";
            return;
        }
        HeroBase enemy = getNearestEnemy(enemies);
        if (enemy == null) return;
        if (this.getDistance(enemy) < 2) {
            enemy.getDamage(calculateDamage(this, enemy));
            this.actions = " attack " + enemy.getType() + " " + enemy.name + " " + calculateDamage(this,enemy) + " dmg";
        } else
            if (emptyStep(allies,moveTo(enemy))) position = moveTo(enemy);
    }

}

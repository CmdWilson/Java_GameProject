package units;

import java.util.ArrayList;

public class Monk extends Magician {
    public Monk(String name, int x, int y) {
        super(name, 100, 100, 5,
                50, 3, 0.1, 0.2, x, y, true, "");
        mp = 120;
        maxMp = 120;
    }

    @Override
    public String toString() {
        return ("Monk " + super.toString() + " ☼" + maxMp + "/" + mp + ": " + actions);
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        super.step(enemies, allies);
        if (this.actions.isEmpty()) {
            if (this.mp > maxMp / 2) {
                HeroBase enemy = getMostDamaged(enemies);
                if (enemy == null) {
                    actions = "doesn't have any target";
                    return;
                }
                int currentDamage = (int) (dice() * damage);
                enemy.getDamage(currentDamage);
                actions = "magic atk " + enemy.name + currentDamage + " dmg";
                mp -= maxMp / 2;
            }
            else {
                restoreMp(maxMp /2 );
            }
        }
    }
}

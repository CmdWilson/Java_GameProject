package units;

import java.util.ArrayList;

public class Monk extends Magician {
    public Monk(String name, int x, int y) {
        super(name, 100, 100, 5,
                50, 3, 0.1, 0.2, x, y, true, "");
        mp = 120;
    }

    @Override
    public String toString() {
        return ("Monk " + super.toString() + " Act: " + actions);
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        super.step(enemies, allies);
        if (this.actions.isEmpty()) {
            if (this.mp > 60) {
                HeroBase enemy = getMostDamaged(enemies);
                int currentDamage = (int) (dice() * damage);
                enemy.getDamage(currentDamage);
                actions = "magic attack " + enemy.name + currentDamage + " dmg";
                mp -= 60;
            }
        }
    }
}

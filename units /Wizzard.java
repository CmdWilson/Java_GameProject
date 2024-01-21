package units;

import java.util.ArrayList;

public class Wizzard extends Magician {
    public Wizzard(String name, int x, int y) {
        super(name, 100, 100, 5,
                50, 3, 0.1, 0.2, x, y, true,"");
        mp = 120;
    }

    @Override
    public String toString() {
        return ("Wizzard " + super.toString()) + " Act: " + actions;
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        super.step(enemies, allies);
        if (this.actions.isEmpty()) {
            if (this.mp > 60) {
                for (HeroBase enemy : enemies) {
                    enemy.getDamage((int)(dice() * damage/10));
                }
                actions = "magic attack all light team";
                mp -= 60;
            }
        }
    }
}

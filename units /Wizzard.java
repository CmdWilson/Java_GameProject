package units;

import java.util.ArrayList;

public class Wizzard extends Magician {
    public Wizzard(String name, int x, int y) {
        super(name, 100, 100, 5,
                50, 3, 0.1, 0.2, x, y, true,"");
        mp = 120;
        maxMp =120;
    }

    @Override
    public String toString() {
        return ("Wizzard " + super.toString()) + " ☼" + maxMp + "/" + mp + ": " + actions;
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        super.step(enemies, allies);
        if (this.actions.isEmpty()) {
            int enyEnemy = 0;
            if (this.mp > this.maxMp / 2) {
                for (HeroBase enemy : enemies) {
                    if (enemy.liveStatus) {
                        enemy.getDamage((int) (dice() * damage / 10));
                        enyEnemy ++;
                    }
                }
                if (enyEnemy > 0) actions = "magic attack to " + enyEnemy + "'s enemies " + damage/10 + " dmg";
                else actions = "do nothing because all enemies died";
                mp -= maxMp / 2;
            }
            else {
                restoreMp(maxMp /2 );
            }
        }
    }
}

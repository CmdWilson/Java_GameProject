package units;

import java.util.ArrayList;

public class Rogue extends Melee {
    protected int invisibleStatus;
    protected double baseevasion;

    public Rogue(String name, int x, int y) {
        super(name, 300, 300, 30,
                70, 2, 0.1, 0.1, x, y, true);
        invisibleStatus = 0;
        baseevasion = 0.1;
    }

    @Override
    public String toString() {
        return ("Rogue: " + super.toString());
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {
        Coordinates curentPosition = this.position;
        super.step(enemies, allies);
        invisibleStatus = 0;
        evasion = baseevasion;
        if (curentPosition != this.position){
            invisibleStatus= 1;
            evasion += 0.9 * invisibleStatus;
            System.out.println(this + " became invisible ");
        }
    }
}

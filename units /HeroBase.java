package units;

import java.util.ArrayList;
import java.util.Random;

abstract public class HeroBase implements Game {
    protected String name;
    protected int maxHp, hp, armor, damage, initiative;
    protected double criticalChance, evasion;
    protected Coordinates position;
    protected boolean liveStatus;


    protected HeroBase(String name, int maxHp, int hp, int armor, int damage,
                       int initiative, double criticalChance, double evasion, int x, int y, boolean liveStatus) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.armor = armor;
        this.damage = damage;
        this.initiative = initiative;
        this.criticalChance = criticalChance;
        this.evasion = evasion;
        this.position = new Coordinates(x, y);
        this.liveStatus = liveStatus;
    }

    public float getDistance(HeroBase enemy) {
        return position.distance(enemy.position);
    }
    public int getInitiative(){
        return this.initiative;
    }

    public boolean getLiveStatus() {
        return this.liveStatus;
    }

    public String getType(){
        return this.getClass().getSimpleName();
    }

    public HeroBase getNearestEnemy(ArrayList<HeroBase> enemies){
        HeroBase nearestEnemy = null;
        for (HeroBase enemy : enemies) {
            if (enemy.liveStatus) {
                if (nearestEnemy == null || position.distance(enemy.position) < position.distance(nearestEnemy.position) ) {
                    nearestEnemy = enemy;
                }
            }
        }
        return nearestEnemy;
    }

    public int calculateDamage(HeroBase self, HeroBase enemy) {
        Random random = new Random();
        int criticalDamage = 1;
        int evaletionEffect = 1;
        double randomCritValue = random.nextDouble();
        if (randomCritValue <= self.criticalChance) criticalDamage = 2;
        double randomEvValue = random.nextDouble();
        if (randomEvValue <= enemy.evasion) evaletionEffect = 10;
        return (int) ((self.damage * criticalDamage) * (100 - enemy.armor) * 0.01 / evaletionEffect);
    }


    public void getDamage(int currentDamage){
        if (!this.liveStatus) return;
        this.hp -=currentDamage;
        if (this.hp <= 0){
            this.hp = 0;
            this.liveStatus = false;
        }
    }

    @Override
    public String toString() {
        return (name + position + " HP= " + hp);
    }

    @Override
    public void step(ArrayList<HeroBase> enemies, ArrayList<HeroBase> allies) {

    }
}

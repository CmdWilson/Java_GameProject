import units.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int numberOfTeams = 10;
        createTeams(numberOfTeams);
        ArrayList<HeroBase> heroOrder = new ArrayList<>();
        heroOrder.addAll(lightSide);
        heroOrder.addAll(darkSide);
        heroOrder.sort(Comparator.comparingInt(HeroBase::getInitiative));

//        a temporary solution for checking the algorithms operation
        int step = 0;
        while (true) {
            step++;
            if (containsElements(heroOrder, lightSide)) {
                System.out.println("Darkside team WIN!!!");
                break;
            }
            if (containsElements(heroOrder, darkSide)) {
                System.out.println("Lightside team WIN!!!");
                break;
            }
            System.out.println("Step № " + step + "--------------------------------");
            heroOrder = teemSteps(heroOrder);
        }
    }

    protected static boolean containsElements(ArrayList<HeroBase> allHeroes, ArrayList<HeroBase> team) {
        return team.stream().noneMatch(allHeroes::contains);
    }

    static ArrayList<HeroBase> cleanDeadHeroes(ArrayList<HeroBase> team) {
        ArrayList<HeroBase> newTeam = new ArrayList<>();
        for (HeroBase heroBase : team) {
            if (heroBase.getLiveStatus()) newTeam.add(heroBase);
        }
        return newTeam;
    }

    static ArrayList<HeroBase> teemSteps(ArrayList<HeroBase> order) {
        for (HeroBase hero : order) {
            if (lightSide.contains(hero)) hero.step(darkSide, lightSide);
            else hero.step(lightSide, darkSide);
        }
        return cleanDeadHeroes(order);
    }

    static String getName() {
        return Names.values()[new Random().nextInt(Names.values().length - 1)].toString();
    }

    static void createTeams(int numbers) {
        for (int i = 0; i < numbers; i++) {
            darkSide.add(getRandomHero(random.nextInt(3, 7), numbers - 1, i));
            lightSide.add(getRandomHero(random.nextInt(4), 0, i));


        }
    }

    static void showTeam(ArrayList<HeroBase> team) {
        team.forEach(n -> System.out.println(n.toString()));
    }

    static HeroBase getRandomHero(int choice, int x, int y) {
        return switch (choice) {
            case 0 -> new Monk(getName(), x, y);
            case 1 -> new Pikeman(getName(), x, y);
            case 2 -> new Crossbowman(getName(), x, y);
            case 3 -> new Peasant(getName(), x, y);
            case 4 -> new Sorcerer(getName(), x, y);
            case 5 -> new Rogue(getName(), x, y);
            case 6 -> new Sniper(getName(), x, y);
            default -> null;
        };
    }

    static Random random = new Random();
    static ArrayList<HeroBase> darkSide = new ArrayList<>();
    static ArrayList<HeroBase> lightSide = new ArrayList<>();
}

import units.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfTeams = 10;
        createTeams(numberOfTeams);
        heroOrder.addAll(lightSide);
        heroOrder.addAll(darkSide);
        heroOrder.sort(Comparator.comparingInt(HeroBase::getInitiative));

        Scanner scanner = new Scanner(System.in);
        int steps = 0;
        while (true) {
            View.view();
//            scanner.nextLine();
            if (containsElements(heroOrder, lightSide)) {
                System.out.println("Darkside team WIN!!!");
                break;
            }
            if (containsElements(heroOrder, darkSide)) {
                System.out.println("Lightside team WIN!!!");
                break;
            }
            if (steps == 200) {
                System.out.println("Draw!!!");
                break;
            }
            heroOrder = teemSteps(heroOrder);
            steps++;
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
            darkSide.add(getRandomHero(random.nextInt(3, 7), i + 1, numbers));
            lightSide.add(getRandomHero(random.nextInt(4), i + 1, 1));
        }
    }

    static HeroBase getRandomHero(int choice, int x, int y) {
        return switch (choice) {
            case 0 -> new Monk(getName(), x, y);
            case 1 -> new Pikeman(getName(), x, y);
            case 2 -> new Crossbowman(getName(), x, y);
            case 3 -> new Apprentice(getName(), x, y);
            case 4 -> new Wizzard(getName(), x, y);
            case 5 -> new Rogue(getName(), x, y);
            case 6 -> new Sniper(getName(), x, y);
            default -> null;
        };
    }

    static Random random = new Random();
    static ArrayList<HeroBase> darkSide = new ArrayList<>();
    static ArrayList<HeroBase> lightSide = new ArrayList<>();
    public static ArrayList<HeroBase> heroOrder = new ArrayList<>();
}

import java.util.*;

public class Client {

  public static void main(String[] args) {
    int rounds = 5;
    Game.startGame(rounds);
  }
}

class Game {

  public static void startGame(int maxRounds) {
    Hero hero = new Hero();

    for (int i = 1; i <= maxRounds && hero.isAlive(); i++) {
      Round round = new Round(hero, i);
      round.startRound();

      if (hero.isAlive() && i < maxRounds) {
        hero.chooseUpgrade();
        hero.fullRecover();
      }
    }

    System.out.println("You winned the game!!!");
  }
}

class Round {

  private Hero hero;
  private Queue<Zombie> zombiesQueue;

  public Round(Hero hero, int roundNumber) {
    this.hero = hero;
    initRound(roundNumber);
  }

  private void initRound(int roundNumber) {
    ZombieFactory zombieFactory = ZombieFactory.getInstance();
    zombiesQueue = new LinkedList<Zombie>();
    for (int i = 0; i < roundNumber; i++) {
      zombiesQueue.add(zombieFactory.createRandomZombie());
    }
    System.out.println("-----------------");
    System.out.println("Round - " + roundNumber);
    System.out.println(zombiesQueue);
  }

  public void startRound() {
    while (hero.isAlive() && !zombiesQueue.isEmpty()) {
      Zombie currentZombie = zombiesQueue.poll();
      startBattle(hero, currentZombie);
    }
  }

  private void startBattle(Hero hero, Zombie currentZombie) {
    System.out.println("Battle between " + hero + " and " + currentZombie);
    while (hero.isAlive() && currentZombie.isAlive()) {
      hero.attack(currentZombie);
      currentZombie.attack(hero);
      hero.heal();
      currentZombie.heal();
      // System.out.println(hero);
      // System.out.println(currentZombie);
    }
    if (hero.isAlive()) {
      System.out.println("Winned Battle. Hero HP: " + hero.getHP());
    } else {
      System.out.println("Game over...");
    }
  }
}

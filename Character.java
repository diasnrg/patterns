import java.util.*;

abstract class Character {

  private double damage;
  private double hp;

  protected AttackBehaviour attackBehaviour;
  protected HealBehaviour healBehaviour;

  void attack(Character otherCharacter) {
    this.attackBehaviour.attack(otherCharacter, this.getDamage());
  }

  void heal() {
    this.healBehaviour.heal(this);
  }

  void setAttackBehaviour(AttackBehaviour attackBehaviour) {
    this.attackBehaviour = attackBehaviour;
  }

  void setHealBehaviour(HealBehaviour healBehaviour) {
    this.healBehaviour = healBehaviour;
  }

  public double getDamage() {
    return this.damage;
  }

  public double getHP() {
    return this.hp;
  }

  public void setDamage(double dmg) {
    this.damage = dmg;
  }

  public void setHP(double hp) {
    this.hp = hp;
  }

  boolean isAlive() {
    return this.hp > 0;
  }

  public String toString() {
    String characterName = getClass().getSimpleName();
    if (!isAlive()) {
      return characterName + " - is dead";
    }
    return characterName + " - damage: " + this.damage + ", hp: " + this.hp;
  }
}

// attack behaviour
interface AttackBehaviour {
  void attack(Character otherCharacter, double damage);
}

class SimpleAttackBehaviour implements AttackBehaviour {

  public void attack(Character otherCharacter, double damage) {
    otherCharacter.setHP(otherCharacter.getHP() - damage);
  }
}

// heal behaviour
interface HealBehaviour {
  void heal(Character character);
}

class SimpleHealBehaviour implements HealBehaviour {

  private double healValue = 1;

  public void heal(Character character) {
    int r = (new Random()).nextInt(2);
    switch (r) {
      case 0:
        character.setHP(character.getHP() + healValue);
    }
  }
}

class NoHealBehaviour implements HealBehaviour {

  public void heal(Character character) {
    // no healing
  }
}

abstract class Zombie extends Character {

  protected Zombie(double damage, double hp) {
    this.setDamage(damage);
    this.setHP(hp);
    this.setAttackBehaviour(new SimpleAttackBehaviour());
    this.setHealBehaviour(new NoHealBehaviour());
  }
}

class SimpleZombie extends Zombie {

  public SimpleZombie() {
    super(3, 5);
  }
}

class DynamiteZombie extends Zombie {

  public DynamiteZombie() {
    super(3, 5);
    setAttackBehaviour(new DynamiteDecorator(this.attackBehaviour));
  }
}

class HealerZombie extends Zombie {

  public HealerZombie() {
    super(1, 10);
    setHealBehaviour(new SimpleHealBehaviour());
  }
}

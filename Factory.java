import java.util.Random;

class ZombieFactory {

  private ZombieFactory() {}

  private static ZombieFactory instance;

  public static ZombieFactory getInstance() {
    if (instance == null) {
      instance = new ZombieFactory();
    }
    return instance;
  }

  public Zombie createRandomZombie() {
    int r = (new Random()).nextInt(5);
    switch (r) {
      case 0:
        return new DynamiteZombie();
      case 1:
        return new HealerZombie();
      default:
        return new SimpleZombie();
    }
  }
}

class WeaponFactory {

  private WeaponFactory() {}

  private static WeaponFactory instance;

  public static WeaponFactory getInstance() {
    if (instance == null) {
      instance = new WeaponFactory();
    }
    return instance;
  }

  public WeaponAttackDecorator createRandomWeapon(
    AttackBehaviour attackBehaviour
  ) {
    int r = (new Random()).nextInt(5);
    switch (r) {
      case 0:
        return new GunDecorator(attackBehaviour);
      case 1:
        return new DynamiteDecorator(attackBehaviour);
      case 2:
        return new DeactivateHealBehaviourDecorator(attackBehaviour);
      default:
        return new KnifeDecorator(attackBehaviour);
    }
  }
}

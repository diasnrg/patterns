import java.util.Random;

abstract class WeaponAttackDecorator implements AttackBehaviour {

  protected AttackBehaviour attackBehaviour;

  protected WeaponAttackDecorator(AttackBehaviour attackBehaviour) {
    this.attackBehaviour = attackBehaviour;
  }

  public String toString() {
    return getClass().getSimpleName();
  }
}

class KnifeDecorator extends WeaponAttackDecorator {

  private double knifeDamage = 2;

  public KnifeDecorator(AttackBehaviour attackBehaviour) {
    super(attackBehaviour);
  }

  @Override
  public void attack(Character otherCharacter, double damage) {
    this.attackBehaviour.attack(otherCharacter, damage + this.knifeDamage);
  }
}

class GunDecorator extends WeaponAttackDecorator {

  private double gunDamage = 2;

  public GunDecorator(AttackBehaviour attackBehaviour) {
    super(attackBehaviour);
  }

  @Override
  public void attack(Character otherCharacter, double damage) {
    int r = (new Random()).nextInt(5);
    switch (r) {
      case 0:
        this.attackBehaviour.attack(otherCharacter, damage * gunDamage);
        System.out.println(this + " activated by " + otherCharacter.getClass());
        break;
      default:
        this.attackBehaviour.attack(otherCharacter, damage);
    }
  }
}

class DeactivateHealBehaviourDecorator extends WeaponAttackDecorator {

  public DeactivateHealBehaviourDecorator(AttackBehaviour attackBehaviour) {
    super(attackBehaviour);
  }

  @Override
  public void attack(Character otherCharacter, double damage) {
    int r = (new Random()).nextInt(5);
    switch (r) {
      case 0:
        otherCharacter.setHealBehaviour(new NoHealBehaviour());
      default:
        this.attackBehaviour.attack(otherCharacter, damage);
    }
  }
}

class DynamiteDecorator extends WeaponAttackDecorator {

  private double dynamiteDamage = 15;
  private boolean activated = false;

  public DynamiteDecorator(AttackBehaviour attackBehaviour) {
    super(attackBehaviour);
  }

  @Override
  public void attack(Character otherCharacter, double damage) {
    int r = (new Random()).nextInt(10);
    switch (r) {
      case 0:
        if (!activated) {
          this.attackBehaviour.attack(otherCharacter, dynamiteDamage);
          activated = true;
          System.out.println(
            this + " activated by " + otherCharacter.getClass()
          );
        }
        break;
      default:
        this.attackBehaviour.attack(otherCharacter, damage);
    }
  }
}

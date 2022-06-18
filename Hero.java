import java.util.Scanner;

// hero
class Hero extends Character {

  private double defaultDamage = 3;
  private double defaultHP = 10;

  public Hero() {
    this.setDamage(defaultDamage);
    this.setHP(defaultHP);
    this.setAttackBehaviour(new SimpleAttackBehaviour());
    this.setHealBehaviour(new SimpleHealBehaviour());
    // -------------------
    System.out.println(this);
  }

  public void fullRecover() {
    this.setHP(defaultHP);
  }

  public void chooseUpgrade() {
    double increaseHP = 5;
    WeaponAttackDecorator randomWeaponDecorator = WeaponFactory
      .getInstance()
      .createRandomWeapon(this.attackBehaviour);

    System.out.println(
      "Upgrade the attack behaviour by " +
      randomWeaponDecorator +
      ", or by increasing the HP to " +
      increaseHP
    );
    System.out.println("Press 1 or 2");

    Scanner in = new Scanner(System.in);
    int choice = in.nextInt();

    if (choice == 1) {
      this.setAttackBehaviour(randomWeaponDecorator);
      System.out.println(
        "Upgraded the attack behaviour with " + randomWeaponDecorator
      );
    } else {
      this.defaultHP += increaseHP;
      System.out.println("HP increased by " + increaseHP);
    }
  }
}

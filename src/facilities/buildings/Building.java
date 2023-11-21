package facilities.buildings;

/**
 * interface Building is an abstract class that is used to group methods with the same methods
 */
public interface Building { //What ever is implements building must have these 4 methods

  /**
   * returns level
   * @return level
   */
  int getLevel();

  /**
   * increases level of facility
   */
  void increaseLevel();

  /**
   * returns upgradeCost
   * @return upgradeCost
   */
  int getUpgradeCost();

  /**
   * returns capacity
   * @return capacity
   */
  int getCapacity();

  /**
   * returns max level
   * @return maxLevel
   */
  int getMaxLevel();
}

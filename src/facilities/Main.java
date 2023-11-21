package facilities;

import facilities.buildings.Hall;
import facilities.buildings.Lab;
import facilities.buildings.Theatre;

/**
 * where any code is run
 */
public class Main {

  /**
   * where code is run
   * @param args
   */
  public static void main(String[] args) {
    Hall hall = new Hall("Hall");
    Lab lab = new Lab("Lab");
    Theatre theatre = new Theatre("Theatre");
    hall.increaseLevel();
    System.out.println(hall.getLevel());
    System.out.println(hall.getCapacity());
    System.out.println(hall.getUpgradeCost());
  }
}
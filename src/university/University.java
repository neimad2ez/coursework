package university;

import facilities.Facility;
import facilities.buildings.Building;
import facilities.buildings.Hall;
import facilities.buildings.Lab;
import facilities.buildings.Theatre;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * University class keeps information on the University
 */
public class University {
  HumanResource humanResource;
  float budget;
  public Estate estate;
  int reputation;
  ArrayList<Facility> uniArray;
  int hasMoney = 1;

  /**
   * creates constructor University and initialises budget, estate, uniArray and humanResource
   * @param funding
   * initial budget
   */
  public University(int funding) {
    budget = funding;
    estate = new Estate();
    uniArray = new ArrayList<Facility>();
    humanResource = new HumanResource();
  }

  /**
   * returns staffSalary
   * @return staffSalary
   */
  public HashMap<Staff, Float> arrayStaffSalary() {
    return humanResource.arrayStaffSalary();
  }

  /**
   * returns total salary of staffSalary
   * @return total salary of staffSalary
   */
  public float getTotalSalary() {
    return humanResource.getTotalSalary();
  }

  /**
   * addStaff method from humanResource class
   * @param staff
   * object from Staff class
   */
  public void addStaff(Staff staff) {
    humanResource.addStaff(staff);
  }

  /**
   * adds rep accumulated to reputation in University class
   * @param rep
   * reputation
   */
  public void addReputation(int rep) {
    reputation += rep;
  }

  /**
   * returns true or false depending if building is max level
   * @param name
   * @return boolean value true or false depending on if building is max level
   */
  public Boolean checkMaxLevel(String name) {
    for (Facility f: estate.getFacilities()) {
      if (f.getName().equals(name)) {
        if (f instanceof Building) {
          if (((Building) f).getLevel() == ((Building) f).getMaxLevel()) {
            return true;
          }
        }
      }
    }
    return false;
  }


  /**
   * upgrades facility to next level
   * @param num
   * number of times it wants to iterate
   * @param name
   * name of the facility
   * @throws Exception
   * if upgrade can't be run
   */
  public void upgradeFacility(int num, String name) throws Exception {
    for (int i = 0; i < num; i++) {
      for (Facility k : estate.getFacilities()) {
        if (k.getName().equals(name)) {
          System.out.println("Upgrade cost is " + ((Building) k).getUpgradeCost());
          upgrade((Building) k);
          if (hasMoney ==1) {
            System.out.println(name + " has been upgraded to level " + ((Building) k).getLevel() + "!");
          }
        }
      }
    }
  }

  /**
   * returns number of staff in staffSalary
   * @return number of staff in staffSalary
   */
  public int getNumOfStaff() {
    return humanResource.getNumOfStaff();
  }

  /**
   * returns reputation
   * @return reputation
   */
  public int getReputation() {
    return reputation;
  }

  /**
   * returns budget
   * @return budget
   */
  public float getBudget() {
    return budget;
  }

  /**
   * checks if budget is enough to build
   * @param type
   * type of facility e.g Hall, Lab, Theatre
   * @return boolean (if enough money)
   */
  private boolean enoughMoney(String type) {
    if (type.equals("Hall") && budget >= 100) {
      return true;
    } else if (type.equals("Lab") && budget >= 200) {
      return true;
    } else if (type.equals("Theatre") && budget >= 300) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * this method creates a new building
   * @param type
   * defines its type e.g "Hall", "Lab" and "Theatre"
   * @param name
   * defines its name
   * @return facility built
   */
  public Facility build(String type, String name) { //Builds a new facility taking in type and name as a parameter
    boolean hasEnoughMoney = enoughMoney(type);
    if(hasEnoughMoney) {
      Facility j = estate.addFacility(type, name);
      uniArray.add(j);
      if (j instanceof Hall) { //checks if Facility j is a part of class Hall
        int hallBase = ((Hall) j).getBaseBuildingCost(); //sets hallBase as the baseBuildingCost of Hall j
        budget -= hallBase; //Reduces budget by the hall base building cost
        reputation += 100; //Increase reputation by 100 per new facility built
        System.out.println(name + " has just been built!");
        System.out.println("Reputation has increased by 100 for building a new facility!");
        return j;
      } else if (j instanceof Lab) {
        int labBase = ((Lab) j).getBaseBuildingCost();
        budget -= labBase;
        reputation += 100;
        System.out.println(name + " has just been built!");
        System.out.println("Reputation has increased by 100 for building a new facility!");
        return j;
      } else if (j instanceof Theatre) {
        int theatreBase = ((Theatre) j).getBaseBuildingCost();
        budget -= theatreBase;
        reputation += 100;
        System.out.println(name + " has just been built!");
        System.out.println("Reputation has increased by 100 for building a new facility!");
        return j;
      }
    }
    return null; //return null if not enough money
  }

  /**
   * checks if building is a part of the university, if it is it will check if building is max level, if not then it checks if budget is enough to increase buildingLevel, if it is enough then it upgrades the building
   * @param building
   * any facility which implements building
   * @throws Exception
   * if building not found
   */
  public void upgrade(Building building) throws Exception { //Upgrade the level of the input building
    boolean isFound = false; // Initialises isFound
    Facility[] array = estate.getFacilities(); // creates Array called array
    //estate.getFacilities get the facilities inside estate class defined in University
    for(Facility j: array) { // Loops through each element in array
      if(j instanceof Hall) { // If j is an element of Hall then it is part of the University
        if(j.equals(building)) { // Checks if j is a building
          isFound = true; // Sets isFound to true
        }
      } else if(j instanceof Lab) { // If j is an element of Lab then it is a part of University
        if(j.equals(building)) { // Checks if j is a building
          isFound = true; // Sets isFound to true
        }
      } else if(j instanceof Theatre) { // If j is an element of Theatre then it is a part of University
        if(j.equals(building)) { // Checks if j is a building
          isFound = true; //Sets isFound to True
        }
      }
    }
    if(!isFound) { // Checks if isFound is false
      throw new Exception("Building not found"); // Throw exception building not found if isFound is false
    }
    if(isFound) { // Checks if isFound is true, if true move on to next if statement
      if (building instanceof Hall) { // Checks if building is in class Hall
        if (building.getLevel() < ((Hall) building).getMaxLevel()) { // Checks if the buildings level is
        //less than the max level
          int cost = building.getUpgradeCost(); // If building level less than max level then it initialises
          // cost, cost gets upgradeCost which is the cost of the upgrading the building to the level above
          if(cost <= budget) { // Checks if budget can handle upgrade
            building.increaseLevel(); // If budget can handle upgrade, it increases the level through increaseLevel() method
            budget -= cost; // Subtracts the cost from the budget
            reputation += 50; // Increases reptuation by 50 everytime a building is upgraded
            System.out.println("Reputation has increased by 50!");
          } else {
            System.out.println("Upgrade can't be afforded");
            hasMoney = 0;
          }
        } else {
          throw new Exception("Hall is max level and can't be upgraded further"); // If level == maxLevel throw exception
        }
      } else if (building instanceof Lab) { // Checks if building is in class Lab
        if (building.getLevel() < ((Lab) building).getMaxLevel()) { // Checks if the buildings level is
          //less than the max level
          int cost = building.getUpgradeCost();
          if(cost <= budget) {
            building.increaseLevel();
            budget -= cost;
            reputation += 50;
            System.out.println("Reputation has increased by 50!");
          } else {
            System.out.println("Upgrade can't be afforded");
            hasMoney = 0;
          }
        } else {
          throw new Exception("Lab is max level and can't be upgraded further"); // If level == maxLevel throw exception
        }
      } else if (building instanceof Theatre) {
        if (building.getLevel() < ((Theatre) building).getMaxLevel()) {
          int cost = building.getUpgradeCost();
          if(cost <= budget) {
            building.increaseLevel();
            budget -= cost;
            reputation += 50;
            System.out.println("Reputation has increased by 50!");
          } else {
            System.out.println("Upgrade can't be afforded");
            hasMoney = 0;
          }
        } else {
          throw new Exception("Theatre is max level and can't be upgraded further"); // If level == maxLevel throw exception
        }
      }
    } else {
      throw new Exception("Building is not part of the University"); // If building not an instance of
      // Hall, Lab or Theatre then throws an exception
    }
  }

  /**
   * increases budget by input
   * @param num
   * amount of students * 10 (ECS Coins)
   */
  public void increaseBudget(float num) {
    budget += num;
  }

//  public static void main(String[] args) throws Exception {
//    University uni = new University(10000);
//    Hall hall = new Hall("John");
//    Facility j = uni.build("Hall", "John");
//    if(j instanceof Hall) { //How to check if building is a part of University
//      System.out.println(((Hall) j).getLevel());
//      uni.upgrade((Building) j);
//      System.out.println(uni.getBudget());
//      System.out.println(((Hall) j).getLevel());
//    }
//  }
}

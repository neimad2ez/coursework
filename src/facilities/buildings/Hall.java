package facilities.buildings;

import facilities.Facility;

/**
 * type of building which houses students
 */
public class Hall extends Facility implements Building{
    int level = 1;
    int baseBuildingCost = 100;
    int maxLevel = 4;
    int baseCapacity = 6;

    /**
     * super calls constructor, methods and properties of parent class
     * @param name
     * name of the hall
     */
    public Hall(String name) {
        super(name);
    }

    /**
     * returns level
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * retunrs baseBuildingCost
     * @return baseBuildingCost
     */
    public int getBaseBuildingCost() {
        return baseBuildingCost;
    }

    /**
     * returns baseCapacity
     * @return baseCapacity
     */
    public int getBaseCapacity() {
        return baseCapacity;
    }

    /**
     * returns maxLevel
     * @return maxLevel
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * increases level by 1
     */
    public void increaseLevel() { //Increase level of hall
        if(level != maxLevel) {
            level += 1;
        }
    }

    /**
     * returns upgradeCost
     * @return upgradeCost for next level
     */
    public int getUpgradeCost() { //Returns the amount of ECS coins needed to get to the next level of Hall
        if(level == maxLevel) {
            return -1; //If max level it returns -1
        } else {
            int upgrade = (baseBuildingCost * (level + 1)); //Base building cost * level of upgraded hall
            return upgrade;
        }
    }

    /**
     * returns capacity by doing 2^level-1 then multiplying by baseCapacity
     * @return capacity
     */
    public int getCapacity() {
        int x = (int) Math.pow(2, level-1); //Math.pow(2, level-1) would do 2^level-1, initialises this value to x
        int capacity = baseCapacity * x; //Capacity would be base capacity multiplied by x as asked in instructions
        return capacity;
    }

//    public static void main(String[] args) {
//        Hall hall = new Hall("Johnny");
//        System.out.println(hall.getUpgradeCost());
//    }
}

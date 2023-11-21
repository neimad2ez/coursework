package facilities.buildings;

import facilities.Facility;

/**
 * type of building where students can get lectures
 */
public class Theatre extends Facility implements Building{
    int level = 1;
    int baseBuildingCost = 200;
    int maxLevel = 6;
    int baseCapacity = 10;

    /**
     * super calls constructor, methods and properties of parent class
     * @param name
     * name of the theatre
     *
     */
    public Theatre(String name) {
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
     * returns baseBuildingCost
     * @return baseBuildingCost
     */
    public int getBaseBuildingCost() {
        return baseBuildingCost;
    }

    /**
     * increases level of theatre
     */
    public void increaseLevel() { //Increase level of Theatre
        if(level != maxLevel) {
            level += 1;
        }
    }

    /**
     * returns upgradeCost
     * @return upgradeCost for next level
     */
    public int getUpgradeCost() { //Returns the amount of ECS coins needed to get to the next level of theatre
        if(level == maxLevel) {
            return -1; //If max level it returns -1
        } else {
            int upgrade = (baseBuildingCost * (level + 1)); //Base building cost * level of upgraded theatre
            return upgrade;
        }
    }

    /**
     * returns capacity by doing 2^level-1 then multiplying by baseCapacity
     * @return capacity of theatre
     */
    public int getCapacity() {
        int x = (int) Math.pow(2, level-1); //Math.pow(2, level-1) would do 2^level-1, initialises this value to x
        int capacity = baseCapacity * x; //Capacity would be base capacity multiplied by x as asked in instructions
        return capacity;
    }
}

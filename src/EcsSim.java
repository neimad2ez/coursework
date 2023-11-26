import facilities.Facility;
import facilities.buildings.Building;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import university.Staff;
import university.University;
import java.util.Iterator;
import java.util.Map;
import java.io.FileReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.nio.file.Paths;

/**
 * Simulates ECS and the University
 */
public class EcsSim {

  University uni;
  ArrayList<Staff> staffMarket = new ArrayList<>();
  String hallName;
  String labName;
  String theatreName;
  int years;
  Scanner scanner;
  int year;
  /**
   * @param funding
   * initial budget
   * @param years
   * amount of years you want to simulate
   */
  public EcsSim(String pathToFile, int funding, int years) throws Exception {
    scanner = new Scanner(Paths.get(pathToFile));
    uni = new University(funding);
    this.years = years;
    this.simulate(years);
    year = 1;
  }

  /**
   * instructs students in classes of 10 people
   * @param hash
   * hashmap (usually staffSalary)
   * @param students
   * number of students
   */
  public int instruct(HashMap<Staff, Float> hash, int students) {
    int rep = 0;
    for (Staff s : hash.keySet()) {
      if ((students - 10) > 0) {
        rep += s.instruct(10);
        students -= 10;
        System.out.println(s.getStaffName() + " is teaching 10 students in his/her class.");
      } else if (students < 10) {
        rep += s.instruct(students);
        System.out.println(s.getStaffName() + " is teaching " + students + " students in his/her class.");
        students = 0;
      }
    }
    if(students > 0) {
      uni.addReputation(-1*students);
      System.out.println("Amount of uninstructed students: " + students);
      System.out.println("Amount of reputation lost: " + -1*students);
    }
    return rep;
  }

  /**
   * checks if staff will leave the University, if yearsOfTeaching >= 30 then they leave, also a chance to leave as staff may be fatigued
   * @param iterator
   * iterator which iterates through hash HashMap
   */
  public void staffLeave(Iterator<Map.Entry<Staff, Float>> iterator) {
    boolean hasLeft = false;
    while(iterator.hasNext()) {
      Map.Entry<Staff, Float> entry = iterator.next();
      Staff staff = entry.getKey();
//      Float salary = entry.getValue();
      if(staff.getYearsOfTeaching() == 30) {
        System.out.println(staff.getStaffName() + " has been teaching for 30 years, they have retired.");
        iterator.remove();
      } else {
        float stamina = staff.getStamina();
        stamina = stamina / 100;
        Random rnd = new Random();
        double randomNum = rnd.nextDouble();
        if (randomNum > stamina) {
          System.out.println(staff.getStaffName() + " has quit because they are fed up!");
          iterator.remove();
          hasLeft = true;
        }
      }
    }
    System.out.println();
    if(!hasLeft) {
      System.out.println("Every staff member has decided to stay another year!");
    }
  }

  /**
   * hires staff checking if the hiring the staff, maintenance and salary may cause bankruptcy (totalCost)
   */
  public void staffHire() {
    double totalCost = uni.getTotalSalary() + uni.estate.getMaintenanceCost() + (staffMarket.get(0).getSkill() * 0.105);
    if(uni.getBudget() >= totalCost) {
      uni.addStaff(staffMarket.get(0));
      staffMarket.remove(0);
    }
  }

  /**
   * randomises upgrade between the 3 facilities e.g lab, theatre and hall
   */
  public void randomiseUpgrade() {
    try {
      Random rnd = new Random();
      int upgradeProbability = rnd.nextInt(3) + 1;
      if (upgradeProbability == 1) {
        if (!uni.checkMaxLevel(hallName)) {
          uni.upgradeFacility(1, hallName);
        } else if (!uni.checkMaxLevel(labName)) {
          uni.upgradeFacility(1, labName);
        } else if (!uni.checkMaxLevel(theatreName)) {
          uni.upgradeFacility(1, theatreName);
        }
      } else if (upgradeProbability == 2) {
        if (!uni.checkMaxLevel(labName)) {
          uni.upgradeFacility(1, labName);
        } else if (!uni.checkMaxLevel(theatreName)) {
          uni.upgradeFacility(1, theatreName);
        } else if (!uni.checkMaxLevel(hallName)) {
          uni.upgradeFacility(1, hallName);
        }
      } else if (upgradeProbability == 3) {
        if (!uni.checkMaxLevel(theatreName)) {
          uni.upgradeFacility(1, theatreName);
        } else if (!uni.checkMaxLevel(hallName)) {
          uni.upgradeFacility(1, hallName);
        } else if (!uni.checkMaxLevel(labName)) {
          uni.upgradeFacility(1, labName);
        }
      }
    } catch (Exception e) {
      System.out.println("Max Level, can't be upgraded anymore");
    }
  }

  /**
   * Replenish stamina +20 or to full of every staff member in staffSalary
   * @param hash
   * staffSalary array
   */
  public void replenishStamina(HashMap<Staff, Float> hash) {
    for(Staff s: hash.keySet()) {
      s.replenishStamina();
    }
  }

  /**
   * checks if all facilities are max level
   * @return allMax - checks if all facilities are max level
   */
  public boolean checkBuildingsMaxLevel() {
    boolean allMax = false;
    int maxCount = 0;
    for(Facility f: uni.estate.getFacilities()) {
      if (f instanceof Building) {
        if ((((Building) f).getLevel()) == ((Building) f).getMaxLevel()) {
          maxCount++;
        }
      }
      if (maxCount == uni.estate.getFacilities().length) {
        allMax = true;
      }
    }
    return allMax;
  }

  /**
   * increases years of teaching for teacher
   * @param hash
   * staffSalary array
   */
  public void increaseYearsOfTeaching(HashMap<Staff, Float> hash) {
    for (Staff s: hash.keySet()) {
      s.increaseYearsOfTeaching();
      System.out.println(s.getStaffName() + " has gained a year of teaching!");
    }
  }

  /**
   * simulates the University
   * @throws Exception
   * throws exception if anything is wrong
   */
  public void simulate() throws Exception {
    if (uni.getBudget() < 0) { // Checks if University has enough money to keep running for the year
      System.out.println("Not enough money, University shut down due to debt");
      System.exit(0); // Exits program if no money available
    }
    System.out.println();
    System.out.println("Beginning of the year!");
    System.out.println();
    if (uni.estate.getFacilities().length == 0) { // If no facilities in the arrayList it will build new facilities
      System.out.println("New University, 3 buildings have been built!");
      hallName = "Hall " + year;
      uni.build("Hall", hallName);
      labName = "Lab " + year;
      uni.build("Lab", labName);
      theatreName = "Theatre " + year;
      uni.build("Theatre", theatreName);
      System.out.println();
    }
    if (checkBuildingsMaxLevel()) { // If all buildings are max level it will create a new set of buildings
      System.out.println("All buildings are max level, 3 more new buildings have been built!");
      hallName = "Hall " + year;
      uni.build("Hall", hallName);
      labName = "Lab " + year;
      uni.build("Lab", labName);
      theatreName = "Theatre " + year;
      uni.build("Theatre", theatreName);
      System.out.println();
    }
    randomiseUpgrade(); //Randomises upgrade every year, chance that lab, hall, theatre are upgraded (only once)
    System.out.println();
    int students = uni.estate.getNumberOfStudents(); //Retrieves amount of students
    int budgetIncrease = students * 10; //Budget increase is students * 10 - gain this amount of ECS coins
    System.out.println("As there are " + students + " students,");
    System.out.println("University budget has increased by: " + budgetIncrease);
    uni.increaseBudget(budgetIncrease); //Increases the budget by budgetIncrease
    System.out.println("University budget is now: " + uni.getBudget());
    while (scanner.hasNextLine()) { //Checks the .txt file for adding staff to staffMarket
      String line = scanner.nextLine();
      if (line.isEmpty()) { //If .txt file has been run through and no more lines left it breaks
        break;
      }
      String[] array = line.split("\\("); //Creates an array of name and skill level e.g [Name, 90)]
      int lastBracket = array[1].indexOf(")"); //Retrieves position of where ")" first occurs
      array[1] = array[1].substring(0, lastBracket); //Substrings array[1] from first character to lastBracket to remove the last bracket e.g [Name, 90]
      staffMarket.add(new Staff(array[0], Integer.valueOf(array[1]))); //Adds this staff to the staffMarket
    }
    int numOfStudents = students; //Initialises a temporary variable for amount of students
    if (uni.arrayStaffSalary().size() < 4) { // 4 Staff maximum per year, if not, it will hire one more staff member
      staffHire(); //Hires a new staff member
    }
    System.out.println();
    System.out.println("During the year...");
    System.out.println();
    int count = numOfStudents; //Another temporary variable for number of students
    HashMap<Staff, Float> hash = uni.arrayStaffSalary(); //Initialises HashMap in EcsSim
    int rep = instruct(hash, count); // method instruct returns amount of rep from instructing students
    uni.addReputation(rep); // adds reputation gained from instructing students
    System.out.println("University reputation has increased by: " + rep);
    System.out.println("University reputation is now: " + uni.getReputation());
    System.out.println();
    System.out.println("End of the year!");
    System.out.println();
    try {
      System.out.println("Estate maintenance cost: " + uni.estate.getMaintenanceCost());
      uni.increaseBudget(-uni.estate.getMaintenanceCost()); //Decreases budget by cost of maintenace
      System.out.println("New budget: " + uni.getBudget());
      System.out.println();
      float totalSalary = uni.getTotalSalary(); // Retrieves total salary of staff members
      System.out.println("Total staff salary is: " + totalSalary);
      uni.increaseBudget(-totalSalary); // Decreases budget by cost of staff salary.
      System.out.println("New budget is now: " + uni.getBudget());
      System.out.println();
    } catch (Exception e) {
      System.out.println("Not enough money, University can't function");
      System.exit(0);
    }
    increaseYearsOfTeaching(hash);
    System.out.println();
    Iterator<Map.Entry<Staff, Float>> iterator = hash.entrySet().iterator();
    staffLeave(iterator);
    replenishStamina(hash);
  }

  public void simulate(int years) throws Exception {
    System.out.println(uni.getBudget());
    System.out.println(years);
    for(int i = 1; i <= years; i++) {
      System.out.println("Year: " + i);
      year = i;
      this.simulate();
    }
  }

  public static void main(String[] args) throws Exception {
    EcsSim ecs = new EcsSim(args[0], Integer.valueOf(args[1]), Integer.valueOf(args[2]));
  }
}

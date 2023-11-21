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
      } else if (students < 10) {
        rep += s.instruct(students);
      }
    }
    return rep;
  }

  /**
   * Adds 3 more people to staffMarket each year, randomised name between (letter) and (number) e.g B1, C1 or D3
   */
  public void addStaffMarket() {
    for (int i = 0; i < 3; i++) {
      for (Staff s: staffMarket) {
        Random rnd = new Random();
        String[] letters = {"A","B","C","D","E"};
        String[] numbers = {"1","2","3","4","5"};
        int randomLetter = rnd.nextInt(letters.length);
        int randomNumber = rnd.nextInt(numbers.length);
        String newStaff = letters[randomLetter] + numbers[randomNumber];
        int low = 0;
        int high = 100;
        int result = rnd.nextInt(high-low) + low;
        if(s.getStaffName() != newStaff) {
          staffMarket.add(new Staff(newStaff, result));
          System.out.println("New staff member " + newStaff + " has joined the Staff Market");
          break;
        }
      }
    }
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
   * hires staff according to how many students there are
   * @param numOfStudents
   * number of students
   */
  public void staffHire(int numOfStudents) {
    if (numOfStudents > 0) {
      if (uni.getNumOfStaff() == 0) {
        uni.addStaff(staffMarket.get(0));
        staffMarket.remove(0);
        numOfStudents -= 10;
      }
      while (numOfStudents >= 10) {
        uni.addStaff(staffMarket.get(0));
        staffMarket.remove(0);
        numOfStudents -= 10;
      }
      if(numOfStudents > 0 && numOfStudents < 10) {
        uni.addStaff(staffMarket.get(0));
        staffMarket.remove(0);
      }
    } else {
      System.out.println("No students");
    }
  }

  /**
   * randomises upgrade between the 3 facilities e.g lab, theatre and hall
   */
  public void randomiseUpgrade() {
    try {
      Random rnd = new Random();
      int min = 0;
      int max = 100;
      int probability = rnd.nextInt(max-min) + min;
      int upgradeProbability = rnd.nextInt(3) + 1;
      if (upgradeProbability == 1) {
        if (probability >= 0) {
          if (!uni.checkMaxLevel(hallName)) {
            uni.upgradeFacility(1, hallName);
          } else if (!uni.checkMaxLevel(labName)) {
            uni.upgradeFacility(1, labName);
          } else if (!uni.checkMaxLevel(theatreName)) {
            uni.upgradeFacility(1, theatreName);
          }
          if(probability >= 50) {
            if (!uni.checkMaxLevel(labName)) {
              uni.upgradeFacility(1, labName);
            } else if (!uni.checkMaxLevel(theatreName)) {
              uni.upgradeFacility(1, theatreName);
            } else if (!uni.checkMaxLevel(hallName)) {
              uni.upgradeFacility(1, hallName);
            }
            if (probability >= 80) {
              if (!uni.checkMaxLevel(theatreName)) {
                uni.upgradeFacility(1, theatreName);
              } else if (!uni.checkMaxLevel(hallName)) {
                uni.upgradeFacility(1, hallName);
              } else if (!uni.checkMaxLevel(labName)) {
                uni.upgradeFacility(1, labName);
              }
            }
          }
        } else {
          System.out.println("No upgrades were made this year");
        }
      } else if (upgradeProbability == 2) {
        if (probability >= 0) {
          if (!uni.checkMaxLevel(labName)) {
            uni.upgradeFacility(1, labName);
          } else if (!uni.checkMaxLevel(theatreName)) {
            uni.upgradeFacility(1, theatreName);
          } else if (!uni.checkMaxLevel(hallName)) {
            uni.upgradeFacility(1, hallName);
          }
          if(probability >= 50) {
            if (!uni.checkMaxLevel(theatreName)) {
              uni.upgradeFacility(1, theatreName);
            } else if (!uni.checkMaxLevel(hallName)) {
              uni.upgradeFacility(1, hallName);
            } else if (!uni.checkMaxLevel(labName)) {
              uni.upgradeFacility(1, labName);
            }
            if (probability >= 80) {
              if (!uni.checkMaxLevel(hallName)) {
                uni.upgradeFacility(1, hallName);
              } else if (!uni.checkMaxLevel(labName)) {
                uni.upgradeFacility(1, labName);
              } else if (!uni.checkMaxLevel(theatreName)) {
                uni.upgradeFacility(1, theatreName);
              }
            }
          }
        } else {
          System.out.println("No upgrades were made this year");
        }
      } else if (upgradeProbability == 3) {
        if (probability >= 0) {
          if (!uni.checkMaxLevel(theatreName)) {
            uni.upgradeFacility(1, theatreName);
          } else if (!uni.checkMaxLevel(hallName)) {
            uni.upgradeFacility(1, hallName);
          } else if (!uni.checkMaxLevel(labName)) {
            uni.upgradeFacility(1, labName);
          }
          if(probability >= 50) {
            if (!uni.checkMaxLevel(hallName)) {
              uni.upgradeFacility(1, hallName);
            } else if (!uni.checkMaxLevel(labName)) {
              uni.upgradeFacility(1, labName);
            } else if (!uni.checkMaxLevel(theatreName)) {
              uni.upgradeFacility(1, theatreName);
            }
            if (probability >= 80) {
              if (!uni.checkMaxLevel(labName)) {
                uni.upgradeFacility(1, labName);
              } else if (!uni.checkMaxLevel(theatreName)) {
                uni.upgradeFacility(1, theatreName);
              } else if (!uni.checkMaxLevel(hallName)) {
                uni.upgradeFacility(1, hallName);
              }
            }
          }
        } else {
          System.out.println("No upgrades were made this year");
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

  public boolean checkMaxLevel() {
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
   * @throws Exception
   */
  public void simulate() throws Exception {
    if (uni.getBudget() < 0) {
      System.out.println("Not enough money, University shut down due to debt");
      System.exit(0);
    }
    System.out.println();
    System.out.println("Beginning of the year!");
    System.out.println();
    if (uni.estate.getFacilities().length == 0) {
      System.out.println("New University, 3 buildings have been built!");
      hallName = "Hall " + year;
      uni.build("Hall", hallName);
      labName = "Lab " + year;
      uni.build("Lab", labName);
      theatreName = "Theatre " + year;
      uni.build("Theatre", theatreName);
      System.out.println();
    }
    if (checkMaxLevel()) {
      System.out.println("All buildings are max level, 3 more new buildings have been built!");
      hallName = "Hall " + year;
      uni.build("Hall", hallName);
      labName = "Lab " + year;
      uni.build("Lab", labName);
      theatreName = "Theatre " + year;
      uni.build("Theatre", theatreName);
      System.out.println();
    }
    randomiseUpgrade();
    System.out.println();
    int students = uni.estate.getNumberOfStudents();
    int budgetIncrease = students * 10;
    System.out.println("As there are " + students + " students,");
    System.out.println("University budget has increased by: " + budgetIncrease);
    uni.increaseBudget(budgetIncrease);
    System.out.println("University budget is now: " + uni.getBudget());
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.isEmpty()) {
        break;
      }
      String[] array = line.split("\\(");
      int lastBracket = array[1].indexOf(")");
      array[1] = array[1].substring(0, lastBracket);
      staffMarket.add(new Staff(array[0], Integer.valueOf(array[1])));
    }
    addStaffMarket();
//    for (Staff s: staffMarket) {
//      System.out.println(s.getStaffName());
//      System.out.println(s.getSkill());
//    }
    int numOfStudents = 23;
    staffHire(numOfStudents);
    System.out.println();
    System.out.println("During the year...");
    System.out.println();
    int count = numOfStudents; //students
    HashMap<Staff, Float> hash = uni.arrayStaffSalary();
    int rep = instruct(hash, count);
    uni.addReputation(rep);
    System.out.println("University reputation has increased by: " + rep);
    System.out.println("University reputation is now: " + uni.getReputation());
    System.out.println();
    System.out.println("End of the year!");
    System.out.println();
    try {
      System.out.println("Estate maintenance cost: " + uni.estate.getMaintenanceCost());
      uni.increaseBudget(-uni.estate.getMaintenanceCost());
      System.out.println("New budget: " + uni.getBudget());
      System.out.println();
      float totalSalary = uni.getTotalSalary();
      System.out.println("Total staff salary is: " + totalSalary);
      uni.increaseBudget(-totalSalary);
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

package university;

import facilities.Facility;
import facilities.buildings.Building;
import facilities.buildings.Hall;
import facilities.buildings.Lab;
import facilities.buildings.Theatre;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Manages the facilities within the university
 */
public class Estate {


  ArrayList<Facility> facilities; //Initialises ArrayList called facilities

  /**
   * Creates constructor Estate and defines facilities ArrayList
   */
  public Estate() {
    this.facilities = new ArrayList<Facility>();
  }

  /**
   * returns array facility
   * @return array facilities
   */
  public Facility[] getFacilities() {
    Facility[] facility1 = new Facility[facilities.size()];
    return facilities.toArray(facility1);
  }



  /**
   * checks what type facility is and adds to facilities ArrayList
   * @param type
   * type of facility e.g "Hall", "Lab", "Theatre".
   * @param name
   * name of the facility
   * @return new facility made
   */
  public Facility addFacility(String type, String name) {
    if(type == "Hall") { //If type is hall it adds the facility created to facilities ArrayList
      Hall hall1 = new Hall(name);
      facilities.add(hall1);
      return hall1;
    } else if(type == "Lab") { //If type is lab it adds the facility created to facilities ArrayList
      Lab lab1 = new Lab(name);
      facilities.add(lab1);
      return lab1;
    } else if(type == "Theatre") { //If type is theatre it adds the facility created to facilities ArrayList
      Theatre theatre1 = new Theatre(name);
      facilities.add(theatre1);
      return theatre1;
    } else {
      return null; //If the type is incorrect it will return null
    }
  }

  /**
   * returns maintenanceCost
   * @return maintenanceCost (10% of buildings capacity)
   */
  public float getMaintenanceCost() {
    int hallCapacity = 0;
    int labCapacity = 0;
    int theatreCapacity = 0; //Initialise hall/lab/theatre capacities
    for(Facility f: facilities) { //Loops through facilities ArrayList looping through each Facility in the ArrayList
      if(f instanceof Hall) { //If the Facility f is part of class Hall then run this line of code
        hallCapacity = ((Hall) f).getCapacity(); //Gets the capacity of Facility f
      } else if(f instanceof Lab) { //If the Facility f is part of class Hall then run this line of code
        labCapacity = ((Lab) f).getCapacity(); //Gets the capacity of Facility f
      } else if(f instanceof Theatre) { //If the Facility f is part of class Hall then run this line of code
        theatreCapacity = ((Theatre) f).getCapacity(); //Gets the capacity of Facility f
      }
    }
    float maintenanceCost = theatreCapacity + labCapacity + hallCapacity; //Add all capacities together
    maintenanceCost = (float) (maintenanceCost * 0.1); //Maintenance cost is all capacities added x 0.1
    return maintenanceCost;
  }

  /**
   * prints all facilities in arrayList facilities
   */
  public void printFacilities() {
    for (Facility f: facilities) {
      System.out.println(f.getName());
    }
  }

  /**
   * returns number of students
   * @return number of students
   */
  public int getNumberOfStudents() {
    int hallCapacity = 0;
    int labCapacity = 0;
    int theatreCapacity = 0; //Initialise capacities
    for(Facility f: facilities) { //Loops through facilities ArrayList looping through each Facility in the ArrayList
      if(f instanceof Hall) { //If the Facility f is part of class Hall then run this line of code
        hallCapacity += ((Hall) f).getCapacity();
      } else if(f instanceof Lab) { //If the Facility f is part of class Hall then run this line of code
        labCapacity += ((Lab) f).getCapacity();
      } else if(f instanceof Theatre) { //If the Facility f is part of class Hall then run this line of code
        theatreCapacity += ((Theatre) f).getCapacity();
      }
    }
    int numOfStudents = Math.min(hallCapacity, Math.min(labCapacity, theatreCapacity)); //Minimal number
    // of students is the smallest capacity, math.min picks the smallest variable.
    return numOfStudents;
  }
//  public static void main(String[] args) {
//    Estate estate1 = new Estate();
//    Facility h = estate1.addFacility("Hall", "hi");
//    System.out.println(estate1.getMaintenanceCost());
//    if(h instanceof Hall) {
//      ((Hall) h).increaseLevel();
//      System.out.println(((Hall) h).getCapacity());
//    }
//    System.out.println(estate1.getMaintenanceCost());
//  }

}

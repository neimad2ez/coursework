package university;

import java.lang.Math;

/**
 * Staff which work in the University
 */
public class Staff {
  String name;
  int skill;
  int yearsOfTeaching;
  int stamina;

  /**
   * defines constructor staff allowing user to input their name and skill as well as initialising yearsOfTeaching to 0 and stamina to 100.
   * @param name
   * initialises name from input
   * @param skill
   * initialises skill from input
   */
  public Staff(String name, int skill) {
    this.name = name;
    this.skill = skill;
    yearsOfTeaching = 0;
    stamina = 100;
  }

  /**
   * returns budget
   * @return staff name
   */
  public String getStaffName() {
    return name;
  }

  /**
   * returns yearsOfTeaching
   * @return yearsOfTeaching
   */
  public int getYearsOfTeaching() {
    return yearsOfTeaching;
  }

  /**
   * returns skill
   * @return staff skill
   */
  public int getSkill() {
    return skill;
  }

  /**
   * returns stamina
   * @return stamina
   */
  public int getStamina() {
    return stamina;
  }

  /**
   * instructs a number of students, increases skill by 1 and changes stamina
   * @param numberOfStudents
   * number of students being taught
   * @return reputation
   */
  public int instruct(int numberOfStudents) {
    int reputation = (100 * skill) / (100 + numberOfStudents);
    if(skill < 100) {
      skill += 1;
    }
    stamina = (int) (stamina - Math.ceil( ((double) numberOfStudents / (20+skill))) * 20);
    return reputation;
  }

  /**
   * replenishes stamina by 20, if already > 80 then sets to 100
   */
  public void replenishStamina() {
    if (stamina <= 80) {
      stamina += 20;
    } else {
      stamina = 100;
    }
  }

  /**
   * increases yearsOfTeaching by 1
   */
  public void increaseYearsOfTeaching() {
    yearsOfTeaching += 1;
  }

//  public static void main(String[] args) {
//    Staff staff1 = new Staff("John", 20);
//    staff1.increaseYearsOfTeaching();
//    staff1.instruct(50);
//    staff1.replenishStamina();
//  }
}

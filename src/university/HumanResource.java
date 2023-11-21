package university;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Iterator;

/**
 * Human Resource manages staff within the University
 */
public class HumanResource {
  HashMap<Staff, Float> staffSalary;

  /**
   * initialises staffSalary
   */
  public HumanResource() {
    staffSalary = new HashMap<Staff, Float>();
  }

  /**
   * returns staffSalary size
   * @return staffSalary size
   */
  public int getNumOfStaff() {
    return staffSalary.size();
  }

  /**
   * returns staffSalary
   * @return staffSalary
   */
  public HashMap<Staff, Float> arrayStaffSalary() {
    return staffSalary;
  }

  /**
   * adds new staff member and gives them random salary using a random percentage between 9.5 and 10.5 * their skill - adds them to staffSalary
   * @param staff
   * object from Staff class
   */
  public void addStaff(Staff staff) {
    Random random = new Random();
    float max = 10.5F;
    float min = 9.5F;
    float num = random.nextFloat(max - min)+min;
    num = num / 100;
    float salary = num * staff.getSkill();
    staffSalary.put(staff, salary);
//    for(Staff k: staffSalary.keySet()) {
//      System.out.println(k.getStaffName());
//    }
  }

  /**
   * returns the iterator for keySet of staff salary
   * @return iterator
   */
  public Iterator<Staff> getStaff() {
    Iterator<Staff> it = staffSalary.keySet().iterator();
    return it;
  }

  /**
   * returns total salary of all staff members
   * @return total salary of all staff members in staffSalary
   */
  public float getTotalSalary() {
    float total = 0;
    for (float i:staffSalary.values()) {
      total += i;
    }
    return total;
  }

//  public static void main(String[] args) {
//    Staff staff1 = new Staff("John", 50);
//    Staff staff2 = new Staff("Kelly", 50);
//    Staff staff3 = new Staff("Ko", 50);
//    HumanResource hr = new HumanResource();
//    hr.addStaff(staff1);
//    hr.addStaff(staff2);
//    hr.addStaff(staff3);
//    hr.getStaff();
//    System.out.println(hr.getTotalSalary());
//  }
}

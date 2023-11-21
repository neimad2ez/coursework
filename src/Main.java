import java.util.Random;

public class Main {
  public static void main(String[] args) {

    float max = 10.5F;
    float min = 9.5F;
    Random random = new Random();

    for(int i = 0;i <20;i++) {
      float num = random.nextFloat(max - min)+min;
      System.out.println(num);
    }
  }
}

package lab3java;

import java.util.Random;

import java.util.Comparator;
import java.util.Arrays;

public class Matrix {
  private Integer[][] mass;

  public Matrix() {
    this(5, 5);
  }

  public Matrix(int a, int b) {
    if(a <= 0) a = 5;
    if(b <= 0) b = 5;

    mass = new Integer[a][b + 1];
    Random rand = new Random();

    for(int i = 0; i < a; i++) {
        for (int j = 0; j < b; j++) {
            mass[i][j] = rand.nextInt(1000);
            int sign = (int) (Math.random() * 4 - 2);
            if (sign != 0) mass[i][j] *= sign;
        }
        mass[i][b] = 0;
    }
  }

  /////
  public void calcCharacteristics() {
    for (int i = 0; i < mass.length; i++) {
      for (int j = 0; j < mass[0].length - 1; j++) {
        if(mass[i][j] % 2 == 0 && mass[i][j] > 0) {
            mass[i][mass[i].length - 1] += mass[i][j];
        }
      }
    }
  }

  public void printCharacteristics() {
    System.out.println("Sorted sums of lines are:");
    for(int i = 0; i < mass.length; i++){
      System.out.println(mass[i][mass[0].length - 1]);
    }
  }

  //////
  public int find(int num, int line) {
    line = line > mass.length ? 0 : line - 1;
    Arrays.sort(mass[line], 0, mass[line].length - 1);

    Integer massToFind[] = new Integer[mass[line].length - 1];

    for(int i = 0; i < mass[line].length - 1; i++) {
      massToFind[i] = mass[line][i];
      System.out.printf("%d ", mass[line][i]);
    }
    System.out.println();

    return Arrays.binarySearch(massToFind, num);
  }

  public void sort() {
    Comparator<Integer[]> comp = (a1, a2) -> a1[a1.length - 1] - a2[a2.length - 1];

    Arrays.sort(mass, comp);
  }

  public void sortString(int str, int a, int b) {
    str = str == 0 ? 1 : str;
    str = str > 0 ? str : -str;
    str = str > mass.length ? mass.length - 1 : str;

    if(a > b) {
      if(a > mass[0].length) a = 0;
      if(b > mass[0].length) b = mass[0].length;
      int tmp = a;
      a = b;
      b = tmp;
    }

    Arrays.sort(mass[str - 1], a - 1, b);
  }

  public void print() {
    for(int i = 0; i < mass.length; i++) {
      for(int j = 0; j < mass[0].length - 1; j++) {
        System.out.printf("%5d", mass[i][j]);
      }
      System.out.println();
    }
  }
}

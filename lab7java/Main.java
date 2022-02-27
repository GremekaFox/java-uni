package lab7java;

import java.util.Scanner;
import java.io.File;

public class Main {
  public static void main(String[] args) {
    double[][] roads = getRoads("city.txt");
    String[] cities = getCities("city.txt");

    System.out.println();
    for(String city : cities) System.out.printf("%s ", city);
    System.out.println();

    Scanner scan = new Scanner(System.in);
    System.out.println("Choose the city to start (enter the number equal to the name of it):"); 
    int a = scan.nextInt();
    System.out.println("Choose the city to end your trip:"); 
    int b = scan.nextInt();
    System.out.printf("%s %s\n", cities[a-1], cities[b-1]);

    double res = calcDimention(roads, a-1, b-1);

    System.out.println(res);
  }

  private static String[] getCities(String fileName) {
    String[] mas = null;

    try(Scanner scan = new Scanner(new File(fileName))) {
      int tmp = 0;
      mas = new String[Integer.parseInt(scan.nextLine())];
      for(int i = 0; i < mas.length; i++) mas[i] = "";

      while(scan.hasNextLine()) {
        String[] words = scan.nextLine().split(" ");
        boolean flag = true;

        for(int i = 0; i < mas.length; i++) {
          if(mas[i].compareTo(words[0]) == 0){
            flag = false;
            break;
          }
        }

        if(flag) mas[tmp++] = words[0];
        flag = true;

        for(int i = 0; i < mas.length; i++) {
          if(mas[i].compareTo(words[1]) == 0) {
            flag = false;
            break;
          }
        }

        if(flag) mas[tmp++] = words[1];
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    return mas;
  }

  private static double calcDimention(double[][] roads, int startCity, int endCity) {
    boolean[] isResult = new boolean[roads.length];
    double[] res = new double[roads.length];
    for(int i = 0; i < res.length; i++) {
      res[i] = 10000;
      isResult[i] = false;
    }

    res[startCity] = 0; // From
    int curCityNum;

    for(int i = 0; i < roads.length; i++) {
      int min = endCity;
      for(int j = 0; j < res.length; j++) {
        if(isResult[j] == false && res[min] >= res[j]) {
          min = j;
        }
      }
      curCityNum = min;
      isResult[curCityNum] = true;
      if(isResult[endCity] == true) break;

      for(int j = 0; j < res.length; j++) {
        double curRoadLength = roads[curCityNum][j];
        if(curRoadLength > 0 && curRoadLength + res[curCityNum] < res[j]) {
          res[j] = curRoadLength + res[curCityNum];
        }
      }
    }

    return res[endCity];
  }

  private static double[][] getRoads(String fileName) {
    File input = new File(fileName);
    String[] mas = getCities(fileName);
    double[][] roads = new double[mas.length][mas.length];
    for(int i = 0; i < roads.length; i++) {
      for(int j = 0; j < roads.length; j++) {
        roads[i][j] = i == j ? 0 : -1;
      }
    }

    try(Scanner scan = new Scanner(input)) {
      Integer.parseInt(scan.nextLine());
      while(scan.hasNextLine()) {
        String[] words = scan.nextLine().split(" ");
        int a = 0;
        int b = 0;

        for(int i = 0; i < mas.length; i++) {
          if(mas[i].equals(words[0])) a = i;
          if(mas[i].equals(words[1])) b = i;
        }

        roads[a][b] = roads[b][a] = Double.parseDouble(words[2]);
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return roads;
  }
}
package lab8;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("What type of file do you prefer to read data from? (csv, xml or bin)");
        Scanner scan = new Scanner(System.in);
        String type = scan.next();
        Garages listGarages = new Garages(type);
        listGarages.printInfo();

        System.out.println("Which garage address do you want to correct? (Write a number from " + 1 + " to " +
        listGarages.getBd().size() + " or 0 if you don't want)");
        int num = scan.nextInt();
        if(num <= listGarages.getBd().size() && num > 0) {
            try {
                RandomAccessFile rnd = new RandomAccessFile("lab8/data/csv/garages.csv", "rw");
                Scanner scan1 = new Scanner(new File("lab8/data/csv/garages.csv"));
                int a = 0;
                for(int i = 0; i < num - 1; i++) {
                    a += scan1.nextLine().length() + 1;
                }
                a += String.valueOf(num).length() + 1;
                rnd.seek(a);
                scan1.close();
                System.out.println("Previous address: " + listGarages.getAddress(num).split(" ")[0]);
                System.out.println("Write correct address \n(Number of symbols must not exceed number of symbols in previous address");
                String str = scan.next();
                rnd.writeBytes(str);
                rnd.close();
                scan1.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            listGarages = new Garages("csv");
        }
        scan.close();

        ArrayList<Integer> numbers = listGarages.getNumbers();
        numbers.forEach(System.out::println);
        System.out.println(listGarages.getAddress(num));
        ArrayList<String> marks = listGarages.getMarks(3);
        ArrayList<String> masters = listGarages.getMasters(4);
        marks.forEach(System.out::println);
        masters.forEach(System.out::println);
        listGarages.toXml();
        listGarages.toBin();
    }
}

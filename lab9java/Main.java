package lab9;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Какой файл вы хотите прочитать? ( введите: csv, xml или bin)");
        Scanner scan = new Scanner(System.in);
        String type = scan.next();
        Garages listGarages = new Garages(type);
        listGarages.printInfo();

        System.out.println("Адресс какого гаража вы хотите исправить? (Напишите номер от " + 1 + " to " + 
        listGarages.getBd().size() + " или 0 , если не хотите менять)");
        int num = scan.nextInt();
        if(num <= listGarages.getBd().size() && num > 0) {
            try {
                RandomAccessFile rnd = new RandomAccessFile("lab9/data/csv/garages.csv", "rw");
                Scanner scan1 = new Scanner(new File("lab9/data/csv/garages.csv"));
                int a = 0;
                for(int i = 0; i < num - 1; i++) {
                    a += scan1.nextLine().length() + 1;
                }
                a += String.valueOf(num).length() + 1;
                rnd.seek(a);
                scan1.close();
                System.out.println("Предыдущий адрес " + listGarages.getAddress(num).split(" ")[0]);
                System.out.println("Напишите правильный адрес \n(Помните, что адрес заменяется посимвольно с начала и новый адрес не может быть длинней предыдущего)");
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

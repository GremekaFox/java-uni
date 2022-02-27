package lab3java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    
        System.out.println("Enter the size of matrix");
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b = scan.nextInt();
        Matrix mass = new Matrix(a, b);

        mass.calcCharacteristics();

        mass.print();

        mass.sort();
        mass.printCharacteristics();
        System.out.println();

        System.out.println();

        mass.print();
        System.out.println();

        System.out.println("Enter the starting value of sorting (1 <= a <= "+ b + " )");
        int st = scan.nextInt();
        System.out.println("Enter the ending value of sorting (1 <= b <= "+ b + " ) and bigger than " + st);
        int en = scan.nextInt();

        mass.sortString(1, st, en);

        System.out.println();
        mass.print();

        System.out.println("Enter a line, where you want to find a value, and then type a value");

        int line = scan.nextInt();
        int toFind = scan.nextInt();

        int num = mass.find(toFind, line);
        if(num >= 0) System.out.println("This elem is in " + (num + 1) + " position");
        else System.out.println("Can't find");

        scan.close();
    }
}

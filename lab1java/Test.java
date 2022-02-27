package lab1java;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        double x;
        int k;
        try {
            x = get();
            if(x > 1 || x < -1) throw new Exception("Wrong value in number x (x (-1, 1))");

            k = get2();
            if(k <= 0) throw new Exception("Wrong value in number k (k should be > 0)");
        }
        catch(Exception e) {
            if(e.getMessage() != null) {
                System.out.println(e.getMessage());
            }
            else {
                System.out.println("Error: the entered string is not a number");
            }
            return;
        }

        System.out.println("My arcsin(x)   = " + myAsin(x , k));

        double c = Math.asin(x);
        System.out.println("Comp arcsin(x) = " + c);
    }

    static double get() {
        System.out.println("Enter your X value");
        double t = 0;
        Scanner scan = new Scanner(System.in);
        t = scan.nextDouble();
        return t;
    }

    static int get2() {
        System.out.println("Enter k value for Eps");
        int t = -1;
        Scanner scan = new Scanner(System.in);
        t = scan.nextInt();
        return t;
    }

    static double myAsin(double x, int k) {
        double next = x;
        double res = x;
        int num = 1;
        final double eps = Math.pow(10, -k);
        while(Math.abs(next) >= eps) {
            next *= Math.pow(x * num, 2);
            next /= (num + 1) * (num + 2);
            num += 2;
            res += next;
        }
        return res;
    } 


}


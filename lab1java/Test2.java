package lab1java;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;
import java.math.RoundingMode;

public class Test2 {
    public static void main(String[] args) {
        BigDecimal x;
        BigInteger k;
        try {
            x = get();
            if(x.compareTo(BigDecimal.valueOf(1)) == 1 || x.compareTo(BigDecimal.valueOf(-1)) == -1) throw new Exception("Wrong value in number x (x (-1, 1))");

            k = get2();
            if(k.compareTo(BigInteger.valueOf(0)) == -1) throw new Exception("Wrong value in number k (k should be > 0)");
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

        boolean minus = x.compareTo(BigDecimal.valueOf(0)) > 0 ? false : true;
        if(minus) x = x.multiply(BigDecimal.valueOf(-1));

        System.out.println("My arcsin(x)   = " + (minus ? "-" : "") + myAsin(x , k));

        double c = Math.asin(x.doubleValue());
        System.out.println("Comp arcsin(x) = " + (minus ? "-" : "") + c);
    }

    static BigDecimal get() {
        System.out.println("Enter your X value");
        double t = 0;
        Scanner scan = new Scanner(System.in);
        t = scan.nextDouble();
        BigDecimal x = BigDecimal.valueOf(t);
        return x;
    }

    static BigInteger get2() {
        System.out.println("Enter k value for Eps");
        int t = -1;
        Scanner scan = new Scanner(System.in);
        t = (int)scan.nextDouble();
        BigInteger k = BigInteger.valueOf(t);
        return k;
    }

    static BigDecimal myAsin(BigDecimal x, BigInteger k) {
        BigDecimal next = x;
        BigDecimal res = x;
        BigDecimal num = BigDecimal.valueOf(1);
        final BigDecimal eps = BigDecimal.valueOf(Math.pow(10, -k.intValue()));
        while(next.compareTo(eps) > -1) {
            next = next.multiply(x);
            next = next.multiply(x);
            next = next.multiply(num);
            next = next.multiply(num);
            num = num.add(BigDecimal.valueOf(1));
            next = next.divide(num, 16, RoundingMode.HALF_UP);
            num = num.add(BigDecimal.valueOf(1));
            next = next.divide(num, 16, RoundingMode.HALF_UP);
            res = res.add(next);
        }
        return res;
    } 
}


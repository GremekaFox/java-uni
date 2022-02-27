    package lab2java;
    
    import java.math.BigDecimal;
    import java.math.BigInteger;
    import java.util.Formatter;
    import java.util.Scanner;
    
    public class FormatterClass {
        public static void Form() {

        System.out.println("Enter your BigInteger value");
        Scanner scan = new Scanner(System.in);
        BigInteger num = scan.nextBigInteger();

        Formatter forma = new Formatter();

        System.out.println(forma.format("Value in 8th notationis\n%o", num));
        forma = new Formatter();
        System.out.println(forma.format("If characters num <7 then outputs zeros _minimum width_ \n%07o", num));
        forma = new Formatter();
        System.out.println(forma.format("Outputs 3-to-5 characters per line _precision specifier_ \n%3.5s", num));
        System.out.println("Examples of using 5 Java-flags");
        System.out.printf("Left Align:\n%-10d%n", num);
        System.out.printf("Positive numbers are displayed with __:\n% 10d%n", num);
        System.out.printf("Positive numbers are displayed with +\n%+10d%n", num);
        System.out.printf("Values ​​include group separators\n%,10d%n", num);
        System.out.printf("Negative values ​​are displayed in ()\n%(10d%n", num);
        forma = new Formatter();
        System.out.println(forma.format("Value in 16th notationis\n%x", num));
        System.out.println("Enter a number with floating point:");
        BigDecimal bigDecimal = scan.nextBigDecimal();
        forma = new Formatter();
        System.out.println(forma.format("Another value in 16th notationis" +
        "\n%f\n", bigDecimal));
        //forma = new Formatter();
        System.out.println("Usual mas: 123, 45.45, 98.43, 1.2 ");
        System.out.println(forma.format("Using the number of argument" +
        " %4$s %2$s %3$s %1$s", "123", "45.45", "98.43", "1.2"));
        }
    }
package lab6java;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Locale;


public class Main {
    public static void main(String[] args){
    
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your date: д-м-год эра час:мин:сек:мс");
        String s = scan.nextLine();
        
        SimpleDateFormat date = new SimpleDateFormat("dd-M-yyyy G", Locale.ENGLISH);
        date.setLenient(false);  

        Calendar cal = Calendar.getInstance();
        try {
        
           // SimpleDateFormat date2 = new SimpleDateFormat("m ; S");
            Date x = date.parse(s);
            System.out.println(x);

           /* System.out.println("\nМинуты без лидирующих нулей и миллисекунды \n" + date2.format(x));
             
            SimpleDateFormat date3 = new SimpleDateFormat("G ; ww ; EEEE");
            System.out.println("\nЭра, неделя в году и день недели полностью \n" + date3.format(x));
           

            
            Formatter f1 = new Formatter();
            System.out.println("\nFirst 2 numbers of year-number\n" + f1.format("%tC", x));
            Formatter f2 = new Formatter();
            System.out.println("\nMonth, day, year\n" + f2.format("%tD", x));
            Formatter f3 = new Formatter();
            System.out.println("\nNanoseconds\n" + f3.format("%tN", x));
            Formatter f4 = new Formatter();
            System.out.println("\nAM/PM\n" + f4.format("%tp", x));

            String fD = date.format(x);
            cal.setTime(date.parse(fD));*/

            System.out.printf("\nNOW: Millisecinds %1$s \nDay in a year %2$s \nDay in a month %3$s\n\n", cal.get(Calendar.MILLISECOND), cal.get(Calendar.DAY_OF_YEAR ), cal.get(Calendar.DAY_OF_MONTH) + 1);
            cal.add(Calendar.DAY_OF_MONTH, 13);
            System.out.printf("13 days in a month later: \nMillisecinds %1$s \nDay in a year %2$s \nDay in a month %3$s\n\n", cal.get(Calendar.MILLISECOND), cal.get(Calendar.DAY_OF_YEAR), cal.get(Calendar.DAY_OF_MONTH) + 1);
            cal.roll(Calendar.MILLISECOND, -100);
            System.out.printf("100 Milliseconds before: \nMillisecinds %1$s \nDay in a year %2$s \nDay in a month %3$s\n\n", cal.get(Calendar.MILLISECOND), cal.get(Calendar.DAY_OF_YEAR), cal.get(Calendar.DAY_OF_MONTH) + 1);
        
       }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}

 
 //29-10-2020 AD 11:21:20:123
 //04-12-2012 AD 10:20:45:498
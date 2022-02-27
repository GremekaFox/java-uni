
import java.util.Scanner;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

public class Main{

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        System.out.println("Enter your first line with leksems");
        String str1 = scan.nextLine();
        System.out.println("Enter your second line of leksems");
        String str2 = scan.nextLine();

        String[] strmas = leks(str1, str2);
        System.out.println("Line without leksems");
        for(String str : strmas) System.out.printf("%s", str +  ' ');
        System.out.println();

        int[] mass = parsing(strmas);
        for(int num : mass) System.out.println(num);
        System.out.println();


        StringBuffer st = new StringBuffer(str1);
        st.delete( 30, 34);
        System.out.println("Line without one russian leksem\n" + st );


        System.out.println();
        System.out.println("Enter your P to find");
        int P = scan.nextInt();
        StringBuffer str3;
        str3 = finding (P, str1, str2);
        System.out.println();
        System.out.printf("%s", "First string with -P after P\n" + str3);
    }
    

    public static String[] leks(String str1, String str2){
        StringTokenizer st = new StringTokenizer(str1, str2);
        int numb = st.countTokens();
        String[] str_mas = new String[numb];
        for (int i = 0; i < numb; i++) {
            str_mas[i] = st.nextToken();
        }
        return str_mas;
    }

    public static int[] parsing(String[] a) {
        int[] mas = new int[a.length];
        int num_elems = 0;
        System.out.println();
        System.out.println("Elements with russian letters");
        int ruselem = -1;
        for(int i = 0; i < a.length; i++) {
            try {
                parseInt(a[i], 2);
                mas[num_elems] = parseInt(a[i]);
                num_elems++;
            }
            catch(Exception e) {
                try { parseInt(a[i]); }
                catch(Exception e1) {
                    boolean isRussian = true;
                    for(int j = 0; j < a[i].length(); j++) {
                        char c = a[i].charAt(j);
                        if(!((c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я'))) isRussian = false;
                    }
                    if(isRussian) {
                        ruselem = i;
                        System.out.println(a[i]);
                    }
                }
            }
        }

        if(ruselem >= 0) {
            for(int i = ruselem; i < a.length - 1; i++) {
                a[i] = a[i + 1]; 
            }
            a[a.length - 1] = null;
        }

        System.out.println();
        System.out.println("Elements in 2 ss");
        int[] res = new int[num_elems];
        for (int j = 0; j < num_elems; j++) {
            res[j] = mas[j];
        }
        return res;
    }

    public static StringBuffer finding(int p, String str1, String str2){
        int a = 0;
        int b = 0;
        boolean is_in_str = false;
        StringBuffer str3 = new StringBuffer(str1);
        while (a < str1.length() && a != -1) {
            a = str1.indexOf(Integer.toString(p) , b);
            b = Integer.toString(p).length() + a;
            if (a != -1) {
                if ((a==0 || str2.contains(Character.toString(str1.charAt(a - 1)))) && (b >= str1.length() || str2.contains(Character.toString(str1.charAt(b))))) {
                    System.out.println(p + " is on " + (a + 1) + " place in the first line ");
                    is_in_str = true;
                    str3.insert(b , -p);
                }
            }
            if(!is_in_str){
            System.out.println("There is no " + p + " in first line");
            }
        }
        return str3;
    }


}
// 101.-453ft-2209ue-110-.Привет-Мир-3452g-1-10223tgdy-1ffas-99-945-495hg-1ddd1-0-34-1001-1
// .-
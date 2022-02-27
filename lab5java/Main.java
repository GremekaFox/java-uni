package lab5java;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Main{

    public static void main(String[] args) throws Exception{

        FileReader read;

        Pattern ptr = Pattern.compile("^((80|\\+375|375){1}(29|44|33|25|17){1}[0-9]{7})$");

        try {
            read = new FileReader("main.txt");
            System.out.println("File is open");
        } 
        catch (Exception e) {
            System.out.println("Can not open the file");
            return;
        }

        ArrayList<String> strO = new ArrayList<String>();
        ArrayList<String> strF = new ArrayList<String>();

        Scanner scan = new Scanner(read);
        
        while (scan.hasNextLine()) {               
            String tmp = scan.nextLine();
            Matcher matcher = ptr.matcher(tmp);
            if (matcher.find()) {
                strO.add(matcher.group(0));
            } 
            else if(!tmp.isEmpty()){
                    strF.add(tmp);
            }
        }

        System.out.print("\nGood numbers\n");
        for (int i = 0; i < strO.size(); i++) {
            System.out.print(strO.get(i) + "\n");
        }
        
        //System.out.print("\nWrong numbers\n");
        //for (int i = 0; i < strF.size(); i++) {
        //    System.out.print(strF.get(i) + "\n");
        //}

        // Pattern p = Pattern.compile("\\s+");
        // Matcher m = p.matcher("Удаляем      \t\t лишние пробелы.   ");
        // System.out.println(m.replaceAll(" "));
       
    }
}
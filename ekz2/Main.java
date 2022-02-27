import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

class Word implements Comparable<Word> {

    String value;
    int ind;

    public Word(String value, int ind) {
        this.value = value;
        this.ind = ind;
    }

    @Override
    public int compareTo(Word other) {

        if (value.length() == other.value.length() || value.equals(other.value)) {
            return 0;
        }

        if (value.length() > other.value.length()) {
            return 1;
        }
        else if (value.length() < other.value.length()) {
            return -1;
        }

        return (ind > other.ind) ? 1 : -1;
    }
}

public class Main {

    private static char newLineSeparator = '\n';
    
    public static void main(String[] args) {

        String separators = readFile("input2.txt");
        separators += ' ';

        System.out.println(separators);

        //1 
        String contents = readFile("input1.txt");

        TreeSet<Character> separatorsInContents = new TreeSet<Character>();

        for (int i = 0; i < contents.length(); i++) {
            if (separators.contains(Character.toString(contents.charAt(i)))) {
                separatorsInContents.add(contents.charAt(i));
            }
        }

        String[] words = contents.split("[" + String.join(Character.toString(','), separatorsInContents.toString()) + "]");
        
        TreeSet<Word> wordsSet = new TreeSet<Word>();
        for (int i = 0; i < words.length; i++) {
            if (!words[i].trim().equals("")) {
                wordsSet.add(new Word(words[i], i));
            }
        }

        ArrayList<String> wordsWithUniqueChars = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].trim().equals("")) {
                continue;
            }

            char[] sortedWord = words[i].toCharArray();
            Arrays.sort(sortedWord);

            boolean nonUnique = false;
            for (int j = 1; j < sortedWord.length; j++) {
                if (sortedWord[j] == sortedWord[j - 1]) {
                    nonUnique = true;
                    break;
                }
            }

            if (!nonUnique) {
                wordsWithUniqueChars.add(words[i]);
            }
        }

        for (int i = 0; i < words.length; i++) {
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }

        String reversedWordsString = String.join("", words);
        String newContents = new String();
        for (int i = 0, j = 0; i < contents.length(); i++) {
            if (!separatorsInContents.contains(contents.charAt(i))) {
                newContents += reversedWordsString.charAt(j);
                j++;
            }
            else {
                newContents += contents.charAt(i);
            }
        }

        try {
            ArrayList<String> output = new ArrayList<String>();

            NavigableSet<Character> reversed = separatorsInContents.descendingSet();
            for (Character c : reversed) {
                if (!c.equals(newLineSeparator)) {
                    output.add(Character.toString(c));
                }
            }
            writeFile("output1.txt", output);
            output.clear();

            for (Word w : wordsSet) {
                output.add(w.value);
            }
            writeFile("output2.txt", output);
            output.clear();

            writeFile("output3.txt", wordsWithUniqueChars);

            output.add(newContents);
            writeFile("output4.txt", output);

        }
        catch (IOException e) {
            System.out.println("IOException occured while trying to write files");
            System.exit(1);
        }
    }

    public static String readFile(String filename) {

        FileReader fr = null;
        try {
            fr = new FileReader(filename);
        }
        catch (FileNotFoundException e) {
            System.out.println(filename + " not found");
        }

        String output = new String();
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                output += line + newLineSeparator;
            }
        }
        catch (IOException e) {
            System.out.println("IOException occured while trying to read contents of the " + filename);
            System.exit(1);
        }

        return output;
    }

    public static void writeFile(String filename, ArrayList<String> contents) throws IOException {

        FileWriter fw = null;
        fw = new FileWriter(filename, true);

        BufferedWriter bw = new BufferedWriter(fw);

        boolean first = true;
        for (String s : contents) {
            if (!s.equals("")) {
                if (first) {
                    bw.append(s);
                    first = false;
                }
                else {
                    bw.append('\n' + s);
                }
            }
        }

        bw.close();
    }

}
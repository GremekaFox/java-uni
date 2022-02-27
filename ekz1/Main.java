import java.io.*;
import java.util.TreeSet;

class IndexedTag implements Comparable<IndexedTag> {
    public String tagName;
    public Integer ind;

    public IndexedTag(String tag, Integer index){
        tagName = tag;
        ind = index;
    }

    @Override public int compareTo(IndexedTag other){
        if (tagName.equals(other.tagName)) {
            return 0;
        }
        if (tagName.length() > other.tagName.length()){
            return -1;
        }
        else if (tagName.length() < other.tagName.length()){
            return 1;
        }
        return (ind > other.ind) ? 1 : -1;
    }
}

class Main{

    public static void main(String[] args) {
        FileReader fr = null;

        try {
            fr = new FileReader("input.txt");
        }
        catch (FileNotFoundException e){
            System.out.println("Error: File Not Found");
            System.exit(1);
        }
         
        BufferedReader bf = new BufferedReader(fr);
        TreeSet<IndexedTag> tagSet = new TreeSet<IndexedTag>();

        try {
            FileWriter fw1 = new FileWriter("output1.txt", true);
            FileWriter fw2 = new FileWriter("output2.txt", true);

            BufferedWriter bw1 = new BufferedWriter(fw1);
            BufferedWriter bw2 = new BufferedWriter(fw2);

            String line;
            int chars = 0;
            while ((line = bf.readLine()) != null){
                chars += processLine(line, chars, tagSet, bw1);
            }
            for (IndexedTag indexedTag : tagSet){
                bw2.append(indexedTag.tagName + '\n');
            }
            bw1.close();
            bw2.close();
        }
        catch (IOException e) {
            System.out.println("Error: IO error");
        }
    }

    public static int processLine(String line, int offset, TreeSet<IndexedTag> tagSet, BufferedWriter writer) throws IOException {
        int charsProcessed = 0;
        
        String tag = "";
        for (int i = 0; i < line.length(); i++, charsProcessed++) {
            if (line.charAt(i) == '<') {
                tag += '<';
            }
            else if (line.charAt(i) == '>') {
                tag += '>';

                writer.append(tag + '\n');
                tagSet.add(new IndexedTag(tag, offset + i));

                tag = "";
            }
            else {
                if (tag.length() > 0) {
                    tag += line.charAt(i);
                }
            }
        }
        return charsProcessed;
    }
}
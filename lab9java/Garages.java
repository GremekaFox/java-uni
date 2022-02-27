
package lab9;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Garages extends Firms implements Serializable {
	private static final long serialVersionUID = 1L;
  transient private String dirName = "./lab9/data/";

  public Garages() { bd = new ArrayList<>(); }

  public Garages(String type) {
    this.bd = new ArrayList<>();
    if(type.equals("csv")) fromCsv();
    else if(type.equals("xml")) fromXml();
    else if(type.equals("bin")) fromBin();
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for(Firm garage : bd) str.append(garage.toString() + ", ");
    str.delete(str.length() - 2, str.length() - 1);
    return str.toString();
  }

  public void fromBin() {
    String fileName = "garages.bin";
    try {
      FileInputStream fis = new FileInputStream(dirName + "bin/" + fileName);
      ObjectInputStream ois= new ObjectInputStream(fis);
      bd = ((Garages)ois.readObject()).getBd();
      ois.close();
      fis.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void toBin() {
    String fileName = "garages.bin";
    try{
      FileOutputStream fos = new FileOutputStream(dirName + "bin/" + fileName);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(this);
      oos.close();
      fos.close();
    } catch(Exception e) {
      e.printStackTrace();
    }

  }

  public void fromXml() {
    String fileName = "garages.xml";
    try {
      FileInputStream fis = new FileInputStream(dirName + "xml/" + fileName);
      XMLDecoder decoder = new XMLDecoder(fis);
      bd = ((Garages)decoder.readObject()).getBd();
      decoder.close();
      fis.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void toXml() {
    try {
      String fileName = "garages.xml";
      FileOutputStream fos = new FileOutputStream(dirName + "xml/" + fileName);
      XMLEncoder enc = new XMLEncoder(fos);
      enc.writeObject(this);
      enc.close();
      fos.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void fromCsv() {
    String fileName = "garages.csv";
    try(Scanner scan = new Scanner(new File(dirName + "csv" + "/" + fileName))) {
      int i = 1;
      while(scan.hasNextLine()) {
        String[] mas = scan.nextLine().split(";");
        String mastersFile = i + "names.csv";
        ArrayList<String> masters = new ArrayList<>();
        String marksFile = i + "marks.csv";
        ArrayList<String> marks = new ArrayList<>();
        try(Scanner scan1 = new Scanner(new File(dirName + "csv" + "/" + mastersFile))) {
          while(scan1.hasNext()) masters.addAll(Arrays.asList(scan1.nextLine().split(";")));
        }
        try(Scanner scan1 = new Scanner(new File(dirName + "csv" + "/" + marksFile))) {
          while(scan1.hasNext()) marks.addAll(Arrays.asList(scan1.nextLine().split(";")));
        }
        add(new Garage(Integer.parseInt(mas[0]), mas[1], marks, masters, mas[2]));
        i++;
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void printInfo() { bd.forEach(Firm::print); }

  @Override
  public void add(Object elem) { bd.add((Garage)elem); }

  public Object getInfo(Integer num) {
    for(Firm garage : bd) {
      if(garage.getNum().equals(num)) return garage;
    }
    return null;
  }

  public void print() {
    for(Firm garage : bd) System.out.println(garage.toString());
  }

  public ArrayList<Integer> getNumbers() {
    ArrayList<Integer> numbers = new ArrayList<>();
    for(Firm garage : bd) {
      numbers.add(garage.getNum());
    }
    return numbers;
  }
  public String getAddress(Integer num) {
    Garage a = (Garage)getInfo(num);
    if(a != null) return a.getAddress();
    return null;
  }

  public ArrayList<String> getMarks(Integer num) {
    Garage a = (Garage)getInfo(num);
    if(a != null) return a.getMarks();
    return null;
  }

  public ArrayList<String> getMasters(Integer num) {
    Garage a = (Garage)getInfo(num);
    if(a != null) return a.getMasters();
    return null;
  }

  public String getDate(Integer num) {
    Garage a = (Garage)getInfo(num);
    if(a != null) return a.getDate();
    return null;
  }
}

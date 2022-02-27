package lab8;

import java.util.ArrayList;
import java.io.Serializable;

public class Garage extends Firm implements Serializable {
  private static final long serialVersionUID = 1L;
  ArrayList<String> marks;
  ArrayList<String> masters;

  public Garage() { super(0, "", ""); }

  public Garage(Integer num, String address, ArrayList<String> marks,
                              ArrayList<String> masters, String date) {
      super(num, address, date);
      this.marks = new ArrayList<>(marks);
      this.masters = new ArrayList<>(masters);
  }


  public ArrayList<String> getMarks() { return marks == null ? new ArrayList<>() : new ArrayList<>(marks); }
  public void setMarks(ArrayList<String> marks) {this.marks = new ArrayList<>(marks); }
  public ArrayList<String> getMasters() { return masters == null ? new ArrayList<>() : new ArrayList<>(masters); }
  public void setMasters(ArrayList<String> masters) {this.masters = new ArrayList<>(masters); }

  @Override
  public void print() {
      super.print();
      System.out.println("Masters:");
      masters.forEach(System.out::println);
      System.out.println("Marks:");
      marks.forEach(System.out::println);
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    res.append("Garage" + num + " [");
    res.append("num=" + num);
    res.append(", address=" + address);
    res.append(", dateFoundation=" + date + ", Marks ");
    res.append(marks.toString());
    res.append(", Masters ");
    res.append(masters.toString());
    res.append("]");
    return res.toString();
  }
}

package lab9;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Firms implements Info, Serializable {
	private static final long serialVersionUID = 1L;
  protected ArrayList<Firm> bd;
  public void add(Object elem) { bd.add((Firm)elem); }
  public void printInfo() { bd.forEach(Firm::print); }
  abstract public Object getInfo(Integer num);
  abstract public ArrayList<Integer> getNumbers();
  abstract public String getAddress(Integer num);
  abstract public String getDate(Integer num);
  public ArrayList<Firm> getBd() { return new ArrayList<>(bd); }
  public void setBd(ArrayList<Firm> bd) { this.bd = new ArrayList<Firm>(bd); }
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for(Firm elem : bd) str.append(elem.toString());
    return str.toString();
  }
}

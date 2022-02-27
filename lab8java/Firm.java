package lab8;

import java.io.Serializable;

public abstract class Firm implements Serializable {
	private static final long serialVersionUID = 1L;
  protected Integer num;
  protected String address;
  protected String date;
  public Firm(Integer num, String address, String date) {
      this.num = num;
      this.address = address;
      this.date = date;
  }
  public Integer getNum() { return this.num; }
  public void setNum(Integer num) { this.num = num; }
  public String getAddress() { return address; }
  public void setAddress(String address) {this.address = address; }
  public String getDate() { return date; }
  public void setDate(String date) {this.date = date; }
  public void print() {
      System.out.println("number: " + num + " " + "address: " + address + " "
       + "date of foundation: " + date);
  }
  public String toString() {
    return "num=" + num + ", address='" + address + "', dateFoundation=" + date + "'";
  }
}

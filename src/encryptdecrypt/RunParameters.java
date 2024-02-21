package encryptdecrypt;

public class RunParameters {

  private String mode = "enc";
  private int key = 0;
  private String data = "";
  private String in = "";
  private String out = "";

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getIn() {
    return in;
  }

  public void setIn(String in) {
    this.in = in;
  }

  public String getOut() {
    return out;
  }

  public void setOut(String out) {
    this.out = out;
  }
}

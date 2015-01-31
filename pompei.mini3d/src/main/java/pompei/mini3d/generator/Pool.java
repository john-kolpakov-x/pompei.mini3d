package pompei.mini3d.generator;

public class Pool {
  private final StringBuilder target = new StringBuilder();
  
  public void pr(String s) {
    target.append(s);
  }
  
  public void prn(String s) {
    target.append(s).append("\n");
  }
  
  public void prn() {
    target.append("\n");
  }
  
  @Override
  public String toString() {
    return target.toString();
  }
}

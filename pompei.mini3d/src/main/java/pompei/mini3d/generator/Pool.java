package pompei.mini3d.generator;

public class Pool {
  private final StringBuilder target = new StringBuilder();
  
  public void print(String s) {
    target.append(s);
  }
  
  public void println(String s) {
    target.append(s).append("\n");
  }
  
  public void println() {
    target.append("\n");
  }
  
  @Override
  public String toString() {
    return target.toString();
  }
}

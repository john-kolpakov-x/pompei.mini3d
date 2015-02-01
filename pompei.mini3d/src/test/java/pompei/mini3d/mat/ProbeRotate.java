package pompei.mini3d.mat;

import static pompei.mini3d.Util.DEG;

public class ProbeRotate {
  public static void main(String[] args) {
    Mat4 m = new Mat4().setRotateZ(51 * DEG);
    
    double v[] = new double[] { 30, 35, 0 };
    
    System.out.println("   До " + Mat4.vecStr3(v, 0));
    m.mul3(v, 0, v, 0);
    System.out.println("После " + Mat4.vecStr3(v, 0));
  }

  //TODO kill it: For test commit
}
